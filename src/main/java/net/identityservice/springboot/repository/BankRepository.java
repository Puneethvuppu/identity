package net.identityservice.springboot.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.identityservice.springboot.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {
	public Bank findByBankAccountNumber(String bankAccountNumber);
}
