package com.test.addressbook.services;

import java.util.List;

import com.test.addressbook.model.Person;

public interface AddressBookService {

	public Person createPerson(Person person);

	public List<Person> getAllPersonList();

	public Person updatePersonDetails(Person person);

	public void deletePerson(String person_id);

}
