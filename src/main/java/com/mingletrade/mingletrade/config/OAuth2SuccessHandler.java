package com.mingletrade.mingletrade.config;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.mingletrade.mingletrade.service.UserService;
import com.mingletrade.mingletrade.util.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

	private final JwtUtil jwtUtil;
	private final UserService userService;

	public OAuth2SuccessHandler(JwtUtil jwtUtil, UserService userService) {
		super();
		this.jwtUtil = jwtUtil;
		this.userService = userService;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		Map<String, Object> attributes = oAuth2User.getAttributes();
		
		
		//로그인 성공시 사용자 이메일 추출
		String email = (String) attributes.get("email");

		//이메일로 DB에서 유저를 조회하자.
		Map<String, Object> userInfo = userService.selectUser(email);
		
		System.out.println("토큰 생성전 userInfo : " + userInfo.toString());
		//JWT 토큰 생성
		String token = jwtUtil.generateToken(
				(String) userInfo.get("email"),
				(String) userInfo.get("name"),
				(String) userInfo.get("picture"),
				(String) userInfo.get("provider"),
				(String) userInfo.get("nickname"),
				(String) userInfo.get("profile_image")
		);
		
		System.out.println("생성된 토큰 : " + token);
		//HttpOnly쿠키로 전달하자		
		Cookie cookie = new Cookie("mingleToken", token);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24);

		// ⚠️ 핵심 두 줄
		cookie.setSecure(false); // HTTP 환경이니까 false
		cookie.setAttribute("SameSite", "None");

		response.addHeader("Set-Cookie",
		    String.format("mingleToken=%s; Max-Age=%d; Path=/; HttpOnly; SameSite=None; Secure=false",
		        token, 60 * 60 * 24)
		);
		
		//프론트로 토큰 전달(쿼리스트링)
		//String redirectUrl = "http://localhost:3000?token=" + token;
		response.sendRedirect("http://localhost:3000");
	}
	
}
