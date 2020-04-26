package sample;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.core.jackson.TypeNameResolver;


class CustomConverter extends ModelResolver {

	public static void add(ObjectMapper ojectMapper)
	{
		ModelConverters.getInstance().addConverter(new CustomConverter(ojectMapper));
	}
    public CustomConverter(ObjectMapper mapper) {
        super(mapper, new QualifiedTypeNameResolver());
    }
    
    static class QualifiedTypeNameResolver extends TypeNameResolver {

        @Override
        protected String nameForClass(Class<?> cls, Set<Options> options) {
            String className = cls.getName().startsWith("java.") ? cls.getSimpleName() : cls.getName();
            if (options.contains(Options.SKIP_API_MODEL)) {
                return className;
            }
            final io.swagger.v3.oas.annotations.media.Schema model = cls.getAnnotation(io.swagger.v3.oas.annotations.media.Schema.class);
            final String modelName = model == null ? null : StringUtils.trimToNull(model.name());
            return modelName == null ? className : modelName;
        }
    }
}

