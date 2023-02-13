package com.test.addressbook.servicesImpl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.addressbook.model.Person;
import com.test.addressbook.repository.AddressBookRepository;
import com.test.addressbook.services.AddressBookService;

/**
 * This class contains the business logic.
 * Also use JPA repository methods to perform CRUD operations.
 * @author Megha Rampalli
 *
 */
@Service
public class AddressBookServiceImpl implements AddressBookService {
	
	@Autowired
	private AddressBookRepository addressBookRepository; 
	
	/**
	 * Generate unique random id of string and save the person detail.
	 * @param person person detail to save
	 * @return newly created person record
	 */
	public Person createPerson(Person person) {
		UUID person_id = UUID.randomUUID();
		person.setPerson_id(person_id.toString());
		 return addressBookRepository.save(person);
	}
	
	
	/**
	 * Get all records from address book.
	 * @return list of person details
	 */
	public List<Person> getAllPersonList(){
		return addressBookRepository.findAll();
	}


	/**
	 * Fetch existing record using person id from the given person
	 * Get all the values from given person and set values to the fetched object
	 * And call save method to update the person object with given values
	 * @param person detail to updated
	 * @return list of updated person
	 */
	@Override
	public Person updatePersonDetails(Person person) {
		Person person1 =addressBookRepository.findById(person.getPerson_id()).get();
		person1.setFirstName(person.getFirstName());
		person1.setLastName(person.getLastName());
		person1.setPhone(person.getPhone());
		person1.setAdderss(person.getAdderss());
		Person updated_person=addressBookRepository.save(person1);
		return updated_person;
	}

	/**
	 * Delete the existing record of given person_id
	 * @param person_id id of person details to be deleted
	 * @return void 
	 */
	@Override
	public void deletePerson(String person_id) {
		addressBookRepository.deleteById(person_id);
		
	}
	
	
	
	
	

}
