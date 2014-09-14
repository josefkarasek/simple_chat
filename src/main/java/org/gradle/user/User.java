package org.gradle.user;

import java.util.List;

public class User {
	private final String name;
	private final long id;
	private List<Long> posts;
	
	public User(String name, long id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public long getId() {
		return id;
	}
	
	public void addPostId(long id) {
		posts.add(id);
	}
}
