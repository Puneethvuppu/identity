package net.identityservice.springboot.service;
import java.util.List;

import net.identityservice.springboot.model.Bank;

public interface BankService {
	Bank saveBank(Bank accountno);
	List<Bank> getAllBank();
	Bank getBankById(long id);
	Bank updateBank(Bank accountno,long id);
	void deleteBank(long id);
	boolean checkAccountNumber(String bankAccountNumber);
}
