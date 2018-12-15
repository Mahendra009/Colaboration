package com.niit.BlogMiddleWare.RestController;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.BlogBackEnd.DAO.ForumDAO;
import com.niit.BlogBackEnd.model.Forum;

@RestController
public class ForumController {
	
	@Autowired
	private ForumDAO forumDAO;
	
	@PostMapping(value="/addForum")
	public ResponseEntity<?> addForum(@RequestBody Forum forum)
	{
		System.out.println("Add Forum");
		forum.setCreateDate(new java.util.Date());
		forum.setForumContent("Troubleshoot");
		forum.setForumName("Consumer Forum");
		forum.setStatus("A");
		forum.setUserName("Amarjeet");
		
		if(forumDAO.saveForum(forum))
			return new ResponseEntity<String>("Forum Added Successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Fail to add Forum",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value="/updateForum/{forumId}")
	public ResponseEntity<?> updateForum(@RequestBody Forum forum, @PathVariable("forumId") int forumId)
	{
		System.out.println("Update Forum");
		Forum forum1 = forumDAO.getForum(forumId);
		forum1.setCreateDate(new Date());
		forum1.setForumContent(forum.getForumContent());
		forum1.setForumName(forum.getForumName());
		forum1.setStatus(forum.getStatus());
		forum1.setUserName(forum.getUserName());
		
		if (forumDAO.updateForum(forum1))
			return new ResponseEntity<String>("Update Forum Successfully", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Fail to update Forum",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(value="/deleteForum/{forumId}")
	public ResponseEntity<?> deleteForum(@RequestBody Forum forum, @PathVariable("forumId") int forumId)
	{
		System.out.println("Forum Deleted");
		Forum forum1 = forumDAO.getForum(forumId);
		
		if(forumDAO.deleteForum(forum1))
			return new ResponseEntity<String>("Delete Forum Successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Fail to deleTE Forum", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/getForum/{forumId}")
	public ResponseEntity<?> getForum(@PathVariable("forumId") int forumId)
	{
		System.out.println("Get Forum");
		Forum forum = forumDAO.getForum(forumId);
		
		if(forum !=null)
			return new ResponseEntity<String>("Get the forum Successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Fail to get Forum",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/listForum")
	public ResponseEntity<?> listForum()
	{
		System.out.println("Get All The Forum");
		List<Forum> listForums = forumDAO.listForums("Neeraj Sharma");
		
		if(listForums != null)
			return new ResponseEntity<List<Forum>>(listForums,HttpStatus.OK);
		else
			return new ResponseEntity<String>("Fail to get list of Forum", HttpStatus.NOT_FOUND);
	}
	
}
