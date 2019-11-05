package com.platform.message.controller;

import com.platform.common.AuthResult;
import com.platform.common.ResultStatus;
import com.platform.common.annotation.Authentication;
import com.platform.common.exception.AuthFailedException;
import com.platform.common.token.TokenManager;
import com.platform.message.service.AuthService;
import com.platform.orm.entity.User;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangying
 */
@RestController
public class AuthController {
	@Autowired
	private TokenManager tokenManager;
	@Autowired
	private AuthService authService;

	@PostMapping("/auth")
	public ResponseEntity<AuthResult> login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		AuthResult result;
		Assert.notNull(username, "username can not be empty");
		Assert.notNull(password, "password can not be empty");
		User user;
		try {
			user = authService.auth(username, password);
			if (Objects.nonNull(user)) {
				String token = tokenManager.generateToken(user.getId(), user.getUsername());
				result = AuthResult.ok(token,user.getUsername());
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		} catch (AuthFailedException e) {
			result = AuthResult.error(ResultStatus.USERNAME_OR_PASSWORD_INVALID);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(AuthResult.error(ResultStatus.USER_NOT_LOGIN), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/logout/{id}")
	@Authentication
	public ResponseEntity<AuthResult> logout(@PathVariable(value = "id") Integer id) {
		tokenManager.removeToken(id);
		return new ResponseEntity<>(AuthResult.ok(), HttpStatus.OK);
	}
}
