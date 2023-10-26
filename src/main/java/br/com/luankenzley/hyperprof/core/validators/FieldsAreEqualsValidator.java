package br.com.luankenzley.hyperprof.core.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class FieldsAreEqualsValidator implements ConstraintValidator<FieldsAreEquals, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsAreEquals constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
        validateParameters();
    }


    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate())
                .addPropertyNode(fieldMatch)
                .addConstraintViolation();
        var fieldPropertyDescriptor = BeanUtils.getPropertyDescriptor(o.getClass(), field);
        var fieldMatchPropertyDescriptor = BeanUtils.getPropertyDescriptor(o.getClass(), fieldMatch);

        if(fieldPropertyDescriptor == null || fieldPropertyDescriptor == null) {
            throw new IllegalArgumentException("Os campos informados não existem na classe");
        }

        try {
            var fieldValue = fieldPropertyDescriptor.getReadMethod().invoke(o);
            var fieldMatchValue = fieldMatchPropertyDescriptor.getReadMethod().invoke(o);
            return Objects.equals(fieldValue, fieldMatchValue);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Não foi possível acessar os campos");
        }


    }

    private void validateParameters() {
        if(field == null || field.isEmpty()) {
            throw new IllegalArgumentException("O campo 'field' não pode ser nulo ou vazio");
        }
        if(fieldMatch == null || fieldMatch.isEmpty()) {
            throw new IllegalArgumentException("O campo 'fieldMatch' não pode ser nulo ou vazio");
        }
    }

}
