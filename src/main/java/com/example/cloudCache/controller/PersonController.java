package com.example.cloudCache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cloudCache.entity.PersonEntity;
import com.example.cloudCache.service.PersonService;

@RestController("/person")
public class PersonController {

	@Autowired
	PersonService personService;
	
	@GetMapping("/{id}")
	public ResponseEntity<PersonEntity> findPersonById(@PathVariable Long id) {
		PersonEntity person = this.personService.findById(id);
		if (person != null) {
			return new ResponseEntity<PersonEntity>(this.personService.findById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<PersonEntity>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<PersonEntity>> findAllPeople() {
		return new ResponseEntity<List<PersonEntity>>(this.personService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<PersonEntity> createPerson(@RequestBody PersonEntity person) {
		this.personService.createPerson(person);
		return new ResponseEntity<PersonEntity>(HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<PersonEntity> updatePerson(@RequestBody PersonEntity person) {
		this.personService.updatePerson(person);
		return new ResponseEntity<PersonEntity>(HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<PersonEntity> deletePersonByID(Long id) {
		this.personService.deletePersonById(id);
		return new ResponseEntity<PersonEntity>(HttpStatus.OK);
	}
}
