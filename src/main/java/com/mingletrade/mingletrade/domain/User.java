package com.mingletrade.mingletrade.domain;

public class User {
    private Long id;               // PK
    private String email;          // 사용자 이메일 (고유)
    private String name;           // OAuth 기본 이름
    private String nickname;       // 서비스 전용 닉네임 (사용자 커스텀)
    private String picture;        // OAuth 프로필 이미지
    private String profileImage;   // 서비스에서 설정한 프로필 이미지 (picture와 별도)
    private String provider;       // google / kakao / naver
    private String providerId;     // provider가 부여한 고유 ID
    private String createdAt;      // 생성일
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", nickname=" + nickname + ", picture="
				+ picture + ", profileImage=" + profileImage + ", provider=" + provider + ", providerId=" + providerId
				+ ", createdAt=" + createdAt + "]";
	}
    
}
