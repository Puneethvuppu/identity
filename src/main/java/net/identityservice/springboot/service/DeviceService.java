package net.identityservice.springboot.service;
import java.util.List;
import net.identityservice.springboot.model.Device;


public interface DeviceService {
	Device saveDeviceDetails(Device user_device_details);
	List<Device> getAllDetails();
	Device getDeviceById(long id);
	Device updateDevice(Device user_device_details , long id);
	void deleteDevice(long id);
	
	
	
	
}
