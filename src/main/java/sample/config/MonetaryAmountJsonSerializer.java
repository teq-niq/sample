package sample.config;

import java.io.IOException;
import java.util.Locale;

import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;

import org.javamoney.moneta.format.CurrencyStyle;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
@JsonComponent
public class MonetaryAmountJsonSerializer extends JsonSerializer<javax.money.MonetaryAmount>{

	@Override
	public void serialize(MonetaryAmount value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		Locale locale = LocaleContextHolder.getLocale();
		final MonetaryAmountFormat format = MonetaryFormats.getAmountFormat(
	            AmountFormatQueryBuilder.of(locale)
	                    .set(CurrencyStyle.SYMBOL)
	                    .set("pattern", "#,##0.00### ¤")
	                    .build()
	    );
		String formatted = format.format(value);
		gen.writeString(formatted);
		
	}

	

}
