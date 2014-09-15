package org.gradle.service;

import java.util.HashMap;

import org.gradle.post.Post;
import org.gradle.user.User;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ChatSvcApi {

	public static final String POSTS_PARAMETER = "/posts";
	public static final String USER_PARAMETER = "/users";
	public static final String ID_PARAMETER = "id";
	public static final String POSTS_PATH = POSTS_PARAMETER + "{id}";
	
	@GET(POSTS_PARAMETER)
	public HashMap<Long, Post> getAllPosts();
	
	@GET(POSTS_PATH)
	public HashMap<Long, Post> getRecentPosts(@Path(ID_PARAMETER) long id);
	
	@POST(USER_PARAMETER)
	public User addUser(@Body User u);
	
	@POST(POSTS_PARAMETER)
	public Post registerPost(@Body User u);
	
	@POST(POSTS_PATH)
	public boolean uploadPost(@Body Post p);
}
