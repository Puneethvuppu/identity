package net.identityservice.springboot.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.identityservice.springboot.model.Device;
import net.identityservice.springboot.service.DeviceService;


@RestController
@RequestMapping("/api/device")
public class DeviceController {

private DeviceService userdeviceService;
	
	public DeviceController(DeviceService userdeviceService) {
		super();
		this.userdeviceService = userdeviceService;
	}
	
	@PostMapping()
	public ResponseEntity<Device> saveDeviceDetails(@RequestBody Device user_device_details){
	
		return new ResponseEntity<Device>(userdeviceService.saveDeviceDetails(user_device_details), HttpStatus.CREATED);
	}
	@GetMapping()
	public List<Device> getAllDetails(){
		return userdeviceService.getAllDetails();
	}
	@GetMapping("{id}")
	public ResponseEntity<Device> getDeviceById(@PathVariable("id") long employeeId){
		return new ResponseEntity<Device>(userdeviceService.getDeviceById(employeeId), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Device> updateDevice(@PathVariable("id") long id
												  ,@RequestBody Device device){
		return new ResponseEntity<Device>(userdeviceService.updateDevice(device, id), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
		
		
		userdeviceService.deleteDevice(id);
		
		return new ResponseEntity<String>("Device Details deleted successfully!.", HttpStatus.OK);
	}
}
