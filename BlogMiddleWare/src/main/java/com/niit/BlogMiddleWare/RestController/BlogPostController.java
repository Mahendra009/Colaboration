package com.niit.BlogMiddleWare.RestController;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.BlogBackEnd.DAO.BlogPostDAO;
import com.niit.BlogBackEnd.DAO.NotificationDAO;
import com.niit.BlogBackEnd.DAO.UserDAO;
import com.niit.BlogBackEnd.model.BlogPost;
import com.niit.BlogBackEnd.model.ErrorClazz;
import com.niit.BlogBackEnd.model.Notification;
import com.niit.BlogBackEnd.model.User;

@RestController
public class BlogPostController {
	
	@Autowired
	private BlogPostDAO blogPostDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private NotificationDAO notificationDAO;
	
	@GetMapping(value="/demo")
	public ResponseEntity<?> demoCheck()
	{
		return new ResponseEntity<>("Demo Success",HttpStatus.OK);
	}
	
	@PostMapping(value="/saveBlog")
	public ResponseEntity<?> addBlog(@RequestBody BlogPost blogPost,HttpSession session)
	{
		System.out.println("Adding Blog");
		System.out.println(blogPost.getBlogTitle());
		String emailId = (String) session.getAttribute("loggedInUser");
		if(emailId == null)
			{
				ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
		/*String emailId = "amar123@gmail.com";*/
		blogPost.setPostedOn(new Date());
		blogPost.setPostedBy(userDAO.getUser(emailId));
		
		try
		{
			blogPostDAO.saveBlog(blogPost);
			
		}
		catch(Exception e)
		{
			ErrorClazz errorClazz = new ErrorClazz(6, "Unable To post your Blog" + e.getMessage());
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		
		
		
	}
	
	
	@PutMapping(value="/updateBlog")
	public ResponseEntity<?> updateBlog(@RequestBody BlogPost blogPost,HttpSession session)
	{
		String emailId = (String) session.getAttribute("loggedInUser");
		if(emailId == null)
			{
				ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
		
		User user = userDAO.getUser(emailId);
 		if(!user.getRole().equals("Admin"))
 		{
 			ErrorClazz errorClazz = new ErrorClazz(6, "Access Denied");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
 		}
 		
 		blogPost.setApprovalStatus(true);
 		blogPostDAO.updateBlog(blogPost);
 		Notification notification = new Notification();
 		notification.setApprovalStatus("Approved");
 		notification.setBlogTitle(blogPost.getBlogTitle());
 		notification.setEmailId(blogPost.getPostedBy().getEmailId());
 		notificationDAO.addNotification(notification);
 		
 		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		
	}
	
	@PutMapping(value="/deleteBlog") //delete mapping is not working thats why using putMapping here.
	public ResponseEntity<?> deleteBlog(@RequestBody BlogPost blogPost,@RequestParam String rejectionReason,HttpSession session)
	{
		String emailId = (String) session.getAttribute("loggedInUser");
		if(emailId == null)
			{
				ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
		User user = userDAO.getUser(emailId);
 		if(!user.getRole().equals("Admin"))
 		{
 			ErrorClazz errorClazz = new ErrorClazz(6, "Access Denied");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
 		}
 			/*blogPost.setApprovalStatus(true);*/
 			
 			Notification notification = new Notification();
 			notification.setApprovalStatus("Rejected");
 			notification.setBlogTitle(blogPost.getBlogTitle());
 			notification.setEmailId(blogPost.getPostedBy().getEmailId());
 			notification.setRejuctionReason(rejectionReason);
 			notificationDAO.addNotification(notification);
 			
 			blogPostDAO.deleteBlog(blogPost);
 			return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@GetMapping(value="/getBlog/{blogId}")
	public ResponseEntity<?> getBlog(@PathVariable("blogId") int blogId,HttpSession session)
	{
		String emailId = (String) session.getAttribute("loggedInUser");
		if(emailId == null)
			{
				ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
		BlogPost blogPost = blogPostDAO.getBlog(blogId);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/listBlogs")
	public ResponseEntity<?> listApprovedBlogs(HttpSession session)
	{
		String emailId = (String) session.getAttribute("loggedInUser");
		if(emailId == null)
			{
				ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
		
		 		List<BlogPost> listapprovedBlogs =	blogPostDAO.listApprovedBlogs();
		 		return new ResponseEntity<List<BlogPost>>(listapprovedBlogs,HttpStatus.OK);
		
		
	}
	
	@GetMapping(value="/listBlogsWaitingForApproval")
	public ResponseEntity<?> listBlogsWaitingForApproval(HttpSession session)
	{
		// Authentication
		String emailId = (String) session.getAttribute("loggedInUser");
		if(emailId == null)
			{
				ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
		
				// Authorization bcz only Admin can view list of blogs waiting for approval
				/*String emailId = "ilovemyindia.mahendra2009@gmail.com";*/
		 		User user = userDAO.getUser(emailId);
		 		if(!user.getRole().equals("Admin"))
		 		{
		 			ErrorClazz errorClazz = new ErrorClazz(6, "Access Denied");
					return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		 		}
		 		List<BlogPost> listBlogsWaitingForApproval = blogPostDAO.listBlogsWaitingForApproval();
		 		return new ResponseEntity<List<BlogPost>>(listBlogsWaitingForApproval,HttpStatus.OK);
		
		
	}
	

}
