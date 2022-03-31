package net.identityservice.springboot.model;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="address_detail")

public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="userId",nullable=false)
	private long userId;
	
	@Column(name="addressDetails",nullable=false,length=150)
	private String addressDetail;
	
	@Column(name="date_time_created",nullable=false)
	private LocalDateTime created=LocalDateTime.now();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	   return this.getId()==0;
	}
	
}
