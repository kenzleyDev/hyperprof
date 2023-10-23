package br.com.luankenzley.hyperprof.core.repositories;

import br.com.luankenzley.hyperprof.core.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    List<Professor> findByDescricaoContaining(String descricao);
}
