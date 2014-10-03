package org.gradle.dataup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.gradle.post.Post;
import org.gradle.service.ChatSvcApi;
import org.gradle.user.User;
import org.junit.Test;

import retrofit.RestAdapter;

public class GeneralTest {

	private static final String SERVER = "http://localhost:8080";

	private User user1 = new User("user1", 0);
	private User user2 = new User("user2", 0);
	
	private Post post1 = new Post("post1", 0);
	private Post post2 = new Post("post2", 0);
	private Post post3 = new Post("post3", 0);
	private Post post4 = new Post("", 0);
	
	private ChatSvcApi chatSvc = new RestAdapter.Builder()
	.setEndpoint(SERVER).build()
	.create(ChatSvcApi.class);
	
	@Test
	public void testAddUser() throws Exception {
		System.out.println(chatSvc.getAllPosts());
		String newUser = "user0";
		long newId = 0;
		User userReceived = chatSvc.addNewUser(new User(newUser, newId));
		assertTrue(userReceived.getId() > 0);
		assertEquals(user1.getName(), userReceived.getName());
	}
	
}
