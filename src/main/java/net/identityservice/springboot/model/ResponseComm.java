package net.identityservice.springboot.model;

public class ResponseComm {
	private boolean status;
	public ResponseComm() {
		super();
	}
	public ResponseComm(boolean status) {
		super();
		this.status=status;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
