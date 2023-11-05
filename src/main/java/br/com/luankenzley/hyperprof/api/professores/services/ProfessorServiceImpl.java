package br.com.luankenzley.hyperprof.api.professores.services;

import br.com.luankenzley.hyperprof.api.professores.dtos.ProfessorRequest;
import br.com.luankenzley.hyperprof.api.professores.dtos.ProfessorResponse;
import br.com.luankenzley.hyperprof.api.professores.mappers.ProfessorMapper;
import br.com.luankenzley.hyperprof.core.Exceptions.ProfessorNotFoundException;
import br.com.luankenzley.hyperprof.core.models.AuthenticatedUser;
import br.com.luankenzley.hyperprof.core.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService{
    private final ProfessorMapper professorMapper;
    private final ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<ProfessorResponse> buscarProfessores(String descricao) {
        return professorRepository.findByDescricaoContaining(descricao)
                .stream()
                .map(professorMapper::toProfessorResponse)
                .toList();
    }

    @Override
    public ProfessorResponse buscarProfessorPorId(Long professorId) {
        return professorRepository.findById(professorId)
                .map(professorMapper::toProfessorResponse)
                .orElseThrow(ProfessorNotFoundException::new);
    }

    @Override
    public ProfessorResponse cadastrarProfessor(ProfessorRequest professorRequest) {
        var professorParaCadastrar = professorMapper.toProfessor(professorRequest);
        professorParaCadastrar.setPassword(passwordEncoder.encode(professorParaCadastrar.getPassword()));
        var professorCadastrado = professorRepository.save(professorParaCadastrar);

        return professorMapper.toProfessorResponse(professorCadastrado);
    }

    @Override
    public ProfessorResponse atualizarProfessorLogado(ProfessorRequest professorRequest) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var professor = ((AuthenticatedUser) authentication.getPrincipal()).getProfessor();
        BeanUtils.copyProperties(professorRequest, professor, "id", "password", "createdAt", "updatedAt");
        professor.setPassword(passwordEncoder.encode(professorRequest.getPassword()));
        var professorAtualiado = professorRepository.save(professor);
        return professorMapper.toProfessorResponse(professorAtualiado);
    }

    @Override
    public void excluirProfessorLogado() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var professor = ((AuthenticatedUser) authentication.getPrincipal()).getProfessor();
        professorRepository.delete(professor);
    }
}
