package net.identityservice.springboot.model;

public class ApiResponseCheckUser {
	private long userID;
    private int status;
    private String message;
    public ApiResponseCheckUser(long i,int v,String s) {
    	setUserID(i);
    	setStatus(v);
    	setMessage(s);
    }
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
}
