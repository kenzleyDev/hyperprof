package br.com.luankenzley.hyperprof.core.repositories;

import br.com.luankenzley.hyperprof.core.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    boolean existsByEmail(String email);

    Optional<Professor> findByEmail(String email);
    List<Professor> findByDescricaoContaining(String descricao);
}
