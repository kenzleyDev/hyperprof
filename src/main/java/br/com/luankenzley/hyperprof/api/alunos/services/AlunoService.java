package br.com.luankenzley.hyperprof.api.alunos.services;

import br.com.luankenzley.hyperprof.api.alunos.dtos.AlunoRequest;
import br.com.luankenzley.hyperprof.api.alunos.dtos.AlunoResponse;

import java.util.List;

public interface AlunoService {
    List<AlunoResponse> listarAlunosPorProfessorLogado();
    AlunoResponse cadastrarAluno(AlunoRequest alunoRequest, Long professorId);

}
