package net.identityservice.springboot.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import net.identityservice.springboot.model.ResponseLos;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.identityservice.springboot.model.ApiResponseCheckUser;
import net.identityservice.springboot.model.Device;
import net.identityservice.springboot.model.RequestEntityCheckUser;
import net.identityservice.springboot.model.RequestEntityEnterOTP;
import net.identityservice.springboot.model.User;
import net.identityservice.springboot.service.UserService;
@RestController
@RequestMapping("/api/user")
public class UserController {
//	int generatedOTP=0;
	private UserService userService;
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	// build create employee REST API
	@PostMapping()
	public ApiResponseCheckUser saveUser(@RequestBody User user){
		
		
		int generatedOTP=userService.generateOTP();
		user.setOTP(generatedOTP);
		userService.saveUser(user);
		long uId=user.getId();
		// call CS/sendSMS
		System.out.println(generatedOTP);
		return new ApiResponseCheckUser(uId, HttpStatus.OK.value(), "OTP sent.");
		/*userService.saveUser(user);
		System.out.println(user.getId());
		//call C.S. /sendOTP with otp, mob.....
		System. out. println("Please enter a OTP: ");
		Scanner in=new Scanner(System.in);
		
		String mob=user.getMobile();
		
		int x=in. nextInt();
		userService.validateOTP(x,,user);
		in.close();*/
		/*user.getAadhar();
		user.getDob();
		user.getEmail();
		user.getFirstName();
		user.getLastName();user.getMobile();user.getPan();*/
		
		//if(user.isValidData())
			//return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
		
		
		//return new ResponseEntity<User>(HttpStatus.NOT_FOUND);	
		
	}
	
	@RequestMapping(value = "/enterOTP", method = RequestMethod.POST)
	public ApiResponseCheckUser enterOTP(@RequestBody RequestEntityEnterOTP requestEntityEnterOTP) {
		long uId=requestEntityEnterOTP.getUserId();
		System.out.println("1, " + uId);
		int OTP=requestEntityEnterOTP.getOTP();
		System.out.println("2, " + OTP);
//		System.out.println(userotp);
		//generatedOTP=userService.generateOTP();
//		user.setOTP(generatedOTP);
		boolean isValid=userService.validateOTP(OTP, uId);
		if(isValid) {
			return new ApiResponseCheckUser(uId, HttpStatus.CREATED.value(), "Correct OTP! Account validated!");
		}
		else {
			//enterOTP(user);
			return new ApiResponseCheckUser(uId, HttpStatus.NOT_FOUND.value(), "Incorrect OTP! Re-enter OTP & UserId.");
			
		}
		
	}
	
	@RequestMapping(value = "/checkUserExists", method = RequestMethod.POST)
	
	public ApiResponseCheckUser checkUserExists(@RequestBody RequestEntityCheckUser requestEntityCheckUser)
	{
		String mobile=requestEntityCheckUser.getMobile();
	   String deviceModel=requestEntityCheckUser.getDeviceModel();
	   String deviceId=requestEntityCheckUser.getDeviceId();
	   List<Long> userId=userService.getUserIdByMobile(mobile);
	   if(userId.isEmpty())
	      return new ApiResponseCheckUser((long)-1, HttpStatus.NOT_FOUND.value(), "No user found by this mobile number. Create account.");
	   
	   long uId=userId.get(0);
	   Device device=new Device();
	   device.setUserId(uId);
	   device.setDeviceModel(deviceModel);
	   device.setDeviceId(deviceId);
	   final String uri = "http://localhost:8080/api/device";
	   RestTemplate restTemplate = new RestTemplate();
	   String isResponse = restTemplate.postForObject(uri,device,String.class);
	   System.out.println(isResponse);
	   
		
   return new ApiResponseCheckUser(uId, HttpStatus.OK.value(), "User exists. New device found & added.");
	}
	
	// build get all employees REST API
	@GetMapping()
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	
	// build get employee by id REST API
	// http://localhost:8080/api/employees/1
	/*@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long userId){
		return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
	}*/
	
	@GetMapping("{id}")
	public ResponseEntity<Object> getUserById(@PathVariable("id") long userId,@RequestParam(value="serve",required=true) String serv)
			 {
		System.out.println(serv);
		System.out.println(userService.getUserById(userId));
		User user = userService.getUserById(userId);
		System.out.println(user);
		Map<String,String> mv = new HashMap<String,String>();
		mv.put("mobile", user.getMobile());
		mv.put("email", user.getEmail());
		if (serv.equals("cs")) {
			return new ResponseEntity<Object>(mv, HttpStatus.OK);
		}
		else {
			
		User obj = userService.getUserById(userId);
		ResponseLos ros = new ResponseLos();
		
		ros.setUserId(obj.getId());
		ros.setFname(obj.getFirstName());
		ros.setAdhaar(obj.getAadhar());
		ros.setDob(obj.getDob());
		ros.setLname(obj.getLastName());
		ros.setEmail(obj.getAadhar());
		ros.setMobile(obj.getMobile());
		ros.setPan(obj.getPan());
		return new ResponseEntity<Object>(ros,HttpStatus.OK);
	
	}
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