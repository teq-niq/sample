package sample.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)

public class MyPartsDto extends MyBaseDto{
	//problem only with this parts field
	private MyBaseDto[] parts;
	
}
