package net.identityservice.springboot.service.impl;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import net.identityservice.springboot.exception.ResourceNotFoundException;
import net.identityservice.springboot.model.User;
import net.identityservice.springboot.repository.UserRepository;
import net.identityservice.springboot.service.UserService;
@Component
@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("User", "Id", id));
		
	}
	
	
	@Override
	public User updateUser(User user, long id) {

		User existingUser = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User", "Id", id)); 
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		// save existing employee to DB
		userRepository.save(existingUser);
		return existingUser;
	}

	@Override
	public void deleteUser(long id) {
		
		// check whether a employee exist in a DB or not
		userRepository.findById(id).orElseThrow(() -> 
								new ResourceNotFoundException("User", "Id", id));
		userRepository.deleteById(id);
	}

	@Override
	public int generateOTP() {
		Random random = new Random();
		int num = random.nextInt(900000) + 100000;
		   return num;

	}

	@Override
	public List<Long> getUserIdByMobile(String mobile) {
		List<Long> userId=userRepository.findUserIdByMobile(mobile);
		return userId;
	}

	@Override
	public String validateOTP(int OTP, long uId) {
		try{
			User user=getUserById(uId);
			int generatedOTP=user.getOTP();
			if(generatedOTP==OTP)
			{
				userRepository.setValidDataById(uId);
				return "true";
			}
			else{
				return "false";
			}
		}
		catch(Exception e){
			return "User Not Found";
		}

	}
}
