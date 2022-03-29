package net.identityservice.springboot.model;

import java.time.LocalDateTime;

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
	
	@Column(name="addressDetails")
	private String addressDetail;
	
	@Column(name="date_time_created")
	private LocalDateTime created=LocalDateTime.now();
	

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

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetails) {
		this.addressDetail = addressDetails;
	}
	public boolean isEmpty()
	{
	   return this.getUserAddressid()==0;
	}
	
}
