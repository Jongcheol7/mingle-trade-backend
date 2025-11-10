package com.mingletrade.mingletrade.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingletrade.mingletrade.domain.Chat;
import com.mingletrade.mingletrade.domain.Freeboard;
import com.mingletrade.mingletrade.domain.PrevClosingPrice;
import com.mingletrade.mingletrade.mapper.ChatMapper;
import com.mingletrade.mingletrade.mapper.FreeboardMapper;

@Service
public class ChatService {

	private final ChatMapper mapper;
	
	
	public ChatService(ChatMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public List<Map<String, Object>> selectChatList(String email){
		System.out.println("chatservice email: " + email);
		return mapper.selectChatList(email);
	};
	
	public List<Chat> selectDirectMessageContent(Long roomId, String senderEmail, String receiverEmail, Long cursor, int limit){
		return mapper.selectDirectMessageContent(roomId, senderEmail, receiverEmail, cursor, limit);
	}
	
	public Map<String, Object> createDirectChatRoom(String senderEmail, String receiverEmail, String receiverUrl){
		Map<String, Object> tmpRoomId = mapper.createRoomId();
		System.out.println("tmpRoomId : " + tmpRoomId);
		Long roomId = (Long) tmpRoomId.get("room_id");
		
		mapper.insertDirectChatRoom(roomId, receiverEmail, receiverUrl);
		mapper.insertDirectChatMember(roomId, senderEmail);
		mapper.insertDirectChatMember(roomId, receiverEmail);
		return tmpRoomId;
	}
	
	
	public Long selectDirectChatRoom(String senderEmail, String receiverEmail) {
		Long roomId = mapper.selectDirectChatRoom(senderEmail, receiverEmail);
	    return roomId == null ? 0L : roomId;
	}
	
	public void saveDirectChatMessage(Long roomId, String senderEmail, String message) {
		mapper.insertDirectChatMessage(roomId, senderEmail, message);
	}
	
	public List<Map<String, Object>> selectDirectChatMember(Long roomId){
		return mapper.selectDirectChatMember(roomId);
	}
}
