package net.identityservice.springboot.service;
import java.util.List;



import net.identityservice.springboot.model.User;

public interface UserService {
	User saveUser(User user);
	List<User> getAllUser();
	User getUserById(long id);
	User updateUser(User user, long id);
	void deleteUser(long id);

	int generateOTP();
	List<Long> getUserIdByMobile(String mobile);
	String validateOTP(int OTP, long uId);
}
