package com.niit.BlogMiddleWare.RestController;

import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.niit.BlogBackEnd.DAO.FriendDAO;
import com.niit.BlogBackEnd.DAO.UserDAO;
import com.niit.BlogBackEnd.model.ErrorClazz;
import com.niit.BlogBackEnd.model.Friend;
import com.niit.BlogBackEnd.model.User;

@RestController
public class FriendController {
	
	@Autowired
	private FriendDAO friendDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping(value="/suggestedUsers")
	public ResponseEntity<?> getSuggestedFriend(HttpSession session)
	
	{
		String emailId = (String) session.getAttribute("loggedInUser");
		if(emailId == null)
			{
				ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
		
		/*String emailId = "amar123@gmail.com";*/
		List<User> suggestedUsers = friendDAO.getSuggestedUsers(emailId);
		return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
	}
	
		// create new object(friendId, fromId,toId,status)
	@PostMapping(value="/sendRequest")
	public ResponseEntity<?> addFriendRequest(@RequestBody User FriendRequestToId , HttpSession session)
	{
		String emailId = (String) session.getAttribute("loggedInUser");
		if(emailId == null)
			{
				ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			
				User fromId =	userDAO.getUser(emailId);
				Friend friend = new Friend();
				friend.setFromId(fromId);
				friend.setToId(FriendRequestToId);
				friend.setStatus('P');
				
				friendDAO.addFriendRequest(friend);
				return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/pendingRequests")
	public ResponseEntity<?> getPendingRequests(HttpSession session)
	{
		String emailId = (String) session.getAttribute("loggedInUser");
		if(emailId == null)
			{
				ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			/*String emailId = "vikas123@gmail.com";*/
			List<Friend> pendingRequests = friendDAO.getPendingRequests(emailId);
			return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
	}
	
		@PutMapping(value="/acceptRequest")
		public ResponseEntity<?> acceptFriendRequest(@RequestBody Friend friend,HttpSession session)
		{
			System.out.println("Friend ID is :" + friend.getFriendId());
			String emailId = (String) session.getAttribute("loggedInUser");
			
			if(emailId == null)
				{
					ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
					return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
				}
			
			friend.setStatus('A');
			
			friendDAO.acceptFriendRequest(friend);
			
			return new ResponseEntity<Friend>(friend,HttpStatus.OK);
			
			
		}
		
		@PutMapping(value="/deleteRequest")
		public ResponseEntity<?> deleteFriendRequest(@RequestBody Friend friend,HttpSession session)
		{
			System.out.println("Friend ID is :" + friend.getFriendId());
			String emailId = (String) session.getAttribute("loggedInUser");
			if(emailId == null)
				{
					ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
					return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
				}
			friendDAO.deleteFriendRequest(friend);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		@GetMapping(value="/listOfFriends")
		public ResponseEntity<?> listOfFriends(HttpSession session)
		{
			String emailId = (String) session.getAttribute("loggedInUser");
			if(emailId == null)
				{
					ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
					return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
				}
			/*String emailId = "vikas123@gmail.com";*/
			List<User> friendsDetails= friendDAO.listOfFriends(emailId);
			return new ResponseEntity<List<User>>(friendsDetails,HttpStatus.OK);
		}

}
