package sample.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Schema(allOf = MyBaseDto.class)
public class MyEqualsDto   extends MyBaseDto{
	private String field;
	private String value;
}
