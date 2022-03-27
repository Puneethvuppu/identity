package net.identityservice.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.identityservice.springboot.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long>{

}
