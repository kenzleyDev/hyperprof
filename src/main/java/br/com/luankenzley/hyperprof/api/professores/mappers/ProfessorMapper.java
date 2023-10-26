package br.com.luankenzley.hyperprof.api.professores.mappers;

import br.com.luankenzley.hyperprof.api.professores.dtos.ProfessorRequest;
import br.com.luankenzley.hyperprof.api.professores.dtos.ProfessorResponse;
import br.com.luankenzley.hyperprof.core.models.Professor;

public interface ProfessorMapper {

    Professor toProfessor(ProfessorRequest professorRequest);
    ProfessorResponse toProfessorResponse(Professor professor);
}
