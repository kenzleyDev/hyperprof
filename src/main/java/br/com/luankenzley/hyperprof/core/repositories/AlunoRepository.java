package br.com.luankenzley.hyperprof.core.repositories;

import br.com.luankenzley.hyperprof.core.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
