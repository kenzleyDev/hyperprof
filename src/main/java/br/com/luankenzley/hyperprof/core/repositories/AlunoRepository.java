package br.com.luankenzley.hyperprof.core.repositories;

import br.com.luankenzley.hyperprof.core.models.Aluno;
import br.com.luankenzley.hyperprof.core.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByProfessor(Professor professor);
}
