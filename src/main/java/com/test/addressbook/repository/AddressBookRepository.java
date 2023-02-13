package com.test.addressbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.addressbook.model.Person;

/**
 * This interface will provide methods to perform certain operations.
 * For example: save(), findByAll(), findById(), deleteById() and so on.
 *
 */
public interface AddressBookRepository extends JpaRepository<Person, String>{

}
