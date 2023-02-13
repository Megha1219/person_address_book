package com.test.addressbook.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.test.addressbook.model.Person;

/**
 * This class will test the repository layer of the project.
 * 
 * @author Megha Rampalli
 *
 */
@DataJpaTest
class AddressBookRepositoryTest {
	
	@Autowired
	private AddressBookRepository addressBookRepository;
	
	Person person1 =null; 
	
	/**
	 * Setting up the behavior before the test case runs.
	 * Mocked some data to perform CRUD operations.
	 */
	
	@BeforeEach 
	void setUp(){
		person1 = new Person("serial1","Megha","Rampalli","7438440194","UK");
		Person person2 = new Person("serial2","Megha1","Rampalli1","74384401945","UK");
		addressBookRepository.save(person1);
		addressBookRepository.save(person2);
	}

	/**
	 * Create person object to be save
	 * Set the person id manually, before saving
	 * Assert: Given person id should be equal to the returned person id
	 */
	@Test
	void testSaveMethod() {
		Person person = new Person("Megha1","Rampalli1","74384401945","UK");
		person.setPerson_id("serial");
		Person p = addressBookRepository.save(person);
		assertEquals("serial", p.getPerson_id());
	}
	
	/**
	 * Fetch all records from database
	 * Assert: List size should be greater than zero
	 */
	@Test
	void  testfindAllMethod() {
		List<Person> person_list = addressBookRepository.findAll();
		assertThat(person_list.size()).isGreaterThan(0);
	}
	
	/**
	 * Fetch the existing record from given id
	 * Set the values to the object that needs to be updated and save that object
	 * Assert: Given first name should be equal to the updated first name
	 */
	@Test
	void testUpdateMethod() {
		Person per = addressBookRepository.findById(person1.getPerson_id()).get();
		per.setFirstName("Samuel");
		Person per2 = addressBookRepository.save(per);
		assertEquals("Samuel", per2.getFirstName());
	}
	
	/**
	 * Deleted existing record of given person id
	 * Try to find the same record into the database
	 * Assert: person.isEmpty method will return true if the person is empty 
	 * 
	 */
	@Test
	void testDeleteMethod() {
		addressBookRepository.deleteById("serial1");
		Optional<Person> person = addressBookRepository.findById("serial1");
		assertEquals(true, person.isEmpty());
	}

}
