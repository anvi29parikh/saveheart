package com.saveheart.repository;

import com.saveheart.dto.AccountDto;
import com.saveheart.dto.UpdateDto;
import com.saveheart.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface UserdetailRepository extends JpaRepository<Account, Integer> {



    /*String s1 = null;
    @Query("select new com.citysight.dto.AccountDto(ac.accountId, ac.email, ac.fname, ac.lname, ac.role, ac.address) from Account ac where ac.email = 'harshil@citysight.com'")
    List<AccountDto> fetchUserDetailsDto();*/

    @Query("SELECT new com.saveheart.dto.AccountDto(ac.accountId, ac.email, ac.fname, ac.lname, ac.role) from Account ac WHERE ac.email = ?1")
     List<AccountDto> fetchUserDetailsDto(String email);

}