package com.mingletrade.mingletrade.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mingletrade.mingletrade.service.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
	
	private ChatService chatService;
	

	public ChatController(ChatService chatService) {
		super();
		this.chatService = chatService;
	}


	@GetMapping("/roomList")
	public ResponseEntity<Map<String, Object>> selectChatList(@RequestParam String email){
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println("selectChatList 받은 내용 : " + email);
		try {
			List<Map<String, Object>> roomList = chatService.selectChatList(email);
			System.out.println("roomList : " + roomList.toString());
			result.put("status", "success");
			result.put("data", roomList);
			return ResponseEntity.ok(result);
			
		}catch (Exception e) {
			result.put("status", "fail");
			result.put("message", e.getMessage());
			return ResponseEntity.status(500).body(result);
		}
	}
}
