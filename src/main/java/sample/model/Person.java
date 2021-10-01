package sample.model;

import java.time.LocalDate;

import javax.money.MonetaryAmount;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Currency;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;
import sample.customvalidations.DateTimeType;
import sample.customvalidations.LocalDateTimeFormat;

@Data
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

	private long id;

	@Size(min = 2)
	private String firstName;

	@NotNull
	@NotBlank
	private String lastName;

	@Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
	private String email;

	@Email()
	private String email1;

	@Min(18)
	@Max(30)
	private int age;

	@CreditCardNumber
	private String creditCardNumber;

	@LocalDateTimeFormat(pattern = "yyyyMMdd", dateTimeType = DateTimeType.Date, message = "Invalid dateTimeField Format. It Should be in yyyyMMdd format")
	private String registrationDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate birthday;
	
	@Currency({"USD", "EUR"}) 
	@JsonFormat(pattern="#,##0.00### ¤")

	private MonetaryAmount billAmount1;
	
	@Currency({"USD"}) 
	@JsonFormat(pattern="#,##0.00### ¤" )

	private MonetaryAmount billAmount2;
	
	@Currency("EUR") 
	@JsonFormat(pattern="#,##0.00### ¤")

	private MonetaryAmount billAmount3;

}
