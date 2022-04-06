package com.saveheart.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.saveheart.dto.AuthResponseDto;
import com.saveheart.dto.LoginRequestDto;
import com.saveheart.dto.ResponseDto;
import com.saveheart.entities.Account;
import com.saveheart.entities.Role;
import com.saveheart.enums.ErrorEnum;
import com.saveheart.exception.CustomException;
import com.saveheart.repository.AccountRepository;
import com.saveheart.repository.RoleRepository;
import com.saveheart.security.util.JwtUtil;
import com.saveheart.services.AuthenticationService;


@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	final static Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
    public void register(Account account) {
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);
        Optional<Role> role = roleRepository.findByRoleName("USER");
        if(role.isPresent()) {
        	account.setRole(role.get());	
        }
        accountRepository.save(account);
    }
	
	@Override
    public ResponseDto login(LoginRequestDto loginRequest) {
        Optional<Account> account = accountRepository.findByEmail(loginRequest.getEmail());
        log.info(".....{}..{}",account,loginRequest.getEmail());
        if (account.isPresent() && validateAuth(account.get(), loginRequest)) {
            String token = jwtUtil.generateToken(account.get().getEmail(), account.get().getRole().getRoleName());
            String tokenExpiry = jwtUtil.extractExpiration(token).toString();
            return new ResponseDto("email: " + loginRequest.getEmail(), new AuthResponseDto(token, tokenExpiry));
        } else {
            throw new CustomException(ErrorEnum.ACCESS_DENIED, HttpStatus.UNAUTHORIZED);
        }
    }

    private Boolean validateAuth(Account account, LoginRequestDto loginRequest) {
        return account != null && passwordEncoder.matches(loginRequest.getPassword(), account.getPassword());
    }
}
