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
import sample.model.MyAndDto;
import sample.model.MyBaseDto;
import sample.model.MyEqualsDto;
import sample.model.MyOrDto;

@RestController
public class SampleController {

	@PostMapping(path = "/sample",
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces= MediaType.APPLICATION_JSON_VALUE)
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = @Content(
			schema =@Schema(oneOf = {MyOrDto.class, MyAndDto.class, MyEqualsDto.class})))
	@ApiResponses({
		@ApiResponse(responseCode = "200",content = {
				@Content( 
						schema =@Schema(oneOf = {MyOrDto.class, MyAndDto.class,MyEqualsDto.class}))})})
	
	
	public ResponseEntity<MyBaseDto> person(@Valid @RequestBody MyBaseDto dto) {
		System.out.println("dto="+dto);
		
		
		return new ResponseEntity<MyBaseDto>(dto, HttpStatus.OK);
	}
}
