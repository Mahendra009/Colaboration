package com.niit.BlogBackEnd.Test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OracleTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.BlogBackEnd");
		context.refresh();
		
		
		/*BlogDAO blogDAO = (BlogDAO) context.getBean("blogDAO");
		Blog blog = new Blog();
		blog.setBlogContent("This is all about Comedy Content");
		blog.setBlogName("Comedy");
		blog.setLikes(9000000);
		blog.setStatus("NA");
		blog.setCreateDate(new Date());
		blog.setUserName("Amarjeet Singh");
		
		blogDAO.saveBlog(blog);
		System.out.println("Blog Created");*/
		
		/*ForumDAO forumDAO = (ForumDAO) context.getBean("forumDAO");
		Forum forum = new Forum();
		forum.setCreateDate(new Date());
		forum.setForumContent("Create Your Next Group");
		forum.setForumName("Blog Forum");
		forum.setStatus("NA");
		forum.setUserName("Neeraj Sharma");
		
		forumDAO.saveForum(forum);
		System.out.println("Forum Created");*/
		
		/*NotificationDAO notificationDAO = (NotificationDAO) context.getBean("notificationDAO");
		Notification notification = new Notification();*/
		
		/*BlogPost blogDAO = (BlogPost) context.getBean("blogPostDAO");
		BlogPost blogPost = new BlogPost();*/
		
		/*BlogCommentDAO blogCommentDAO = (BlogCommentDAO) context.getBean("blogCommentDAO");
		BlogComment blogComment = new BlogComment();*/
		
		/*FriendDAO friendDAO = (FriendDAO) context.getBean("friendDAO");
		Friend friend = new Friend();*/
		
		/*ProfilePictureDAO profilePictureDAO = (ProfilePictureDAO) context.getBean("profilePictureDAO");
		ProfilePicture profilePicture = new ProfilePicture();*/

		/*ChatDao chatDao = (ChatDao) context.getBean("chatDao");
		Chat chat = new Chat();*/
		
	}

}
