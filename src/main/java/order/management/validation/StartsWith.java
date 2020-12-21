package order.management.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StartsWithValidator.class)
@Documented
public @interface StartsWith {

    String message() default "Must start with {prefix}";

    String prefix() default "5";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
