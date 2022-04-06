package com.saveheart.services;

import com.saveheart.dto.LoginRequestDto;
import com.saveheart.dto.ResponseDto;
import com.saveheart.entities.Account;

public interface AuthenticationService {
	void register(Account account);
	
	ResponseDto login(LoginRequestDto loginRequestDto);
}
