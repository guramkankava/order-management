package order.management.validation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CommisionValidator.class)
@Documented
@Target(TYPE)
public @interface Commission {

    String message() default "Min commision is 50 GEL or 1 percent of amount";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
