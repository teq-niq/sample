package sample.config;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;

@Configuration
class OpenApiConfig {
	@Autowired
	TypesCapturer typesCapturer;

	@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application-version}") String appVersion) {
		return new OpenAPI()
				.info(new Info()
						.title("sample application API")
						.version(appVersion)
						.description(appDesciption)
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
	
	/**
	 * In this we are trying to enforce resolved schemas than schemas from 
	 * io.swagger.v3.core.converter.ModelConverterContextImpl.getDefinedModels()
	 * @return
	 */
	@Bean
	@Lazy(false)
	OpenApiCustomiser resolvedSchemaEnforcer() {
		return new OpenApiCustomiser() {

			@Override
			public void customise(OpenAPI openApi) {
				Map<String, Type> resolvedSchemas = typesCapturer.getResolvedSchemas();
				
				
				Map<String, Schema> schemas = openApi.getComponents().getSchemas();
				Set<Entry<String, Schema>> entrySet = schemas.entrySet();
				for (Entry<String, Schema> entry : entrySet) {
					String key = entry.getKey();
					Type type = resolvedSchemas.get(key);
					if(type!=null)
					{
						ResolvedSchema resolvedSchema = ModelConverters.getInstance().readAllAsResolvedSchema(type);
						if(resolvedSchema!=null && resolvedSchema.schema!=null)
						{
							schemas.put(key, resolvedSchema.schema);
						}
						
					}
					
				}
				
			}};
	}
	
	

	

	

}
