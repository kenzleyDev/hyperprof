package br.com.luankenzley.hyperprof.api.alunos.services;

import br.com.luankenzley.hyperprof.api.alunos.dtos.AlunoRequest;
import br.com.luankenzley.hyperprof.api.alunos.dtos.AlunoResponse;
import br.com.luankenzley.hyperprof.api.alunos.mappers.AlunoMapper;
import br.com.luankenzley.hyperprof.core.Exceptions.ProfessorNotFoundException;
import br.com.luankenzley.hyperprof.core.models.AuthenticatedUser;
import br.com.luankenzley.hyperprof.core.repositories.AlunoRepository;
import br.com.luankenzley.hyperprof.core.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService{

    private final AlunoMapper alunoMapper;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    @Override
    public AlunoResponse cadastrarAluno(AlunoRequest alunoRequest, Long professorId) {
        var professor = professorRepository.findById(professorId)
                .orElseThrow(ProfessorNotFoundException::new);

        var alunoParaCadastrar = alunoMapper.toAluno(alunoRequest);

        alunoParaCadastrar.setProfessor(professor);
        var alunoCadastrado = alunoRepository.save(alunoParaCadastrar);

        return alunoMapper.toAlunoResponse(alunoCadastrado);
    }

    @Override
    public List<AlunoResponse> listarAlunosPorProfessorLogado() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var professor = ((AuthenticatedUser) authentication.getPrincipal()).getProfessor();
        return alunoRepository.findByProfessor(professor)
                .stream()
                .map(alunoMapper::toAlunoResponse)
                .toList();
    }

}
