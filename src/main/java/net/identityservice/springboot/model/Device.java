package net.identityservice.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userDeviceDetails")
public class Device {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userDeviceId;
	
	@Column(name="device_model",nullable=false,length=20)
	private String deviceModel;
	
	private String deviceId;
	public long getUserDeviceId() {
		return userDeviceId;
	}
	public void setUserDeviceId(long userDeviceId) {
		this.userDeviceId = userDeviceId;
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
	
}
