package br.com.luankenzley.hyperprof.api.common.handlers;

import br.com.luankenzley.hyperprof.api.common.dtos.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
            throws IOException, ServletException {
        var status = HttpStatus.UNAUTHORIZED;
        var body = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(authException.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .cause(authException.getClass().getSimpleName())
                .build();

        var json = objectMapper.writeValueAsString(body);

        response.setStatus(status.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
}
