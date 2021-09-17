package sample.model;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sample.customvalidations.DateTimeType;
import sample.customvalidations.LocalDateTimeFormat;

@Data
@EqualsAndHashCode(callSuper=true)
@XmlRootElement(name = "person")

@XmlAccessorType(XmlAccessType.FIELD)
public class Person extends Human{

	private long id;

	@Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
	private String email;

	@Email()
	private String email1;

	@CreditCardNumber
	private String creditCardNumber;

	@LocalDateTimeFormat(pattern = "yyyyMMdd", dateTimeType = DateTimeType.Date, message = "Invalid dateTimeField Format. It Should be in yyyyMMdd format")
	private String registrationDate;
	
	@NotNull @Valid
	@XmlElement(name = "address")
	private Address address;
	

}
