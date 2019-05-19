package com.example.demo.service;

import java.net.PortUnreachableException;
import java.util.List;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private UserRepository userRepository;

	//Create operation
	public Person create(String firstName,String lastName, int age) {

		return personRepository.save(new Person(firstName, lastName, age));
	}

	//Retrieve operation
	public List<Person> getAll(){

		return personRepository.findAll();
	}

	public Person getByFirstName(String firstName) {

		return personRepository.findByFirstName(firstName);
	}
	//Update operation
	public Person update(String firstName, String lastName, int age) {
		Person p = personRepository.findByFirstName(firstName);
		p.setLastName(lastName);
		p.setAge(age);
		return personRepository.save(p);
	}
	//Delete operation
	public void deleteAll() {
		personRepository.deleteAll();
	}
	public void delete(String firstName) {
		Person p = personRepository.findByFirstName(firstName);
		personRepository.delete(p);
	}

	//Register User
	public  String RegisterUser(User userdata)
	{
		userRepository.save(userdata);
		return "OK";
	}

	//Login user
	public String Login(Object userdata)
	{
		return userdata.toString();
	}


}
