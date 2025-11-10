package com.mingletrade.mingletrade.domain;

import java.time.LocalDateTime;

public class Chat {
    private Long id;
    private Long roomId;
    private String senderEmail;
    private LocalDateTime createdAt;
    private String message;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Chat [id=" + id + ", roomId=" + roomId + ", senderEmail=" + senderEmail + ", createdAt=" + createdAt
				+ ", message=" + message + "]";
	}
    
    
}
