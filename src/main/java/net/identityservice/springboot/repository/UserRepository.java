package net.identityservice.springboot.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	@Query("SELECT ud.id FROM User ud WHERE ud.mobile = :mobile and ud.validData = TRUE")
	List<Long> findUserIdByMobile(@Param("mobile") String mobile);
	
	 @Transactional
	  @Modifying
	@Query("UPDATE User ud SET ud.validData=TRUE WHERE ud.id=:ID")
	void setValidDataById(@Param("ID") long id);
	
}