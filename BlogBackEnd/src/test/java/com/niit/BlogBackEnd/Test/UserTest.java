package com.niit.BlogBackEnd.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.BlogBackEnd.DAO.UserDAO;
import com.niit.BlogBackEnd.model.User;

public class UserTest {
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.BlogBackEnd");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
	}

	
	@Test
	public void testSaveUser() {
		User user = new User();
		user.setAddress("Aminabad");
		user.setCity("Lucknow");
		user.setCountry("India");
		user.setEmailId("ilovemyindia.mahendra2009@gmail.com");
		user.setEnable(true);
		user.setOnline(true);
		user.setName("Mahendra Srivastava");
		user.setPassword("maddy123");
		user.setPhone("9415016516");
		user.setRole("Admin");
		
		assertTrue("Problem in saving User", userDAO.saveUser(user));
		
	}

	@Ignore
	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetUser() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testListUsers() {
		fail("Not yet implemented");
	}

}
