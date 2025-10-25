package com.mingletrade.mingletrade.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mingletrade.mingletrade.service.UserService;
import com.mingletrade.mingletrade.util.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	JwtUtil jwtUtil;
	UserService userService;
	
	
	public AuthController(JwtUtil jwtUtil, UserService userService) {
		super();
		this.jwtUtil = jwtUtil;
		this.userService = userService;
	}


	@GetMapping("/login")
	public ResponseEntity<?> getUser(@CookieValue(value = "mingleToken", required = false) String token) {
		System.out.println("auth/me 접근 : " + token);
	    if (token == null) {
	        return ResponseEntity.status(401).body("Unauthorized: No token");
	    }

	    try {
	        Map<String, Object> claims = jwtUtil.parseClaims(token);
	        String email = (String) claims.get("sub");
	        Map<String, Object> user = userService.selectUser(email);
	        System.out.println("user: " + user.toString());
	        user.put("profileImage", user.get("profile_image"));
	        if (user == null) {
	            return ResponseEntity.status(404).body("User not found");
	        }
	        return ResponseEntity.ok(user);
	    } catch (Exception e) {
	        return ResponseEntity.status(401).body("Invalid or expired token");
	    }
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletResponse response){
		//동일한 이름으로 빈 쿠키 생성
		Cookie cookie = new Cookie("mingleToken", null);
		cookie.setHttpOnly(true);
	    cookie.setPath("/");
	    cookie.setMaxAge(0); //즉시 만료
	    cookie.setSecure(false);
	    cookie.setAttribute("SameSite", "None");
	    response.addHeader("Set-Cookie",
	        "mingleToken=; Max-Age=0; Path=/; HttpOnly; SameSite=None; Secure=false");
	    return ResponseEntity.ok("logged out");
	}
}
