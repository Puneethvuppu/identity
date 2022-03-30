package net.identityservice.springboot.model;

public class RequestEntityEnterOTP {
	long userId;
	int OTP;
	
	public RequestEntityEnterOTP(long userId, int OTP)
	{
		setUserId(userId);
		setOTP(OTP);
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getOTP() {
		return OTP;
	}
	public void setOTP(int OTP) {
		this.OTP = OTP;
	}
	
}
