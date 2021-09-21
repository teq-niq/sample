package sample.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(discriminatorProperty = "operator", discriminatorMapping = {
		 @DiscriminatorMapping(value = "and", schema = MyAndDto.class),
     @DiscriminatorMapping(value = "or", schema = MyOrDto.class),
     @DiscriminatorMapping(value = "equals", schema = MyEqualsDto.class)
})

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, 
include = JsonTypeInfo.As.PROPERTY, 
property = "operator",  visible = true)
@JsonSubTypes({ 
	@JsonSubTypes.Type(value = MyAndDto.class, name = "and"),
        @JsonSubTypes.Type(value = MyOrDto.class, name = "or"),
        @JsonSubTypes.Type(value = MyEqualsDto.class, name = "equals") })
public class MyBaseDto {
	private Operator operator;
}
