package net.identityservice.springboot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import net.identityservice.springboot.exception.ResourceNotFoundException;
import net.identityservice.springboot.model.Address;
import net.identityservice.springboot.repository.AddressRepository;
import net.identityservice.springboot.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	private AddressRepository addressRepository;
	public AddressServiceImpl(AddressRepository addressRepository) {
		super();
		this.addressRepository=addressRepository;
	}
	@Override
	public Address saveAddress(Address address) {
		
		return addressRepository.save(address);
	}

	@Override
	public List<Address> getAllAddress() {
		
		return addressRepository.findAll();
	}

	@Override
	public Address getAddressById(long id) {
		
		return addressRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Address", "Id", id));
	}

	@Override
	public Address updateAddress(Address address, long id) {
		
		return null;
	}

	@Override
	public void deleteAddress(long id) {
		addressRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Address", "Id", id));
addressRepository.deleteById(id);
		
	}
	

}
