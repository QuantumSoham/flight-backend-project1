package com.flightapp.repository;

import com.flightapp.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

	//findByEmail is parsed to 
	//SELECT * FROM USERACCOUNT WHERE EMAIL= :email;
    Optional<UserAccount> findByEmail(String email);
}
