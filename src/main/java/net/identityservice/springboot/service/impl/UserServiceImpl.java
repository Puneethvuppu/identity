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
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}else {
//			throw new ResourceNotFoundException("Employee", "Id", id);
//		}
		return userRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("User", "Id", id));
		
	}
	
	
	@Override
	public User updateUser(User user, long id) {
		
		// we need to check whether employee with given id is exist in DB or not
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
		   int randomnext=random.nextInt();
		   return randomnext;

	}

	@Override
	public List<Long> getUserIdByMobile(String mobile) {
		List<Long> userId=userRepository.findUserIdByMobile(mobile);
		return userId;
	}
	
}
