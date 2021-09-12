package sample.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
@Schema(
        type = "object",
        subTypes = {Employee.class, Person.class}
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "humanType", visible = true)

@JsonSubTypes({
	
    @JsonSubTypes.Type(value = Person.class, name="P"),
    @JsonSubTypes.Type(value = Employee.class, name = "E")
})
public abstract class Human {
	@Size(min = 2)
	private String firstName;

	@NotNull
	@NotBlank
	private String lastName;
	
	@Min(18)
	@Max(30)
	private int age;
	
	private HumanType humanType;
}
