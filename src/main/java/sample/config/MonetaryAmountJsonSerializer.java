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
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@JsonComponent
public class MonetaryAmountJsonSerializer extends StdSerializer<javax.money.MonetaryAmount>
		implements ContextualSerializer {

	public MonetaryAmountJsonSerializer() {
		super(javax.money.MonetaryAmount.class);

	}

	@Override
	public void serialize(MonetaryAmount value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

		// in case this is used change logic accordingly
		throw new AssertionError("Not expecting the method to be used");
	}

	@Override
	public JsonSerializer<javax.money.MonetaryAmount> createContextual(SerializerProvider prov, BeanProperty property)
			throws JsonMappingException {
		JsonSerializer<javax.money.MonetaryAmount> ret = null;
		String name = property.getName();
		final JsonFormat.Value jsonFormat = findFormatOverrides(prov, property, handledType());
		if (jsonFormat != null) {
			String pattern = patternWithDefault(jsonFormat, "#,##0.00### ¤");
			Locale locale = localeWithDefault(jsonFormat);

			ret = new JsonSerializer<javax.money.MonetaryAmount>() {

				@Override
				public void serialize(MonetaryAmount value, JsonGenerator gen, SerializerProvider serializers)
						throws IOException {
					AmountFormatQueryBuilder builder = AmountFormatQueryBuilder.of(locale).set("pattern", pattern);
					// builder.set(CurrencyStyle.SYMBOL) should we use shape of explore enhancing
					final MonetaryAmountFormat format = MonetaryFormats.getAmountFormat(builder.build());
					String formatted = format.format(value);
					gen.writeString(formatted);

				}

			};

		} else {
			
			throw new AssertionError("Even if @JsonFormat is not used this condition is not expected");
		}

		return ret;
	}

	private Locale localeWithDefault(final JsonFormat.Value format) {
		Locale locale = format.getLocale();
		if (locale == null) {
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
