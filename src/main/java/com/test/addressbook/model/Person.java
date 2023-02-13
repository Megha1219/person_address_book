package com.test.addressbook.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.boot.jaxb.mapping.internal.GenerationTypeMarshalling;

@Entity
public class Person {
	
	@Id
	private String person_id;
	private String firstName;
	private String lastName;
	private String phone;
	private String adderss;
	
	
	public Person() {
		super();
	}


	public Person( String person_id,String firstName, String lastName, String phone, String adderss) {
		super();
		this.person_id=person_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.adderss = adderss;
	}
	
	public Person(String firstName, String lastName, String phone, String adderss) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.adderss = adderss;
	}

	
	public String getPerson_id() {
		return person_id;
	}


	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAdderss() {
		return adderss;
	}


	public void setAdderss(String adderss) {
		this.adderss = adderss;
	}
	
	
	
	
	

}
