package sample.config;

import java.lang.annotation.Annotation;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.media.Schema;
import sample.customvalidations.LocalDateTimeFormat;

import org.springframework.context.annotation.Configuration;

@Configuration
class CustomOpenApiValidator extends ModelResolver {

	private final Class[] handledValidations = { javax.validation.constraints.NotNull.class,
			javax.validation.constraints.NotBlank.class,
			javax.validation.constraints.NotEmpty.class,
			javax.validation.constraints.Min.class,
			javax.validation.constraints.Max.class,
			javax.validation.constraints.DecimalMin.class,
			javax.validation.constraints.DecimalMax.class,
			javax.validation.constraints.Pattern.class,
			javax.validation.constraints.Size.class };

	private final Package[] allowedPackages = { handledValidations[0].getPackage(),
			org.hibernate.validator.constraints.CreditCardNumber.class.getPackage(),
			LocalDateTimeFormat.class.getPackage() };

	public CustomOpenApiValidator(ObjectMapper mapper) {
		super(mapper);
	}

	@Override
	protected void applyBeanValidatorAnnotations(Schema property, Annotation[] annotations, Schema parent) {
		super.applyBeanValidatorAnnotations(property, annotations, parent);
		if (annotations != null) {
			for (Annotation annotation : annotations) {
				Class<? extends Annotation> annotationType = annotation.annotationType();
				boolean handled = false;
				for (Class check : handledValidations) {
					if (annotationType == check) {
						handled = true;
						break;
					}
				}
				if (!handled) {
					Package annotationPackage = annotationType.getPackage();
					boolean allowed = false;
					for (Package allowedPackage : allowedPackages) {
						if (allowedPackage == annotationPackage) {
							allowed = true;
							break;
						}
					}
					if (allowed) {
						Map extensions = property.getExtensions();
						String extensionKey = "x-" + annotationType.getSimpleName();
						if (!(extensions != null && extensions.containsKey(extensionKey))) {
							Object value = describeAnnotation(annotation, annotationType);
							property.addExtension(extensionKey, value);

						}
					}
				}
			}
		}

	}

	private Object describeAnnotation(Annotation annotation, Class<? extends Annotation> annotationType) {
		Object ret = true;
		if (annotationType == LocalDateTimeFormat.class) {
			LocalDateTimeFormat format = (LocalDateTimeFormat) annotation;
			ret = new DateTimeFormatData(format.pattern(), format.dateTimeType().name());

		}
		return ret;
	}
}

