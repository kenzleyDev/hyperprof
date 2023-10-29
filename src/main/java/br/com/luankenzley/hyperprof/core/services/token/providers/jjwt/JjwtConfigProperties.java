package br.com.luankenzley.hyperprof.core.services.token.providers.jjwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "br.com.luankenzley.hyperprof.core.services.token.jjwt")
public class JjwtConfigProperties {

    private String accessTokenSigningKey;
    private Long acessTokenExpirationInSeconds;
    private String refreshTokenSigningKey;
    private Long refreshTokenExpirationInSeconds;

}
