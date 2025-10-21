package com.mingletrade.mingletrade.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mingletrade.mingletrade.domain.User;
import com.mingletrade.mingletrade.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/update/nickname")
	public String updateNickname(@RequestBody User user) {
		System.out.println("받은 데이터 : " + user);
		userService.updateNickname(user);
		return "success";
	}
}
