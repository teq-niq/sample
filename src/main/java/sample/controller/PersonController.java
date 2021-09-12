package sample.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import sample.model.Address;
import sample.model.Employee;
import sample.model.Human;
import sample.model.Person;

@RestController
public class PersonController {

	@PostMapping(path = "/person",
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces= MediaType.APPLICATION_JSON_VALUE)
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = @Content(
			schema =@Schema(oneOf = {Person.class, Employee.class})))
	@ApiResponses({
		@ApiResponse(responseCode = "200",content = {
				@Content( 
						schema =@Schema(oneOf = {Person.class, Employee.class}))})})
	
	
	public ResponseEntity<Human> person(@Valid @RequestBody Human human) {
		System.out.println("human="+human);
		if(human instanceof Person)
		{
			Person person=(Person) human;
			Address address = person.getAddress();
			System.out.println("address="+address);
		}
		
		return new ResponseEntity<Human>(human, HttpStatus.OK);
	}
}
