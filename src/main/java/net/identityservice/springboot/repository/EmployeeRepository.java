package net.identityservice.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.identityservice.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}