package sample.controller;

import jakarta.validation.Valid;

import sample.model.Person;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@PostMapping(path = "/person", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Person person(@Valid @RequestBody Person person) {
		return person;
	}
}
