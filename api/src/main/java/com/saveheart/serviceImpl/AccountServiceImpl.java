package com.saveheart.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.saveheart.repository.UserdetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.saveheart.dto.AccountDto;
import com.saveheart.dto.UpdateDto;
import com.saveheart.entities.Account;
import com.saveheart.repository.AccountRepository;
import com.saveheart.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private UserdetailRepository userdetailRepository;

	//@Autowired Account account;
	
	@Override
	public Optional<Account> getAccountByEmail(String email){
		return accountRepository.findByEmail(email);
	}
	
	@Override
	public List<AccountDto> fetchAllUsers() {
		return accountRepository.fetchAllAccountDtos();
	}

	@Override
	public List<AccountDto> fetchUserDetails(String email) { return userdetailRepository.fetchUserDetailsDto(email); }



	@Override
	public void updateUser(UpdateDto updateDto)
	{
		Account account = new Account();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || !authentication.isAuthenticated()) {
			System.out.println("null");
		}
		String email = authentication.getName();


		List<AccountDto> accounts = userdetailRepository.fetchUserDetailsDto(email);
		int id = accounts.get(0).getAccountId();



		String s1 = updateDto.getEmail();
		String s2 = updateDto.getFname();
		String s3 = updateDto.getLname();

		//userdetailRepository.updatedUser(s1,s2,s3,id);






	}





}
