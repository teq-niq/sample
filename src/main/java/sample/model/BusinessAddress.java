package sample.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Schema(allOf = Address.class)
public class BusinessAddress extends Address{
@NotNull
@NotBlank
private String companyName;
}
