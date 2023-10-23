package br.com.luankenzley.hyperprof.api.professores.services;

import br.com.luankenzley.hyperprof.api.professores.dtos.ProfessorResponse;
import br.com.luankenzley.hyperprof.api.professores.mappers.ProfessorMapper;
import br.com.luankenzley.hyperprof.core.Exceptions.ProfessorNotFoundException;
import br.com.luankenzley.hyperprof.core.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService{

    private final ProfessorMapper professorMapper;
    private final ProfessorRepository professorRepository;

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
}
