package net.identityservice.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.identityservice.springboot.model.User;
@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	//@Query("SELECT ud.id FROM user ud WHERE ud.mobile = :mobileNumber")
	//long findUserIdByMobile(@Param("mobileNumber") int mobileNumber);
	@Query("SELECT ud.id FROM User ud WHERE ud.mobile = :mobile")
	List<Long> findUserIdByMobile(@Param("mobile") String mobile);
	
	
}