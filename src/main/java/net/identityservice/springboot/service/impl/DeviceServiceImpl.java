package net.identityservice.springboot.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import net.identityservice.springboot.exception.ResourceNotFoundException;
import net.identityservice.springboot.model.Device;
import net.identityservice.springboot.repository.DeviceRepository;
import net.identityservice.springboot.service.DeviceService;

@Service

public class DeviceServiceImpl implements DeviceService {

private DeviceRepository deviceRepository;
	
	public DeviceServiceImpl(DeviceRepository deviceRepository) {
		super();
		this.deviceRepository = deviceRepository;
	}

	@Override
	public Device saveDeviceDetails(Device details) {
		return deviceRepository.save(details);
	}

	@Override
	public List<Device> getAllDetails() {
		return deviceRepository.findAll();
	}

	@Override
	public Device getDeviceById(long id) {
		return deviceRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Device", "Id", id));
	}

	@Override
	public Device updateDevice(Device user_device_details, long id) {
		
				Device existingDevice = deviceRepository.findById(id).orElseThrow(
						() -> new ResourceNotFoundException("Device", "Id", id)); 
				existingDevice.setDeviceModel(user_device_details.getDeviceModel());
				existingDevice.setDeviceId(user_device_details.getDeviceId());
				deviceRepository.save(existingDevice);
				return existingDevice;	
	}

	@Override
	public void deleteDevice(long id) {
		deviceRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Device", "Id", id));
deviceRepository.deleteById(id);
		
	}

	

	
	
}
