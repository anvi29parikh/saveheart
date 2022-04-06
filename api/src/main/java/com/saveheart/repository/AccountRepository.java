package com.saveheart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.saveheart.dto.AccountDto;
import com.saveheart.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	Optional<Account> findByEmail(String email);



	@Query("select new com.saveheart.dto.AccountDto(ac.accountId, ac.email, ac.fname, ac.lname, ac.role) from Account ac")
	List<AccountDto> fetchAllAccountDtos();


}
