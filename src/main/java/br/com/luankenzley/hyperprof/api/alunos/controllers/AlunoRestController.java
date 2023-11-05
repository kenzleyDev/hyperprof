package br.com.luankenzley.hyperprof.api.alunos.controllers;

import br.com.luankenzley.hyperprof.api.alunos.dtos.AlunoRequest;
import br.com.luankenzley.hyperprof.api.alunos.dtos.AlunoResponse;
import br.com.luankenzley.hyperprof.api.alunos.services.AlunoService;
import br.com.luankenzley.hyperprof.api.common.routes.ApiRoutes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlunoRestController {

    private final AlunoService alunoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(ApiRoutes.CADASTRAR_ALUNO)
    public AlunoResponse cadastrarAluno(
            @RequestBody @Valid AlunoRequest alunoRequest,
            @PathVariable Long professorId
    ) {
        return alunoService.cadastrarAluno(alunoRequest, professorId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(ApiRoutes.LISTAR_ALUNOS_POR_PROFESSOR_LOGADO)
    public List<AlunoResponse> listarAlunosPorProfessorLogado() {
        return alunoService.listarAlunosPorProfessorLogado();
    }
}
