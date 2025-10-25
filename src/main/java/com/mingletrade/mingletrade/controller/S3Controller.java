package com.mingletrade.mingletrade.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mingletrade.mingletrade.service.S3Service;

@RestController
@RequestMapping("/api/s3")
public class S3Controller {
	
	private final S3Service s3Service;

	public S3Controller(S3Service s3Service) {
		super();
		this.s3Service = s3Service;
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
}
