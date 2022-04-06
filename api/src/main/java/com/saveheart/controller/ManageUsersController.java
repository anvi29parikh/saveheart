package com.saveheart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saveheart.dto.AccountDto;
import com.saveheart.dto.ResponseDto;
import com.saveheart.services.AccountService;


@RestController
@RequestMapping("/admin")
public class ManageUsersController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/users")
	public ResponseEntity<ResponseDto> fetchUsers(){
		ResponseDto responseDto = new ResponseDto();
		List<AccountDto> accounts = accountService.fetchAllUsers();
		responseDto.setData(accounts);
		responseDto.setMetadata(null);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
}
