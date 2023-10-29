package br.com.luankenzley.hyperprof.api.professores.services;

import br.com.luankenzley.hyperprof.api.professores.dtos.ProfessorResponse;
import br.com.luankenzley.hyperprof.api.professores.mappers.ProfessorMapper;
import br.com.luankenzley.hyperprof.core.models.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeServiceImpl implements MeService{

    private final ProfessorMapper professorMapper;

    @Override
    public ProfessorResponse buscarProfessorLogado() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var professor = ((AuthenticatedUser) authentication.getPrincipal()).getProfessor();
        return professorMapper.toProfessorResponse(professor);

    }
}
