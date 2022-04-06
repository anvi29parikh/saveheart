package com.saveheart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saveheart.dto.LoginRequestDto;
import com.saveheart.dto.ResponseDto;
import com.saveheart.entities.Account;
import com.saveheart.services.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	final static Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	@Autowired
	private AuthenticationService authenticationService;
	@GetMapping("/publicAPI1")
	public String publicAPI () {
		return "public api";
	}
	
	@GetMapping("/privateAPI")
	public String privateAPI () {
		return "private api";
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDto> register (@Valid @RequestBody Account account) {
		log.info("Attemtng to create a account with email : {}", account.getEmail());
			authenticationService.register(account);
				return new ResponseEntity<>(new ResponseDto("Status: " + HttpStatus.CREATED, "USER_REGISTERED"), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDto> login (@Valid @RequestBody LoginRequestDto loginRequestDto) {
		authenticationService.login(loginRequestDto);
		return new ResponseEntity<>(authenticationService.login(loginRequestDto), HttpStatus.OK);
	}
}
