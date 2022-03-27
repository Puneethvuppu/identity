package net.identityservice.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.identityservice.springboot.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

}
