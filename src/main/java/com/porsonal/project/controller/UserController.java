package com.porsonal.project.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.porsonal.project.customException.CustomException;
import com.porsonal.project.user.User;

@RestController
@RequestMapping("/users")
public class UserController {
	
	Map<String, User> users;
	
	@GetMapping()
	//the required attribute does not work with primitive values because they do not have null as default value.
	public String getUsers(@RequestParam(value="page", defaultValue = "2") int page,
			@RequestParam(value="limit", defaultValue = "35") int limit, 
			@RequestParam(value="sort", required=false) String sort) {
		return limit + " user from page " + page + " was returned";
	}
	
	@GetMapping(path="/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<User> getUser(@PathVariable String userId) {
		
//		this string was used to create NullPointerException so that I can test the ExceptionsHandler class.
//		String nullString = null;
//		int lenght = nullString.length();
		
		if(true) throw new CustomException("this is a custom exception");
		
		User user = new User();
		if(userId != null) {
			user = users.get(userId);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
				consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//If you don't provide the @Valid here your validation annotation will work in your model class.
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User storedUser = user;
		//to create a unique string id.
		String id = UUID.randomUUID().toString();
		storedUser.setUserID(id);
		users = new HashMap<>();
		users.put(id, storedUser);
		return new ResponseEntity<>(storedUser, HttpStatus.OK);
	}
	
	@PutMapping(path="/{userId}")
	public String ModifyUser(@PathVariable String userId, @RequestBody User user) {
		User modifiedUser = users.get(userId);
		modifiedUser.setFirstname(user.getFirstname());
		modifiedUser.setLastname(user.getLastname());
		users.put(userId, modifiedUser);
		return "The user was modifed";
	}
	
	@DeleteMapping(path="/{userId}")
	public String DeleteUser(@PathVariable String userId) {
		users.remove(userId);
		return "The user was removed";
	}

}
