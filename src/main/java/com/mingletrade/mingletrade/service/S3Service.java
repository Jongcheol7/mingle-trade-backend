package com.mingletrade.mingletrade.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Service
public class S3Service {

	private final S3Presigner presigner;
	
	//.env 환경변수 주입
	@Value("${S3_BUCKET}")
	private String bucketName;

	public S3Service(S3Presigner presigner) {
		super();
		this.presigner = presigner;
	}
	
	public String generatePresignedUrl(String fileName, String contentType, String envPrefix, String folder) {
		
		//S3 업로드 요청객체 생성
		PutObjectRequest putRequest = PutObjectRequest.builder()
				.bucket(bucketName)
				.key(envPrefix + "/" + folder + "/" + fileName) //업로드경로+파일명
				.contentType(contentType)
				.build();
		
		//Presigned URL 생성요청 객체
		PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
				.signatureDuration(Duration.ofMinutes(10))
				.putObjectRequest(putRequest)
				.build();
		
		//실제로 Presigned URL 생성
		PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);
		
		return presignedRequest.url().toString();
	}
}
