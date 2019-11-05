package com.platform.message.controller;

import com.platform.message.handler.UserPriorityHandler;
import com.platform.message.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.platform.common.RequestResult;
import com.platform.common.annotation.Authentication;
import com.platform.common.exception.UniqueException;
import com.platform.message.vo.UserVO;
import com.platform.orm.entity.User;

/**
 * @author wangying
 */
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserPriorityHandler priorityHandler;

	@GetMapping("/user/{id}")
	@Authentication
	public UserVO getUserById(@PathVariable("id") Integer id) {
		return userService.findById(id);
	}

	@PutMapping("/user/{id}")
	@Authentication
	public RequestResult resetPassword(@PathVariable Integer id, @RequestParam String password) {
		userService.resetPassword(id, password);
		return RequestResult.success();
	}

	@PutMapping("/user/priority")
	@Authentication
	public RequestResult updatePriority(@RequestBody UserVO user) {
		if (priorityHandler.hasPermission(user.getUsername())) {
			userService.updatePriority(user);
			return RequestResult.success();
		} else {
			return RequestResult.failed();
		}
	}

	@PostMapping("/user/register")
	public RequestResult register(@RequestBody UserVO user) throws UniqueException {
		userService.register(user);
		return RequestResult.success();
	}

	@GetMapping("/user/priorities")
	public List<Integer> getUserPriority() {
		Integer priority = priorityHandler.getUserPriority();
		return userService.getUserPriorities(priority);
	}
}
