package com.mingletrade.mingletrade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mingletrade.mingletrade.domain.User;

@Mapper
public interface ChatMapper {

	List<Map<String, Object>> selectChatList(String email);
	
	List<Map<String, Object>> selectDirectMessageContent(String senderEmail, String receiverEmail, Long cursor, int limit);
	
	Map<String, Object> createRoomId();
	
	void insertDirectChatRoom(@Param("roomId") Long roomId,
	        @Param("receiverEmail") String receiverEmail,
	        @Param("receiverUrl") String receiverUrl);
	
	void insertDirectChatMember(Long roomId, String email);
	
}
