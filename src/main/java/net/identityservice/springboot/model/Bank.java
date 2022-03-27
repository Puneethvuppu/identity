package net.identityservice.springboot.model;

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
	
	@Column(name="Account_No")
	private long bankAccountNumber;

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

	public long getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(long bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	
	
}
