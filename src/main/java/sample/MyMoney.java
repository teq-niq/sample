package sample;

import java.math.BigDecimal;

import org.javamoney.moneta.Money;
import org.springdoc.core.converters.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema
public class MyMoney extends MonetaryAmount {
	@JsonCreator
	public MyMoney(BigDecimal amount, String currencyAbbr) {
		super(amount, currencyAbbr);
		
	}

	@Override
	protected javax.money.MonetaryAmount delegateImpl(BigDecimal amount, String currencyAbbr) {
		
		return Money.of(amount, currencyAbbr);
	}

}
