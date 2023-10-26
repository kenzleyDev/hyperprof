package br.com.luankenzley.hyperprof.api.professores.dtos;

import br.com.luankenzley.hyperprof.api.professores.validators.ProfessorEmailIsUnique;
import br.com.luankenzley.hyperprof.core.validators.FieldsAreEquals;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@FieldsAreEquals(field = "password", fieldMatch = "passwordConfirmation")
public class ProfessorRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String nome;

    @Email
    @NotNull
    @NotEmpty
    @ProfessorEmailIsUnique
    @Size(min = 3, max = 255)
    private String email;

    @NotNull
    @Min(18)
    @Max(100)
    private int idade;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 500)
    private String descricao;

    @NotNull
    @Min(10)
    @Max(500)
    private BigDecimal valorHora;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 255)
    private String password;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 255)
    private String passwordConfirmation;
}
