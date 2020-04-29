package sample;

public class DateTimeFormatData {
	
	private String pattern;
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public DateTimeFormatData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DateTimeFormatData(String pattern, String dateTimeType) {
		super();
		this.pattern = pattern;
		this.dateTimeType = dateTimeType;
	}
	public String getDateTimeType() {
		return dateTimeType;
	}
	public void setDateTimeType(String dateTimeType) {
		this.dateTimeType = dateTimeType;
	}
	private String dateTimeType;

}
