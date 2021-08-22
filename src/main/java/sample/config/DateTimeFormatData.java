package sample.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class DateTimeFormatData {

	private String pattern;

	private String dateTimeType;

}
