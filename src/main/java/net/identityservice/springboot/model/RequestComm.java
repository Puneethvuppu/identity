package net.identityservice.springboot.model;
import java.util.HashMap;
public class RequestComm {
    private String requestType="SUOTP";
    private HashMap<String,String> details;

    public String getRequestType() {
		return requestType;
	}

	public HashMap<String, String> getDetails() {
		return details;
	}
	public void setDetails(HashMap<String, String> details) {
		this.details = details;
    }
}