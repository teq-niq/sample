package sample.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import sample.customvalidations.DateTimeType;
import sample.customvalidations.LocalDateTimeFormat;

@Data


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "addressType", visible = true)

@JsonSubTypes({@JsonSubTypes.Type(value = HomeAddress.class, name = "H"), @JsonSubTypes.Type(value = BusinessAddress.class, name = "B")})
@Schema(discriminatorProperty = "addressType", discriminatorMapping = {
		 @DiscriminatorMapping(value = "H", schema = HomeAddress.class),
        @DiscriminatorMapping(value = "B", schema = BusinessAddress.class)
})
public abstract class Address {
	
	@NotNull
	@NotBlank
	private String line1;
	
	private String line2;
	
	@NotNull
	@NotBlank
	private String city;
	
	@NotNull
	@NotBlank
	private String state;
	
	@NotNull
	@NotBlank
	private String country;
	
	@LocalDateTimeFormat(pattern = "yyyyMMdd", dateTimeType = DateTimeType.Date, message = "Invalid dateTimeField Format. It Should be in yyyyMMdd format")
	private String atAddressSince;
	
	private AddressType addressType;

}
