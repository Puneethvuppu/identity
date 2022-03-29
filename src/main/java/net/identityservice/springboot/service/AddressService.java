package net.identityservice.springboot.service;
import java.util.List;
import net.identityservice.springboot.model.Address;

public interface AddressService {

	Address saveAddress(Address address);
	List<Address> getAllAddress();
	Address getAddressById(long id);
	Address updateAddress(Address address,long id);
	void deleteAddress(long id);
	boolean checkAddress(String addressDetail);
}
