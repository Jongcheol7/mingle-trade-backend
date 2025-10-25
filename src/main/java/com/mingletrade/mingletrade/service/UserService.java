package com.mingletrade.mingletrade.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mingletrade.mingletrade.domain.User;
import com.mingletrade.mingletrade.mapper.UserMapper;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

@Service
public class UserService {

	private final UserMapper userMapper;
	private final S3Client s3Client;
	
	//.env 환경변수 주입
	@Value("${S3_BUCKET}")
	private String bucketName;

	public UserService(UserMapper userMapper, S3Client s3Client) {
		super();
		this.userMapper = userMapper;
		this.s3Client = s3Client;
	}
	
	public Map<String, Object> selectUser(String email){
		return userMapper.findByEmail(email);
	}
	
	public void updateNickname(User user) {
		userMapper.updateNickname(user);
	}
	
	public void updateProfileImage(String email, String profileImage) {
		//기존 프로필이미지 조회
		Map<String, Object> userInfo = userMapper.findByEmail(email);
		
		userMapper.updateProfileImage(email, profileImage);
		
		String oldProfileImage = (String) userInfo.get("profile_image");

		//기존 이미지가 있고, S3 버킷 기반일때만 삭제
		if(oldProfileImage != null && oldProfileImage.contains("cloudfront.net")) {
			try {
                String key = extractS3Key(oldProfileImage);
                DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build();
                s3Client.deleteObject(deleteRequest);
                System.out.println("✅ S3에서 기존 프로필 삭제 완료: " + key);
            } catch (Exception e) {
                System.err.println("⚠️ S3 기존 이미지 삭제 실패: " + e.getMessage());
            }
		}
	}
	
    // URL → S3 key 변환
    private String extractS3Key(String url) {
        // 예시: https://dxxxx.cloudfront.net/dev/profile/1761411802995.jpg
        // 결과: dev/profile/1761411802995.jpg
        int idx = url.indexOf("dev/");
        if (idx == -1) idx = url.indexOf("prod/"); // prod 환경 대응
        return idx != -1 ? url.substring(idx) : null;
    }
}
