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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.BlogBackEnd.DAO.BlogCommentDAO;
import com.niit.BlogBackEnd.DAO.UserDAO;
import com.niit.BlogBackEnd.model.BlogComment;
import com.niit.BlogBackEnd.model.BlogPost;
import com.niit.BlogBackEnd.model.ErrorClazz;
import com.niit.BlogBackEnd.model.User;

@RestController
public class BlogCommentController {
	
	@Autowired
	private BlogCommentDAO blogCommentDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@PostMapping(value="/addComment")
	public ResponseEntity<?> addBlogComment(@RequestBody BlogPost blogPost,@RequestParam String commentText,HttpSession session)
	{
		String emailId = (String) session.getAttribute("loggedInUser");
		if(emailId == null)
		{
			ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		BlogComment blogComment = new BlogComment();
		blogComment.setCommentText(commentText);
		blogComment.setBlogPost(blogPost);
		User user = userDAO.getUser(emailId);
		blogComment.setCommentedBy(user);
		blogComment.setCommentedOn(new Date());
		blogCommentDAO.addBlogComment(blogComment);
		
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/listComment/{blogId}")
	public ResponseEntity<?> getBlogComments(@PathVariable("blogId") int blogId,HttpSession session)
	{
		String emailId = (String) session.getAttribute("loggedInUser");
		if(emailId == null)
		{
			ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		  List<BlogComment> blogComments = blogCommentDAO.getBlogComments(blogId);
		  return new ResponseEntity< List<BlogComment>>(blogComments,HttpStatus.OK); 
		
		
	}

}
