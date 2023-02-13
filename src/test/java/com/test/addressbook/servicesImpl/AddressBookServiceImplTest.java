package com.test.addressbook.servicesImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.addressbook.model.Person;
import com.test.addressbook.repository.AddressBookRepository;

/**
 * This class will test the service layer of the project.
 * Will mock the AddressBookRepository
 * @author megha.karampuri
 *
 */
@ExtendWith(MockitoExtension.class)
class AddressBookServiceImplTest {
	
	@InjectMocks
	private AddressBookServiceImpl addreddBookService ;
	 
	@Mock
	private AddressBookRepository addressBookRepository; 
	
	List<Person> person_list =null;
	Person person=null;
	 
	/**
	 * Setting up the behavior before the test case runs.
	 */
	@BeforeEach
	void setUp() {
		person_list = new ArrayList<>();
		person = new Person("serial1","Megha","Rampalli","7438440194","UK");
		Person person1 = new Person("serial2","Megha1","Rampalli1","74384401945","UK");
		
		person_list.add(person);
		person_list.add(person1);
	}

	/**
	 *
	 * Set the person id manually, before saving
	 * Stub person object when calling save method
	 * Call createPerson method from addreddBookService class
	 * Assert:  Returned object should not be null
	 * 			Given person first name should be equal to the returned person first name
	 */
	@Test
	void testCreatPerson_returnPerson() {
		Person person = new Person("Sam","Rampalli","8765432456","UK");
		person.setPerson_id("serial1");  
		when(addressBookRepository.save(any())).thenReturn(person);
		Person person2 =addreddBookService.createPerson(person);
		assertNotNull(person2);
		assertEquals("Sam", person2.getFirstName());
	}
	
	/**
	 * Stub list of person object when calling findAll method
	 * Call getAllPersonList method from addreddBookService class
	 * Assert: Returned list of size should be greater that zero
	 * 			
	 */
	@Test
	void testGetAllPersonDetails() {
		
		when(addressBookRepository.findAll()).thenReturn(person_list);
		List<Person> per = addreddBookService.getAllPersonList();
		assertThat(per.size()).isGreaterThan(0);
	}
	
	/**
	 * Stub person object of type Optional as findById return Optional object
	 * Set values to be updated
	 * stub person object when calling save method
	 * Call updatePersonDetails method from addreddBookService class
	 * Assert : Given first name should be equal to the updated first name
	 */
	@Test
	void testUpdatePersonDetails() {
	
		Optional OptionalPerson = Optional.of(person);
		when(addressBookRepository.findById(anyString())).thenReturn(OptionalPerson);
		//values to be updated
		person.setFirstName("Samuel");
		person.setLastName("rampalli");
		//return updated person 
		when(addressBookRepository.save(any())).thenReturn(person);
		Person per = addreddBookService.updatePersonDetails(person);
		assertEquals("Samuel", per.getFirstName());
		
	}
	
	/**
	 * Call deletePerson method from addreddBookService class with argument matcher parameter
	 * verify deleteById methods only call once
	 */
	@Test
	void testDeletePersonDetails() {
		addreddBookService.deletePerson(anyString());
		verify(addressBookRepository,times(1)).deleteById(anyString());
		
	}

}
