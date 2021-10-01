package sample.config;

import java.io.IOException;
import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;

import org.javamoney.moneta.Money;
import org.javamoney.moneta.format.CurrencyStyle;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
@JsonComponent
public class MonetaryAmountJsonDeSerializer extends JsonDeserializer<javax.money.MonetaryAmount>{

	@Override
	public MonetaryAmount deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String readValue = p.getCodec().readValue(p, String.class);
		Locale locale = LocaleContextHolder.getLocale();
		final MonetaryAmountFormat format = MonetaryFormats.getAmountFormat(
	            AmountFormatQueryBuilder.of(locale)
	                    //.set(CurrencyStyle.SYMBOL)
	                    .set("pattern", "#,##0.00### ¤")
	                    .build()
	    );
		return format.parse(readValue);
		
	}

	

}
