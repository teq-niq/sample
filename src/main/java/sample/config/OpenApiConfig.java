package sample.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.SchemaSerializerReplacer;
import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.SerializerFactory;

import io.swagger.v3.core.util.Json;
import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
class OpenApiConfig {

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
	
	@PostConstruct
	public void init()
	{
		ObjectMapper yamlMapper = Yaml.mapper();
		ObjectMapper jsponMapper = Json.mapper();
		modify(yamlMapper);
		modify(jsponMapper);
	
		
	}

	private void modify(ObjectMapper jsponMapper) {
		SerializerFactory serializerFactory = jsponMapper.getSerializerFactory();
		BeanSerializerFactory bsf=(BeanSerializerFactory) serializerFactory;
		SerializerFactoryConfig factoryConfig = bsf.getFactoryConfig();

		SchemaSerializerReplacer.modify(factoryConfig);
	}

	

}
