package sample.customvalidations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
		ElementType.ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = LocalDateTimeValidator.class)
@Documented
public @interface LocalDateTimeFormat {

	String message() default "{message.key}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String pattern();

	DateTimeType dateTimeType() default DateTimeType.DateTime;

}