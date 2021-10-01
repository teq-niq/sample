package sample.config;

import java.io.IOException;
import java.util.Locale;

import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@JsonComponent
public class MonetaryAmountJsonDeSerializer extends StdDeserializer<javax.money.MonetaryAmount>
		implements ContextualDeserializer {

	public MonetaryAmountJsonDeSerializer() {
		super(javax.money.MonetaryAmount.class);
		
	}

	@Override
	public MonetaryAmount deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		// in case this is used change logic accordingly
		throw new AssertionError("Not expecting the method to be used");

	}

	@Override
	public JsonDeserializer<javax.money.MonetaryAmount> createContextual(DeserializationContext ctxt,
			BeanProperty property) throws JsonMappingException {

		JsonDeserializer<javax.money.MonetaryAmount> ret = null;
		String name = property.getName();
		final JsonFormat.Value jsonFormat = findFormatOverrides(ctxt, property, handledType());
		if (jsonFormat != null) {
			String pattern = patternWithDefault(jsonFormat, "#,##0.00### ¤");
			Locale locale = localeWithDefault(jsonFormat);
			
			ret = new JsonDeserializer<javax.money.MonetaryAmount>() {

				@Override
				public MonetaryAmount deserialize(JsonParser p, DeserializationContext ctxt)
						throws IOException, JsonProcessingException {
					String readValue = p.getCodec().readValue(p, String.class);
					AmountFormatQueryBuilder builder = AmountFormatQueryBuilder.of(locale).set("pattern", pattern);
					//builder.set(CurrencyStyle.SYMBOL) should we use shape of explore enhancing
					final MonetaryAmountFormat format = MonetaryFormats.getAmountFormat(builder.build());
					MonetaryAmount formatted = format.parse(readValue);
					return formatted;

				}
			};

		} else {
			
			throw new AssertionError("Even if @JsonFormat is not used this condition is not expected");
		}

		return ret;
	}

	private Locale localeWithDefault(final JsonFormat.Value format) {
		Locale locale = format.getLocale();
		if(locale==null)
		{
			locale = LocaleContextHolder.getLocale();
		}
		return locale;
	}

	private String patternWithDefault(final JsonFormat.Value format, String defaultPattern) {
		String pattern = format.getPattern();
		if (pattern == null) {

			pattern = defaultPattern;
		}
		return pattern;
	}

}
