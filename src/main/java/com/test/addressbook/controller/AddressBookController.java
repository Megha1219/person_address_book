package com.test.addressbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.addressbook.model.Person;
import com.test.addressbook.services.AddressBookService;

/**
 * This class handles the HTTP request depending on matching properties or HTTP methods that are coming from client.
 * Calls the business logic functions and return a response to the client.
 * @author Megha Rampalli
 *
 */
@RestController
@RequestMapping("/person")
public class AddressBookController {

	@Autowired
	private AddressBookService addressBookService;

	/**
	 * Add new person record to address book.
	 * @param person person detail to save 
	 * @return newly created person record with CREATED status 
	 */
	@PostMapping
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		Person person1= addressBookService.createPerson(person);
		return ResponseEntity.status(HttpStatus.CREATED).body(person1);
	}
	 
	/**
	 * Get all records from address book.
	 * @return list of person details
	 */
	@GetMapping
	public ResponseEntity<List<Person>> getAllPersonList(){
		List<Person> person_list = addressBookService.getAllPersonList();
		return ResponseEntity.status(HttpStatus.OK).body(person_list);
	}
	
	/**
	 * Update the existing record of person to the address book.
	 * @param person person detail to updated
	 * @return list of updated person with OK status 
	 */
	@PutMapping
	public ResponseEntity<Person> updatePersonDetail(@RequestBody Person person){
		Person person1 = addressBookService.updatePersonDetails(person);
		return ResponseEntity.status(HttpStatus.OK).body(person1);
	}
	
	/**
	 * Delete the existing record of person from the address book.
	 * @param person_id id of person details to be deleted
	 * @return void with NO_CONTENT status
	 */
	@DeleteMapping("/{person_id}")
	public ResponseEntity<Void> deletePersonDetail(@PathVariable String person_id){
		addressBookService.deletePerson(person_id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
}
