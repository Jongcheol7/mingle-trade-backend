package com.mingletrade.mingletrade.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

	//토큰 유효시간 (1시간 = 3600000ms)
	private static final long EXPIRATION_TIME = 1000 * 60 * 60;
	
	//JWT 서명용 시크릿 키 (실제 운영에서 .env로 분리)
	@Value("${JWT_SECRET}")  // ✅ .env에 있는 값 자동 주입
    private String secret;

	private Key getSigningKey() {
        return new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }
	
	//JWT 생성
	public String generateToken(String email, String name, String picture, String provider, String nickname, String profileImage) {
		return Jwts.builder()
				.setSubject(email)
				.claim("name", name)
				.claim("picture", picture)
				.claim("provider", provider)
				.claim("nickname", nickname)
				.claim("profileImage", profileImage)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	//JWT 검증
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	//토큰에서 이메일 추출
	public String getEmailFromToken(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
}
