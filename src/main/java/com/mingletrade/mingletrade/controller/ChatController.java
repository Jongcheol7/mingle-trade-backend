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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mingletrade.mingletrade.domain.Chat;
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
	
	@GetMapping("/findChatRoom")
	public ResponseEntity<Map<String, Object>> findChatRoom(@RequestParam String senderEmail, 
															@RequestParam String receiverEmail){
		//System.out.println("findChatRoom 받은 내용 : "+ senderEmail + " / " + receiverEmail);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Long roomId = chatService.selectDirectChatRoom(senderEmail, receiverEmail);
			//System.out.println("결과 : " + roomId);
			result.put("status", "success");
			result.put("data", roomId);
			return ResponseEntity.ok(result);
			
		}catch (Exception e) {
			result.put("status", "fail");
			result.put("message", e.getMessage());
			return ResponseEntity.status(500).body(result);
		}
	}
	
	@GetMapping("/directMessages")
	public ResponseEntity<Map<String, Object>> selectDirectMessageContent(
													@RequestParam Long roomId,
													@RequestParam String senderEmail, 
													@RequestParam String receiverEmail, 
													@RequestParam(required = false) Long cursor, // 마지막으로 본 messageId
													@RequestParam(defaultValue = "20") int limit){
		System.out.println("selectDirectMessageContent 받은 내용 : " + roomId +  senderEmail + receiverEmail + cursor + limit);

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(roomId != 0 && !senderEmail.equals("") && !receiverEmail.equals("")) {
				roomId = chatService.selectDirectChatRoom(senderEmail, receiverEmail);
				System.out.println("roomId : " + roomId);
			}
			if(senderEmail.equals("") && receiverEmail.equals("")) {
				List<Map<String, Object>> test = chatService.selectDirectChatMember(roomId);
				result.put("member", test);
				
			}
			List<Chat> messages = chatService.selectDirectMessageContent(roomId, senderEmail, receiverEmail, cursor, limit);
			Long nextCursor = null;
			if (!messages.isEmpty() && messages.size() >= limit) {
			    nextCursor = messages.get(messages.size() - 1).getId();
			}
			result.put("data", messages);
			result.put("nextCursor", nextCursor);
			result.put("status", "success");
			result.put("roomId", roomId);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			result.put("status", "fail");
			result.put("message", e.getMessage());
			return ResponseEntity.status(500).body(result);
		}
	}
	
//	@GetMapping("/findDirectMessagesByRoomId")
//	public ResponseEntity<Map<String, Object>> findDirectMessagesByRoomId(
//					@RequestParam Long roomId,
//					@RequestParam(required = false) Long cursor, // 마지막으로 본 messageId
//					@RequestParam(defaultValue = "20") int limit){
//		System.out.println("selectDirectMessageContent 받은 내용 : " + roomId + cursor + limit);
//		Map<String, Object> result = new HashMap<String, Object>();
//		try {
//		if(roomId != 0) {
//		roomId = chatService.selectDirectChatRoom(senderEmail, receiverEmail);
//		System.out.println("roomId : " + roomId);
//		}
//		List<Map<String, Object>> messages = chatService.selectDirectMessageContent(roomId, senderEmail, receiverEmail, cursor, limit);
//		result.put("data", messages);
//		result.put("status", "success");
//		result.put("roomId", roomId);
//		return ResponseEntity.ok(result);
//		} catch (Exception e) {
//		result.put("status", "fail");
//		result.put("message", e.getMessage());
//		return ResponseEntity.status(500).body(result);
//		}
//	}
	
	@PostMapping("/makeChatRoom")
	public ResponseEntity<Map<String, Object>> createDirectChatRoom(@RequestBody Map<String,Object> param){
		System.out.println("createDirectChatRoom 받은 내용 : " + param.toString());
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String senderEmail = (String) param.get("senderEmail");
			String receiverEmail = (String) param.get("receiverEmail");
			String receiverUrl = (String) param.get("receiverUrl");
			Long existingRoomId = chatService.selectDirectChatRoom(senderEmail, receiverEmail);
			System.out.println("existingRoomId : " + existingRoomId);
			if(existingRoomId > 0) {
				result.put("status", "success");
				result.put("data", existingRoomId);
				return ResponseEntity.ok(result);
			}else {
				Map<String, Object> roomId = chatService.createDirectChatRoom(senderEmail, receiverEmail, receiverUrl);
				result.put("status", "success");
				result.put("data", roomId);
				return ResponseEntity.ok(result);
			}
			
		}catch (Exception e) {
			result.put("status", "fail");
			result.put("message", e.getMessage());
			return ResponseEntity.status(500).body(result);
		}
	}
	
	@PostMapping("/saveChatMessage")
	public ResponseEntity<Map<String, Object>> saveChatMessage(@RequestBody Map<String, Object> param){
		System.out.println("saveChatMessage 받은 내용 : " + param.toString());
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Long roomId = ((Number) param.get("roomId")).longValue();
			String senderEmail = (String) param.get("senderEmail");
			String message = (String) param.get("message");
			chatService.saveDirectChatMessage(roomId, senderEmail, message);
			result.put("status", "success");
			result.put("data", "메세지 저장완료");
			return ResponseEntity.ok(result);
			
		}catch (Exception e) {
			result.put("status", "fail");
			result.put("data", e.getMessage());
			return ResponseEntity.status(500).body(result);
		}
	}
}
