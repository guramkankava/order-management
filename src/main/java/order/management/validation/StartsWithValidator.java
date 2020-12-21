package order.management.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class StartsWithValidator implements ConstraintValidator<StartsWith, String>{

    private String prefix;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isBlank(value)) {
            return true;
        }
        return value.startsWith(this.prefix);
    }

    @Override
    public void initialize(StartsWith constraintAnnotation) {
        this.prefix = constraintAnnotation.prefix();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

}
