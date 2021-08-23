package sample.customvalidations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LocalDateTimeValidator implements ConstraintValidator<LocalDateTimeFormat, String> {

	private String pattern;

	private DateTimeType dateTimeType;

	@Override
	public void initialize(LocalDateTimeFormat constraintAnnotation) {
		this.pattern = constraintAnnotation.pattern();
		this.dateTimeType = constraintAnnotation.dateTimeType();
	}

	@Override
	public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
		if (object == null || "".equals(object)) {
			return true;
		}

		try {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
			if (DateTimeType.Time.equals(dateTimeType)) {
				LocalTime.parse(object, dateFormatter);
			}
			else if (DateTimeType.Date.equals(dateTimeType)) {
				LocalDate.parse(object, dateFormatter);
			}
			else {
				LocalDateTime.parse(object, dateFormatter);
			}
			return true;
		}
		catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}
}