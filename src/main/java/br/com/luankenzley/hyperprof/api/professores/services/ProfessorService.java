package br.com.luankenzley.hyperprof.api.professores.services;

import br.com.luankenzley.hyperprof.api.professores.dtos.ProfessorResponse;

import java.util.List;

public interface ProfessorService {

    List<ProfessorResponse> buscarProfessores(String descricao);

    ProfessorResponse buscarProfessorPorId(Long professorId);
}
