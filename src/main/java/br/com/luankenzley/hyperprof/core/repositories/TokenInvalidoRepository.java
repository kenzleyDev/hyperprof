package br.com.luankenzley.hyperprof.core.repositories;

import br.com.luankenzley.hyperprof.core.models.TokenInvalido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenInvalidoRepository extends JpaRepository<TokenInvalido, Long> {

    boolean existsByToken(String token);
}
