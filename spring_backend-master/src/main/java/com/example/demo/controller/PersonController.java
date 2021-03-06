package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

//import org.json.JSONObject;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@PostMapping("/create")
	public Person create(@RequestBody Object request) throws IOException {
		
		
		System.out.println("#################################################################################");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(request);
		JsonNode jsonNode = objectMapper.readTree(json);
		String fName = jsonNode.get("firstName").asText();
		String lName = jsonNode.get("lastName").asText();
		int age = jsonNode.get("age").asInt();
//		System.out.println("Request:"+color);
		Person p = personService.create(fName, lName, age);
		return p;
	}
	
	@RequestMapping("/get")
	public Person getPerson(@RequestParam String firstName) {
		return personService.getByFirstName(firstName);
	}
	@RequestMapping("/getAll")
	public List<Person> getAll(){
		return personService.getAll();
	}
	@RequestMapping("/update")
	public String update(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age) {
		Person p = personService.update(firstName, lastName, age);
		return p.toString();
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam String firstName) {
		personService.delete(firstName);
		return "Deleted "+firstName;
	}
	@RequestMapping ("/deleteAll")
	public String deleteAll() {
		personService.deleteAll();
		return "Deleted all records";
	}


	//Registering user to system
	@RequestMapping(value = "/Register",method = RequestMethod.POST)
	public  String Register(@RequestBody User userdata){
	    String responce=personService.RegisterUser(userdata);
		return responce;
	}

	//User Login
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public  String Login(@RequestBody Object data)
    {
        String responce=personService.Login(data);
        return responce;
    }
	
}
