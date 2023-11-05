package br.com.luankenzley.hyperprof.api.professores.validators;

import br.com.luankenzley.hyperprof.core.repositories.ProfessorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfessorEmailIsUniqueValidator implements ConstraintValidator<ProfessorEmailIsUnique,String> {

    private final ProfessorRepository professorRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) {
            return true;
        }

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var isAuthenticated = authentication != null
                && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated();
        if(isAuthenticated) {
            var professor = professorRepository.findByEmail(value);
            return professor.isEmpty() || professor.get().getEmail().equals(authentication.getName());
        }

        return !professorRepository.existsByEmail(value);
    }
}
