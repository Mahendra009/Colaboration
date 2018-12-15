/*package com.niit.BlogBackEnd.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.BlogBackEnd.DAO.BlogPostDAO;
import com.niit.BlogBackEnd.DAO.UserDAO;
import com.niit.BlogBackEnd.model.BlogPost;


public class BlogTest {
	
	private static AnnotationConfigApplicationContext context;
	private static BlogPostDAO blogPostDAO; 
	private static UserDAO userDAO; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.BlogBackEnd");
		context.refresh();
		blogPostDAO = (BlogPostDAO) context.getBean("blogPostDAO");
	}

	
	@Test
	public void testSaveBlog() {
		BlogPost blogPost = new BlogPost();
		blogPost.setApprovalStatus(true);
		blogPost.setBlogContent("Animation");
		blogPost.setBlogTitle("Animation World");
		blogPost.setLikes(500000);
		blogPost.setPostedBy(userDAO.getUser("emailId"));
		blogPost.setPostedOn(new Date());
		
		
		assertTrue("Problem in adding Blog", blogPostDAO.saveBlog(blogPost));
	}

	@Ignore
	@Test
	public void testUpdateBlog() {
		Blog blog = BlogPostDAO.getBlog(-4);
		blog.setApprovalStatus(true);
		
		assertTrue("Problem in Updating Blog", BlogPostDAO.updateBlog(blog));
	}

	@Ignore
	@Test
	public void testDeleteBlog() {
		Blog blog = BlogPostDAO.getBlog(-7);
		assertTrue("Problem in deleting Blog", BlogPostDAO.deleteBlog(blog));
	}

	@Ignore
	@Test
	public void testGetBlog() {
		System.out.println("3d Games");
		
		assertNotNull("Problem in getting Blog", BlogPostDAO.getBlog(12));
	}

	@Ignore
	@Test
	public void testListBlogs() {
		List<Blog> listBlogs = BlogPostDAO.listBlogs();
		assertTrue("Problem in getting List", BlogPostDAO.listBlogs().size()>0);
		
		for (Blog blog:listBlogs)
		{
			System.out.println(blog.getBlogContent()+":::");
			System.out.println(blog.getBlogId()+":::");
			System.out.println(blog.getBlogName()+":::");
			System.out.println(blog.getLikes()+":::");
			System.out.println(blog.getStatus()+":::");
			System.out.println(blog.getUserName()+":::");
			System.out.println(blog.getCreateDate()+":::");
		}
		
	}

}
*/