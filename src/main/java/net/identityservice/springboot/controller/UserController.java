package net.identityservice.springboot.controller;
import java.util.List;

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
import net.identityservice.springboot.model.User;
import net.identityservice.springboot.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	// build create employee REST API
	@PostMapping()
	public ResponseEntity<User> saveUser(@RequestBody User user){
	
		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
	}
	
	// build get all employees REST API
	@GetMapping()
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	
	// build get employee by id REST API
	// http://localhost:8080/api/employees/1
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long userId){
		return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
	}
	
	// build update employee REST API
	// http://localhost:8080/api/employees/1
	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id
												  ,@RequestBody User user){
		return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
	}
	
	// build delete employee REST API
	// http://localhost:8080/api/employees/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
		
		// delete employee from DB
		userService.deleteUser(id);
		
		return new ResponseEntity<String>("User deleted successfully!.", HttpStatus.OK);
	}
	
}