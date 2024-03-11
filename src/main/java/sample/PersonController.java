package sample;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@RestController
public class PersonController {
	@RequestMapping(path = "/person", method = RequestMethod.POST)
	@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(examples = {
			@ExampleObject(value = INVALID_REQUEST, name = "invalidRequest", description = "Invalid Request"),
			@ExampleObject(value = VALID_REQUEST, name = "validRequest", description = "Valid Request") }))
	public Person person(@Valid @RequestBody Person person) {
		return person;
	}

	private static final String VALID_REQUEST = """
			{
			  "id": 0,
			  "firstName": "string",
			  "lastName": "string",
			  "email": "abc@abc.com",
			  "email1": "abc@abc.com",
			  "age": 20,
			  "creditCardNumber": "4111111111111111"
			}""";

	private static final String INVALID_REQUEST = """
			{
			  "id": 0,
			  "firstName": "string",
			  "lastName": "string",
			  "email": "abcabc.com",
			  "email1": "abcabc.com",
			  "age": 17,
			  "creditCardNumber": "411111111111111"
			}""";
}
