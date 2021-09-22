package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.JsonSerializer;

import io.swagger.v3.core.jackson.SchemaSerializer;
import io.swagger.v3.core.util.ObjectMapperFactory;
import io.swagger.v3.core.util.SchemaSerializerFactory;
import sample.config.CustomSchemaSerializer;

@SpringBootApplication
public class SampleApplication {

	
	public static void main(String[] args) {
		ObjectMapperFactory.setSchemaSerializerFactory(new SchemaSerializerFactory() {
			
			@Override
			public SchemaSerializer create(JsonSerializer<?> serializer) {
				
				return new CustomSchemaSerializer((JsonSerializer<Object>)serializer);
			}
		});
		SpringApplication.run(SampleApplication.class, args);
	}
	

}