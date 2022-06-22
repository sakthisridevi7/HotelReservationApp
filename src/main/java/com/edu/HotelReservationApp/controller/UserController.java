package com.edu.HotelReservationApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.edu.HotelReservationApp.entity.User;
import com.edu.HotelReservationApp.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user){
		return new ResponseEntity<User>(userService.addUser(user),HttpStatus.CREATED);
	}
	@GetMapping
	public List<User> getUserList(){
		return userService.getUserList();
	}
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable("userId")long userId) {
		return userService.getUserById(userId);
	}
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable("userId")long userId, @RequestBody User user) {
		return userService.updateUser(userId,user);
	}
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId")long userId){
		return new ResponseEntity<String>(userService.deleteUser(userId),HttpStatus.OK);
	}
	@GetMapping("/GetByFirstName/{firstName}")
	public List<User> getUserByFirstName(@PathVariable("firstName") String firstName){
		return userService.getUserByFirstName(firstName);
	}
	@GetMapping("/GetByLastName/{lastName}")
	public List<User> getUserByLastName(@PathVariable("lastName") String lastName){
		return userService.getUserByLastName(lastName);
	}
	@GetMapping("/GetUserByFullName/{firstName}/{lastName}")
	public List<User> getUserByFullName(@PathVariable("firstName")String firstName,@PathVariable("lastName") String lastName){
		return userService.getUserByFullName(firstName , lastName);
	}
	
	@GetMapping("/GetByEmailId/{emailId}")
	public User getUserByEmailId(@PathVariable("emailId")String emailId){
		return userService.getUserByEmailId(emailId);
	}
	@GetMapping("/GetByUsername/{username}")
	public User getUserByUserName(@PathVariable("username")String username) {
		return userService.getUserByUsername(username);
	}

}
