package com.mingletrade.mingletrade.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/directMessages")
	public ResponseEntity<Map<String, Object>> selectDirectMessageContent(
													@RequestParam String senderEmail, 
													@RequestParam String receiverEmail, 
													@RequestParam(required = false) Long cursor, // 마지막으로 본 messageId
													@RequestParam(defaultValue = "20") int limit){
		System.out.println("selectDirectMessageContent 받은 내용 : " + senderEmail + receiverEmail + cursor + limit);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> messages = chatService.selectDirectMessageContent(senderEmail, receiverEmail, cursor, limit);
			System.out.println("roomList : " + messages.toString());
			result.put("status", "success");
			result.put("data", messages);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			result.put("status", "fail");
			result.put("message", e.getMessage());
			return ResponseEntity.status(500).body(result);
		}
	}
	
	@PostMapping("/makeChatRoom")
	public ResponseEntity<Map<String, Object>> createDirectChatRoom(@RequestBody Map<String,Object> param){
		System.out.println("createDirectChatRoom 받은 내용 : " + param.toString());
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String senderEmail = (String) param.get("senderEmail");
			String receiverEmail = (String) param.get("receiverEmail");
			String receiverUrl = (String) param.get("receiverUrl");
			Map<String, Object> roomId = chatService.createDirectChatRoom(senderEmail, receiverEmail, receiverUrl);
			result.put("status", "success");
			result.put("data", roomId);
			return ResponseEntity.ok(result);
		}catch (Exception e) {
			result.put("status", "fail");
			result.put("message", e.getMessage());
			return ResponseEntity.status(500).body(result);
		}
	}
}
