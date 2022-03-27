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

import net.identityservice.springboot.model.Bank;
import net.identityservice.springboot.service.BankService;

@RestController
@RequestMapping("/api/bank")
public class BankController {
private BankService bankService;
	
	public BankController(BankService bankService) {
		super();
		this.bankService = bankService;
	}
	
	@PostMapping()
	public ResponseEntity<Bank> saveBank(@RequestBody Bank acountNo){
	
		return new ResponseEntity<Bank>(bankService.saveBank(acountNo), HttpStatus.CREATED);
	}
	
	
	@GetMapping()
	public List<Bank> getAllBank(){
		return bankService.getAllBank();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Bank> getUserById(@PathVariable("id") long bankId){
		return new ResponseEntity<Bank>(bankService.getBankById(bankId), HttpStatus.OK);
	}
	

	@PutMapping("{id}")
	public ResponseEntity<Bank> updateUser(@PathVariable("id") long id
												  ,@RequestBody Bank accountNo){
		return new ResponseEntity<Bank>(bankService.updateBank(accountNo, id), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
		
		// delete employee from DB
		bankService.deleteBank(id);
		
		return new ResponseEntity<String>("Bank Account No deleted successfully!.", HttpStatus.OK);
	}
}
