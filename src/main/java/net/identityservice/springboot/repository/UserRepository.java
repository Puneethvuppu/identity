package net.identityservice.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.identityservice.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}