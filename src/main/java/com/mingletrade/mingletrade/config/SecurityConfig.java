package com.mingletrade.mingletrade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mingletrade.mingletrade.service.CustomOAuth2UserService;

@Configuration
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;
	private final OAuth2SuccessHandler oAauth2SuccessHandler;
	private final JwtFilter jwtFilter;


    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, OAuth2SuccessHandler oAauth2SuccessHandler, JwtFilter jwtFilter) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.oAauth2SuccessHandler = oAauth2SuccessHandler;
        this.jwtFilter = jwtFilter;
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
		    // 🔒 1. 모든 요청 우선 허용 (테스트용)
			.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
			// 🚫 2. 기본 로그인 폼 비활성화
			.formLogin(form -> form.disable())
			// 🌐 3. OAuth2 로그인 설정
			//.oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("http://localhost:3000",true)) //로그인 성공시 프론트로 이동
			//.oauth2Login(oauth2 -> oauth2.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService)).defaultSuccessUrl("http://localhost:3000",true)) //로그인 성공시 DB저장후 프론트로 이동
			.oauth2Login(oauth2 -> oauth2.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService)).successHandler(oAauth2SuccessHandler)) // 로그인 성공시 DB저장후 핸들러로 이동
			// 🧱 4. JWT 필터 등록 (모든 요청 앞단에서 토큰 검증)
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // ✅ JWT 필터 등록
			//CSRF 임시 비활성화(테스트용)
			.csrf(csrf -> csrf.disable());
		
		return http.build();
	}
}
