package com.mingletrade.mingletrade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.mingletrade.mingletrade.service.CustomOAuth2UserService;

@Configuration
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
			//모든 요청 허용(임시)
			.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
			//기본 로그인폼 끄기
			.formLogin(form -> form.disable())
			//OAuth2 로그인 활성화
			//.oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("http://localhost:3000",true)) //로그인 성공시 프론트로 이동
			.oauth2Login(oauth2 -> oauth2.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService)).defaultSuccessUrl("http://localhost:3000",true)) 
			
			//CSRF 임시 비활성화(테스트용)
			.csrf(csrf -> csrf.disable());
		
		return http.build();
	}
}
