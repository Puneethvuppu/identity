package net.identityservice.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="address")

public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userAddressid;
	
	@Column(name="userId")
	private long userId;
	
	@Column(name="House")
	private String houseNo;
	
	@Column(name="Street")
	private String streetName;
	
	@Column(name="City")
	private String cityName;
	
	@Column(name="State")
	private String stateName;
	
	@Column(name="Country")
	private String countryName;
	
	@Column(name="PIN")
	private int pincode;

	

	public long getUserAddressid() {
		return userAddressid;
	}

	public void setUserAddressid(long userAddressid) {
		this.userAddressid = userAddressid;
	}

	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	
	
}
