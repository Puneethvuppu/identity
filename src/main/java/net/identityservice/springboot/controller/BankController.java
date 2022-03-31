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
	public ResponseEntity<Bank> saveBank(@RequestBody Bank bank){
		String accountNumber=bank.getBankAccountNumber();
		boolean result=bankService.checkAccountNumber(accountNumber);
		if(result)
		{
			bankService.saveBank(bank);
			return new ResponseEntity<Bank>(HttpStatus.CREATED);
		}
		return new ResponseEntity<Bank>(HttpStatus.OK);
	}
}
