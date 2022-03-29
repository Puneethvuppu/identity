package net.identityservice.springboot.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="bank")
public class Bank {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userAccountId;
	
	@Column(name="userId")
	private long userId;
	
	@Column(name="account_No")
	private String bankAccountNumber;
	
	@Column(name="date_time_created")
	private LocalDateTime created=LocalDateTime.now();

	public long getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(long userAccountId) {
		this.userAccountId = userAccountId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public boolean isEmpty()
	{
		return this.getUserAccountId()==0; 
//				&& this.getBankAccountNumber()==null && this.getUserId()==0); 
	}
	
}
