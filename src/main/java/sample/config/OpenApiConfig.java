package sample.config;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.money.MonetaryAmount;

import org.springdoc.core.SpringDocUtils;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;

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
	void init() {
		SpringDocUtils.getConfig().replaceWithSchema(MonetaryAmount.class, new MonetaryAmountSchema());
		
		
		
	}
	
//	@Bean
//	public MoneyModule moneyModule() {
//		return new MoneyModule().withMonetaryAmount(Money::of);
//	}
	
	@Bean
	@Lazy(false)
	OpenApiCustomiser resolvedSchemaEnforcer() {
		return new OpenApiCustomiser() {

			@Override
			public void customise(OpenAPI openApi) {
				
				Map<String, Schema> schemas = openApi.getComponents().getSchemas();
				Set<Entry<String, Schema>> entrySet = schemas.entrySet();
				for (Entry<String, Schema> entry : entrySet) {
					String key = entry.getKey();
					Schema schema = entry.getValue();
					System.out.println(key+"="+schema);
					Map<String, Schema> properties = schema.getProperties();
					Set<String> keySet = properties.keySet();
					for (String propertyName : keySet) {
						Schema schema2 = properties.get(propertyName);
						System.out.println(propertyName+"="+schema2);
					}
				}
				
			}};
	}

}
