package com.mingletrade.mingletrade.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
}
