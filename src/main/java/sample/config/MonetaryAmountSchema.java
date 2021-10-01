package sample.config;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;

import org.javamoney.moneta.Money;
import org.javamoney.moneta.format.CurrencyStyle;
import org.springframework.context.i18n.LocaleContextHolder;

import io.swagger.v3.oas.models.media.Schema;

public class MonetaryAmountSchema extends Schema<MonetaryAmount> {

	public MonetaryAmountSchema() {
		super("string", "monetaryAmount");
		
	}
	
	 @Override
	    public MonetaryAmountSchema type(String type) {
	        super.setType(type);
	        return this;
	    }

	    @Override
	    public MonetaryAmountSchema format(String format) {
	        super.setFormat(format);
	        return this;
	    }

	    public MonetaryAmountSchema _default(MonetaryAmount _default) {
	        super.setDefault(_default);
	        return this;
	    }

	    @Override
	    protected MonetaryAmount cast(Object value) {
	        if (value != null) {
	            try {
	                if (value instanceof MonetaryAmount) {
	                    return (MonetaryAmount) value;
	                } else if (value instanceof String) {
	                	Locale locale = LocaleContextHolder.getLocale();
	                	final MonetaryAmountFormat format = MonetaryFormats.getAmountFormat(
	            	            AmountFormatQueryBuilder.of(locale)
	            	                    .set(CurrencyStyle.SYMBOL)
	            	                    .set("pattern", "#,##0.00### ¤")
	            	                    .build()
	            	    );
	                	return format.parse((String)value);
	                }
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	        }
	        return null;
	    }

	    public MonetaryAmountSchema addEnumItem(MonetaryAmount _enumItem) {
	        super.addEnumItemObject(_enumItem);
	        return this;
	    }
	
	 @Override
	    public boolean equals(java.lang.Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (o == null || getClass() != o.getClass()) {
	            return false;
	        }
	        return super.equals(o);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(super.hashCode());
	    }

	    @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("class MonetaryAmountSchema {\n");
	        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
	        sb.append("}");
	        return sb.toString();
	    }

}
