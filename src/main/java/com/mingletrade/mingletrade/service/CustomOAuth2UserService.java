package com.mingletrade.mingletrade.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.mingletrade.mingletrade.mapper.UserMapper;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService{

	private final UserMapper userMapper;
	
	public CustomOAuth2UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) {
		//기본 구현체 호출 (구글에서 사용자 정보 요청)
		OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();
		
		String email = (String) attributes.get("email");
		String name = (String) attributes.get("name");
		String picture = (String) attributes.get("picture");
		
		System.out.println("DB저장전 email, name, picture : " + email + name + picture);
		
		//DB에 존재하는지 확인
		Map<String, Object> existingUser = userMapper.findByEmail(email);
		if(existingUser == null) {
			//새 유저 등록
			Map<String,Object> user = new HashMap<String, Object>();
			user.put("email", email);
			user.put("name", name);
			user.put("picture", picture);
			user.put("provider", "google");
			
			System.out.println("등록할 유저 정보 : " + user.toString());
			
			userMapper.insertUser(user);
			System.out.println("새 유저 등록 완료: " + email);
		}else {
			System.out.println("기존 유저 로그인: " + email);
		}
		
		/*
		//구글이 내려주는 유저 정보 확인용 로그
        System.out.println("✅ 구글 로그인 성공: 사용자 정보 ↓↓↓");
        oAuth2User.getAttributes().forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });
        */

        return oAuth2User; // 그대로 반환 (다음 필터에서 처리)
	}
	
}
