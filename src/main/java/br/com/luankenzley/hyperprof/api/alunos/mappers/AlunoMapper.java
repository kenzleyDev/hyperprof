package br.com.luankenzley.hyperprof.api.alunos.mappers;

import br.com.luankenzley.hyperprof.api.alunos.dtos.AlunoRequest;
import br.com.luankenzley.hyperprof.api.alunos.dtos.AlunoResponse;
import br.com.luankenzley.hyperprof.core.models.Aluno;

public interface AlunoMapper {

    Aluno toAluno(AlunoRequest alunoRequest);
    AlunoResponse toAlunoResponse(Aluno aluno);

}
