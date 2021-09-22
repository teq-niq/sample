package sample.model;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)

public class MyPartsDto extends MyBaseDto{
	//problem only with this parts field
	private MyBaseDto[] parts;
	private List<MyBaseDto> parts1;
	private Map<String, MyBaseDto> parts2;
	private String stringFieldInParts;
}
