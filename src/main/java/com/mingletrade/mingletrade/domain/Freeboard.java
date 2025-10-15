package com.mingletrade.mingletrade.domain;

import java.time.LocalDateTime;

public class Freeboard {

	private int id;
	private String title;
	private String writer;
	private String content;
	private LocalDateTime createdAt;
	private int views;
	
	public Freeboard(int id, String title, String writer, String content, LocalDateTime createdAt, int views) {
		super();
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.createdAt = createdAt;
		this.views = views;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	@Override
	public String toString() {
		return "FreeBoard [id=" + id + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", createdAt=" + createdAt + ", views=" + views + "]";
	}
}
