package net.identityservice.springboot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import net.identityservice.springboot.exception.ResourceNotFoundException;
import net.identityservice.springboot.model.Bank;
import net.identityservice.springboot.repository.BankRepository;
import net.identityservice.springboot.service.BankService;

@Service
public class BankServiceImpl implements BankService{
	
	private BankRepository bankrepository;
	
	public BankServiceImpl(BankRepository bankrepository) {
		super();
		this.bankrepository = bankrepository;
	}

	@Override
	public Bank saveBank(Bank accountno) {
		
		return bankrepository.save(accountno);
	}

	@Override
	public List<Bank> getAllBank() {
		
		return bankrepository.findAll();
	}

	@Override
	public Bank getBankById(long id) {
		
		return bankrepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("AccountNo", "Id", id));
	}

	@Override
	public Bank updateBank(Bank accountno, long id) {
		Bank existingBank = bankrepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("AccountNo", "Id", id)); 
		
		existingBank.setAccountNo(accountno.getAccountNo());
		bankrepository.save(existingBank);
		return existingBank;	
	}

	@Override
	public void deleteBank(long id) {
		bankrepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("AccountNo", "Id", id));
bankrepository.deleteById(id);
		
	}

}
