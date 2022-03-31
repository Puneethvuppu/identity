package net.identityservice.springboot.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.identityservice.springboot.model.Address;
import net.identityservice.springboot.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {
private AddressService addressService;
	
	public AddressController(AddressService addressService) {
		super();
		this.addressService = addressService;
	}

	@PostMapping()
	public ResponseEntity<Address> saveAddress(@RequestBody Address address){
	   String addressDetail=address.getAddressDetail();
	   boolean result=addressService.checkAddress(addressDetail);
	   if(result)
	   {
		   addressService.saveAddress(address);
	      return new ResponseEntity<Address>( HttpStatus.CREATED);
	   }
	   return new ResponseEntity<Address>(HttpStatus.OK);
	}
}
