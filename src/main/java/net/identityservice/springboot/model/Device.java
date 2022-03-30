package net.identityservice.springboot.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="device")
public class Device {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userDeviceDetailId;
	
	@Column(name="userId")
	private long userId;
	
	@Column(name="device_model",nullable=false,length=20)
	private String deviceModel;
	
	@Column(name="deviceId")
	private String deviceId;
	
	@Column(name="date_time_created")
	LocalDateTime now = LocalDateTime.now();
	
	public long getUserDeviceDetailId() {
		return userDeviceDetailId;
	}
	public void setUserDeviceDetailId(long userDeviceDetailId) {
		this.userDeviceDetailId = userDeviceDetailId;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
	
}
