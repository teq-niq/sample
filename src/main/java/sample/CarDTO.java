package sample;

import javax.money.MonetaryAmount;

import org.hibernate.validator.constraints.Currency;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarDTO {
	@JsonProperty("price")
	@Currency("USD")
	MonetaryAmount price;
}
