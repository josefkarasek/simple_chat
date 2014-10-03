package org.gradle.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.gradle.post.Post;
import org.gradle.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ChatController {
	
	private static final AtomicLong userId = new AtomicLong(0L);
	private static final AtomicLong postId = new AtomicLong(0L);
	
	private Map<Long, User> users = new HashMap<Long, User>();
	private Map<Long, Post> posts = new HashMap<Long, Post>();
	
	ChatController() {
		
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public @ResponseBody User addUser(@RequestBody User u) {
		if(u.getId() != 0)
			return u;
		
		long newUserId = userId.incrementAndGet();
		User newUser = new User(u.getName(), newUserId);
		users.put(newUserId, newUser);
		
		return newUser;
	}
	
	@RequestMapping(value="/posts", method=RequestMethod.POST)
	public @ResponseBody Post registerPost(@RequestBody User u) {
		if(u.getId() == 0)
			return null;
		
		long newPostId = postId.incrementAndGet();
		Post newPost = new Post("", newPostId);
		posts.put(newPostId, newPost);
		users.get(u.getId()).addPostId(newPostId);
		
		return newPost;
	}
	
	@RequestMapping(value="/posts/id", method=RequestMethod.POST)
	public boolean uploadPost(@RequestBody Post p) {
		if(p.getText() == "")
			return false;
		
		posts.get(p.getId()).setText(p.getText());
		
		return true;
	}
	
	@RequestMapping(value="/posts", method=RequestMethod.GET)
	public@ResponseBody Map<Long, Post> downloadAllPosts() {
		return posts;
	}
	
	@RequestMapping(value="/posts/{id}", method=RequestMethod.GET)
	public@ResponseBody Map<Long, Post> downloadRecentPosts(@PathVariable("id") long numberOfIds) {
		Map<Long, Post> postsToBeSent = new HashMap<Long, Post>();
		
		for(long i = 1; i < posts.size() && i <= numberOfIds; ++i) {
			postsToBeSent.put(i, posts.get(i));
		}
		return postsToBeSent;
	}
}


