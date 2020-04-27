package sample;

import javax.annotation.PostConstruct;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.springdoc.core.SpringDocUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.zalando.jackson.datatype.money.MoneyModule;

@SpringBootApplication

@ComponentScan(basePackages = {  "sample" })
public class SampleApplication {
	
	@PostConstruct
	void init()
	{
		SpringDocUtils.getConfig().replaceWithClass(MonetaryAmount.class, MonetaryAmountConverter.class);
	}

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }
    
    @Bean
    public MoneyModule moneyModule() {
    	return new MoneyModule().withMonetaryAmount(Money::of);
    }

    
}
