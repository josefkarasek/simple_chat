package org.gradle.post;

public class Post {
	private final long id;
	private String text;
	
	public Post(String text, long id) {
		this.text = text;
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public long getId() {
		return id;
	}
}
