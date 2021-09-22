package sample.config;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.media.Schema;
@Configuration
public class TypesCapturer extends ModelResolver {

	private final Map<String, Type>  resolvedSchemas= new HashMap<>();

	public Map<String, Type> getResolvedSchemas() {
		return resolvedSchemas;
	}

	public TypesCapturer(ObjectMapper mapper) {
		super(mapper);
		
		
	}

	@Override
	public Schema resolve(AnnotatedType annotatedType, ModelConverterContext context, Iterator<ModelConverter> next) {
		Schema resolve = super.resolve(annotatedType, context, next);
		Type type = annotatedType.getType();
		String name = resolve.getName();
		if(name!=null)
        {
     	  resolvedSchemas.put(name, type);
        }
		
		return resolve;
	}

	

	

}
