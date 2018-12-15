package com.niit.BlogMiddleWare.RestController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.BlogBackEnd.DAO.NotificationDAO;
import com.niit.BlogBackEnd.model.ErrorClazz;
import com.niit.BlogBackEnd.model.Notification;

@RestController
public class NotificationController {
	
	@Autowired
	private NotificationDAO notificationDAO;
	
	@GetMapping(value = "/notifications")
	public ResponseEntity<?> listNotificationNotViewed(HttpSession session)
	{
		String emailId = (String) session.getAttribute("loggedInUser");
		if(emailId == null)
			{
				ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
		
		/*String emailId = "ilovemyindia.mahendra2009@gmail.com";*/
		List<Notification> listNotificationNotViewed = notificationDAO.listNotificationNotViewed(emailId);
		return new ResponseEntity<List<Notification>> (listNotificationNotViewed,HttpStatus.OK);
	}
	
	@GetMapping(value="/notification/{notificationID}")
	public ResponseEntity<?> getNotification(@PathVariable("notificationID") int notificationID,HttpSession session)
	{
		String emailId = (String) session.getAttribute("loggedInUser");
		if(emailId == null)
			{
				ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
		Notification notification = notificationDAO.getNotification(notificationID);
		return new ResponseEntity<Notification>(notification,HttpStatus.OK);
	}
	
	@PutMapping(value="/updateNotification/{notificationID}")
	public ResponseEntity<?> updateNotification(@PathVariable("notificationID") int notificationID,HttpSession session)
	{
		String emailId = (String) session.getAttribute("loggedInUser");
		if(emailId == null)
			{
				ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
		notificationDAO.updateNotification(notificationID);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	

}
