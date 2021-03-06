package net.identityservice.springboot.model;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="user_detail")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_name",nullable = false,length=50)
	private String firstName;
	
	@Column(name = "last_name",nullable = false,length=50)
	private String lastName;
	
	@Column(name = "email",nullable = false,length=150)
	private String email;
	
	@Column(name = "pan",nullable = false,length=10)
	private String pan;
	
	@Column(name="aadhar",nullable = false,length=12)
	private String aadhar;
	
	@Column(name="mobile_no",nullable = false,length=10)
	private String mobile;
	
	@Column(name="dob",nullable = false)
	@JsonFormat(pattern="yyyy/MM/dd")
    private LocalDate dob;
	
	@Column(name="valid_data",nullable = false)
	boolean validData=false;
	
	@Column(name="otp")
	private int OTP;

	@Column(name="date_time_created",nullable = false)
	LocalDateTime now = LocalDateTime.now();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public boolean isValidData() {
		return validData;
	}

	public void setValidData(boolean validData) {
		this.validData = validData;
	}

	public int getOTP() {
		return OTP;
	}

	public void setOTP(int oTP) {
		OTP = oTP;
	}
	
}