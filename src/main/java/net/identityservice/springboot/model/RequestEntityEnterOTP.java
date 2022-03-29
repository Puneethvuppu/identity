package net.identityservice.springboot.model;

public class RequestEntityEnterOTP {
	long userId;
	int OTP;
	
	public long getUserId()
	{
		return this.userId;
	}
	public void setUserId(long userId)
	{
		this.userId=userId;
	}
	
	public int getOTP()
	{
		return this.OTP;
	}
	public void setOTP(int OTP)
	{
		this.OTP=OTP;
	}
	
}
