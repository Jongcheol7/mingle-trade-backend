package com.mingletrade.mingletrade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mingletrade.mingletrade.domain.Chat;
import com.mingletrade.mingletrade.domain.User;

@Mapper
public interface ChatMapper {

	List<Map<String, Object>> selectChatList(String email);
	
	List<Chat> selectDirectMessageContent(Long roomId, String senderEmail, String receiverEmail, Long cursor, int limit);
	
	Map<String, Object> createRoomId();
	
	void insertDirectChatRoom(@Param("roomId") Long roomId,
	        @Param("receiverEmail") String receiverEmail,
	        @Param("receiverUrl") String receiverUrl);
	
	void insertDirectChatMember(Long roomId, String email);
	
	Long selectDirectChatRoom(String senderEmail, String receiverEmail);
	
	void insertDirectChatMessage(Long roomId, String senderEmail, String message);
	
	List<Map<String, Object>> selectDirectChatMember(Long roomId);
}
