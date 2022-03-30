package net.identityservice.springboot.controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import net.identityservice.springboot.model.Address;
import net.identityservice.springboot.model.ApiResponseCheckUser;
import net.identityservice.springboot.model.Bank;
import net.identityservice.springboot.model.Device;
import net.identityservice.springboot.model.RequestComm;
import net.identityservice.springboot.model.RequestEntityCheckUser;
import net.identityservice.springboot.model.RequestEntityEnterOTP;
import net.identityservice.springboot.model.RequestLos;
import net.identityservice.springboot.model.ResponseComm;
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

	@RequestMapping(value = "/postBankAndAddress", method = RequestMethod.POST)
	public ApiResponseCheckUser saveBankAndAddress(@RequestBody RequestLos rlos)
	{
		long userId=rlos.getUserId();
		
		//Bank Detail Save
		String bankAccountNumber=rlos.getBankAccountNumber();
		Bank bank=new Bank();
		bank.setUserId(userId);
		bank.setBankAccountNumber(bankAccountNumber);
		// api call
		final String uri1 = "http://localhost:8080/api/bank";
		RestTemplate restTemplate = new RestTemplate();
		String isResponse = restTemplate.postForObject(uri1,bank,String.class);
		
		//Address Detail Save
		String addressDetail=rlos.getAddress();
		Address address=new Address();
		address.setUserId(userId);
		address.setAddressDetail(addressDetail);
		// api call
		final String uri2 = "http://localhost:8080/api/address";
		restTemplate = new RestTemplate();
		isResponse = restTemplate.postForObject(uri2,address,String.class);
		
		return new ApiResponseCheckUser(userId, HttpStatus.OK.value(), "Data Stored.");
	}
	
	
	@PostMapping()
	public ApiResponseCheckUser saveUser(@RequestBody User user){
		int generatedOTP=userService.generateOTP();
		user.setOTP(generatedOTP);
		userService.saveUser(user);
		long uId=user.getId();
		HashMap<String,String> temp=new HashMap<String, String>();
		temp.put("userId", ""+user.getId());
		temp.put("otp", ""+generatedOTP);
		temp.put("mobileNumber", user.getMobile());
		final String uri1 = "http://10.70.4.178:8180/sendSMS";
		RestTemplate restTemplate = new RestTemplate();
		RequestComm request=new RequestComm();
		request.setDetails(temp);
//		String isResponse = restTemplate.postForObject(uri1,request,String.class);
		ResponseComm isResponse = restTemplate.postForObject(uri1,request,ResponseComm.class);
		if(isResponse.isStatus())
			return new ApiResponseCheckUser(uId, HttpStatus.OK.value(), "OTP sent.");
		return new ApiResponseCheckUser(uId, HttpStatus.BAD_REQUEST.value(), "OTP not sent.");
	}
	
	
	@RequestMapping(value = "/enterOTP", method = RequestMethod.POST)
	public ApiResponseCheckUser enterOTP(@RequestBody RequestEntityEnterOTP requestEntityEnterOTP) {
		long uId=requestEntityEnterOTP.getUserId();
		int OTP=requestEntityEnterOTP.getOTP();
		boolean isValid=userService.validateOTP(OTP, uId);
		if(isValid) {
			return new ApiResponseCheckUser(uId, HttpStatus.CREATED.value(), "Correct OTP! Account validated!");
		}
		else {
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
	   // api call
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

	
	@GetMapping("{id}")
	public HashMap<String, String> getUserById(@PathVariable("id") long userId,@RequestParam(value="serviceId",required=true) String serv)
			 {
		System.out.println(serv);
		System.out.println(userId);
		User user = userService.getUserById(userId);
		System.out.println(user.getAadhar());
		System.out.println(user);
		String name=user.getFirstName()+" "+user.getLastName();
		HashMap<String,String> temp=new HashMap<String, String>();
		
		
		if (serv.equals("cs")) {
			temp.put("name", user.getFirstName()+" "+user.getLastName());
			
			temp.put("mobileNumber", user.getMobile());
			temp.put("emailID", user.getEmail());
		}
		else if (serv.equals("los")){
			User obj = userService.getUserById(userId);
			LocalDate dob=obj.getDob();
			String formattedDob=dob.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			temp.put("fName", obj.getFirstName());
			temp.put("lName", obj.getLastName());
			temp.put("dob", formattedDob);
			temp.put("pan", obj.getPan());
			temp.put("adhaar", obj.getAadhar());
			temp.put("email", obj.getEmail());
			temp.put("mobile", obj.getMobile());
		}
		else
		{
			temp.put("Error", HttpStatus.BAD_REQUEST.toString());
		}
		return temp;
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