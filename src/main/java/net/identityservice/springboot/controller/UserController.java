package net.identityservice.springboot.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.identityservice.springboot.model.ApiResponseCheckUser;
import net.identityservice.springboot.model.Device;
import net.identityservice.springboot.model.RequestEntityCheckUser;
import net.identityservice.springboot.model.User;
import net.identityservice.springboot.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private UserService userService;
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	// build create employee REST API
	@PostMapping()
	public ResponseEntity<User> saveUser(@RequestBody User user){
		int otp=userService.generateOTP();
		
		System.out.println(otp);
		userService.saveUser(user);
		System.out.println(user.getId());
		//call C.S. /sendOTP with otp, mob.....
		System. out. println("Please enter a OTP: ");
		Scanner in=new Scanner(System.in);
		
		int x=in. nextInt();
		in.close();
		
		
		if(otp==x)
			return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
		return new ResponseEntity<User>(userService.saveUser(user),HttpStatus.NOT_FOUND);	
		
	}

	@RequestMapping(value = "/checkUserExists", method = RequestMethod.POST)
	
	public ApiResponseCheckUser checkUserExists(@RequestBody RequestEntityCheckUser requestEntityCheckUser)
	{
		String mobile=requestEntityCheckUser.getMobile();
	   String deviceModel=requestEntityCheckUser.getDeviceModel();
	   String deviceId=requestEntityCheckUser.getDeviceId();
	   List<Long> userId=userService.getUserIdByMobile(mobile);
	   if(userId.isEmpty())
	      return new ApiResponseCheckUser((long)-1, HttpStatus.NOT_FOUND.value(), "No user found by this mobile number.");
	   
	   long uId=userId.get(0);
	   Device device=new Device();
	   device.setUserId(uId);
	   device.setDeviceModel(deviceModel);
	   device.setDeviceId(deviceId);
	   final String uri = "http://localhost:8080/api/device";
	   RestTemplate restTemplate = new RestTemplate();
	   String isResponse = restTemplate.postForObject(uri,device,String.class);
	   System.out.println(isResponse);
	   
		
	   return new ApiResponseCheckUser(uId, HttpStatus.OK.value(), "New device found & added.");
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