package com.saveheart.services;

import java.util.List;
import java.util.Optional;


import com.saveheart.dto.AccountDto;
import com.saveheart.dto.UpdateDto;
import com.saveheart.entities.Account;


public interface AccountService {

	Optional<Account> getAccountByEmail(String email);
	
	List<AccountDto> fetchAllUsers();
	List<AccountDto> fetchUserDetails(String email);


	public void updateUser(UpdateDto updateDto);


}
