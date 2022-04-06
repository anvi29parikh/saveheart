package com.saveheart.controller;

import java.util.*;


import com.saveheart.entities.Account;
import com.saveheart.utils.CommonMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.saveheart.dto.AccountDto;
import com.saveheart.dto.ResponseDto;
import com.saveheart.dto.UpdateDto;
import com.saveheart.services.AccountService;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserDetailsController {
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private CommonMethods cm;

	int id;
	@GetMapping("/detail")
	public ResponseEntity<ResponseDto> fetchdetails()
	{

		ResponseDto responseDto = new ResponseDto();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		String userEmail = authentication.getName();

		List<AccountDto> accounts = accountService.fetchUserDetails(userEmail);
		id = accounts.get(0).getAccountId();
		responseDto.setData(accounts);
		responseDto.setMetadata(null);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
	@PostMapping("/update")
	public ResponseEntity<ResponseDto> updateUser(@Valid @RequestBody UpdateDto updateDto){
		Account account = new Account();
		ResponseDto responseDto = new ResponseDto();


		//UpdateDto updateDto = new UpdateDto();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		String userEmail = authentication.getName();




		accountService.updateUser(updateDto);


		List<AccountDto> accounts = accountService.fetchUserDetails(userEmail);

		return new ResponseEntity<>(new ResponseDto("Status: " + HttpStatus.CREATED, "User_Updated"), HttpStatus.CREATED);
	}
}
