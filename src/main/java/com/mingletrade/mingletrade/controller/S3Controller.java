package com.mingletrade.mingletrade.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mingletrade.mingletrade.service.S3Service;

import software.amazon.awssdk.services.s3.S3Client;

@RestController
@RequestMapping("/api/s3")
public class S3Controller {
	
	private final S3Service s3Service;
	private final S3Client s3Client;
	
	@Value("${aws.cloudfront-domail}")
	private String cloudfrontDomain;
	
	@Value("${aws.s3.bucket}")
	private String bucket;

	public S3Controller(S3Service s3Service, S3Client s3Client) {
		super();
		this.s3Service = s3Service;
		this.s3Client = s3Client;
	}
	
	@PostMapping("/presigned-url")
	public Map<String, String> getPresignedUrl(@RequestBody Map<String, Object> param){
		System.out.println("/presigned-url 요청받음, " + param.toString());
		String fileName = (String) param.get("fileName");
		String contentType = (String) param.get("contentType");
		String envPrefix = (String) param.get("envPrefix");
		String folder = (String) param.get("folder");
		String url = s3Service.generatePresignedUrl(fileName, contentType, envPrefix, folder);
		return Map.of("url", url);
	}
	
	@PostMapping("delete")
	public ResponseEntity<?> deleteImage(@RequestBody Map<String, Object> request){
		String imageUrl = (String) request.get("imageUrl");
		String key = imageUrl.replace(cloudfrontDomain, "");
		
		System.out.println("cloudfrontDomain : " + cloudfrontDomain);
		System.out.println("bucket : " + bucket);
		System.out.println("imageUrl : " + imageUrl);
		System.out.println("key : " + key);
		
		try {
			s3Client.deleteObject(b -> b.bucket(bucket).key(key.substring(1)));
			return ResponseEntity.ok("success");
		}catch(Exception e) {
			return ResponseEntity.status(500).body("failed: " + e.getMessage());
		}
	}
}
