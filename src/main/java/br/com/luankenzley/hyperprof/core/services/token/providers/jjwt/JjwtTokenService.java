package br.com.luankenzley.hyperprof.core.services.token.providers.jjwt;

import br.com.luankenzley.hyperprof.core.models.TokenInvalido;
import br.com.luankenzley.hyperprof.core.repositories.TokenInvalidoRepository;
import br.com.luankenzley.hyperprof.core.services.token.TokenService;
import br.com.luankenzley.hyperprof.core.services.token.TokenServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class JjwtTokenService implements TokenService {

    private final JjwtConfigProperties configProperties;
    private final TokenInvalidoRepository tokenInvalidoRepository;

    @Override
    public String gerarAccessToken(String subject) {
        return gerarToken(
                subject,
                configProperties.getAcessTokenExpirationInSeconds(),
                configProperties.getAccessTokenSigningKey());
    }

    @Override
    public String getSubjectDoAccessToken(String accessToken) {
        return getClaims(accessToken, configProperties.getAccessTokenSigningKey()).getSubject();
    }

    @Override
    public String gerarRefreshToken(String subject) {
        return gerarToken(
                subject,
                configProperties.getRefreshTokenExpirationInSeconds(),
                configProperties.getRefreshTokenSigningKey()
        );
    }

    @Override
    public String getSubjectDoRefreshToken(String refreshToken) {
        return getClaims(refreshToken, configProperties.getRefreshTokenSigningKey()).getSubject();
    }

    @Override
    public void invalidarTokens(String... tokens) {
        var tokensInvalidos = Stream.of(tokens)
                .map(token -> TokenInvalido.builder().token(token).build())
                .toList();
        tokenInvalidoRepository.saveAll(tokensInvalidos);

    }

    private String gerarToken(String subject, Long expirationInSeconds, String signinKey){
        var dataHoraAtual = Instant.now();
        var dataHoraExpiracao = dataHoraAtual.plusSeconds(expirationInSeconds);

        return Jwts.builder()
                .setClaims(new HashMap<String, Object>())
                .setSubject(subject)
                .setIssuedAt(Date.from(dataHoraAtual))
                .setExpiration(Date.from(dataHoraExpiracao))
                .signWith(Keys.hmacShaKeyFor(signinKey.getBytes()))
                .compact();
    }


    private Claims getClaims(String token, String signinKey) {
        try{
            return tryGetClaims(token, signinKey);
        } catch (JwtException e) {
            throw new TokenServiceException(e.getLocalizedMessage());
        }
    }

    private Claims tryGetClaims(String token, String signinKey) {

        if(tokenInvalidoRepository.existsByToken(token)) {
            throw new TokenServiceException("Token inválido");
        }
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(signinKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
