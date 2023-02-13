package com.test.addressbook.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.addressbook.model.Person;
import com.test.addressbook.services.AddressBookService;

/**
 * This class will test controller layer of the project with different API end points.
 * And mock the AddressBookService class.
 * Use ObjectMapper to create JSON object.
 * 
 * @author megha.karampuri
 *
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(AddressBookController.class)
class AddressBookControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	private AddressBookService addressBookService;
	
	@Autowired
	private ObjectMapper mapper;
	
	List<Person> person_list =null;
	Person person = null;
	
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
	 * Given object of person when calling createPerson method
	 * Perform a POST request with
	 * 		@param accept json object of person
	 * 	Expect HTTPstatus CREATED after success
	 * Assert: response should be equal to the given json object of person
	 * 		
	 * @throws Exception
	 */
	@WithMockUser(roles="ADMIN")
	@Test
	public void testCreatePerson_withPostRequest() throws Exception {
		Person person = new Person("User","Rampalli","7438440194","UK");
		when(addressBookService.createPerson(any())).thenReturn(person);
		
		MockHttpServletResponse response =  mvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(person)))
		.andExpect(status().isCreated()).andReturn().getResponse();
		
		assertThat(response.getContentAsString()).isEqualTo(mapper.writeValueAsString(person));
	}
	
	/**
	 * Given list of person when calling getAllPersonList method
	 * Perform a GET request and expect HTTPstatus OK after success
	 * Assert: response length should be greater than zero
	 * 
	 * @throws Exception
	 */
	@WithMockUser(roles="NORMAL")
	@Test
	public void testGetAllPersonDetails_whenGetRequest() throws Exception {
		when(addressBookService.getAllPersonList()).thenReturn(person_list);
		
		MockHttpServletResponse response = mvc.perform(get("/person")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andReturn().getResponse();
		
		assertThat(response.getContentAsString().length()).isGreaterThan(0);
	}
	
	/**
	 * Given object of person when calling updatePersonDetails method
	 * Perform a PUT request with
	 * 		@param accept json object of person to be updated
	 * and expect HTTPstatus OK after success
	 * Assert:  response should be equal to the given json object of person
	 * 
	 * @throws Exception
	 */
	@WithMockUser(roles="ADMIN")
	@Test
	public void testUpdatePersonDetails() throws Exception {
		when(addressBookService.updatePersonDetails(any())).thenReturn(person);
		
		MockHttpServletResponse response =  mvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(person)))
		.andExpect(status().isOk()).andReturn().getResponse();
		
		assertThat(response.getContentAsString()).isEqualTo(mapper.writeValueAsString(person));
	}
	
	/**
	 * Perform DELETE request with 
	 * 		path @param person id
	 * Expect HttpStaus NO CONTENT after success
	 * Verify deletePerson method is called only once.
	 * Assert: response should be empty
	 * 
	 * @throws Exception
	 */
	@WithMockUser(roles="ADMIN")
	@Test
	public void testDeletePerson() throws Exception {
		
		MockHttpServletResponse response=  mvc.perform(delete("/person/2")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent()).andReturn().getResponse();
		
		verify(addressBookService,times(1)).deletePerson(anyString());
		
		assertThat(response.getContentAsString()).isEmpty();
		
	}
	
	

}
