package com.niit.BlogMiddleWare.RestController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.BlogBackEnd.DAO.ProfilePictureDAO;
import com.niit.BlogBackEnd.model.ErrorClazz;
import com.niit.BlogBackEnd.model.ProfilePicture;

@RestController
public class ProfilePictureController {
	
	@Autowired
	private ProfilePictureDAO profilePictureDAO;
	
	@PostMapping(value="/uploadProfilePicture")
	public ResponseEntity<?>uploadProfilePicture(HttpSession session,@RequestParam CommonsMultipartFile image)
	{
		String emailId = (String) session.getAttribute("loggedInUser");
		if(emailId == null)
			{
				ErrorClazz errorClazz = new ErrorClazz(5, "Unauthorised access..Please login with valid credentials");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
		
		ProfilePicture profilePicture = new ProfilePicture();
		profilePicture.setEmailId(emailId);
		profilePicture.setImage(image.getBytes());
		profilePictureDAO.uploadProfilePicture(profilePicture);
		return new ResponseEntity<ProfilePicture>(profilePicture,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/getimage")
	public @ResponseBody byte[] getProfilePicture(HttpSession session,@RequestParam String emailId)
	{
		System.out.println("EmailId is" + emailId);
		String authEmailId =  (String) session.getAttribute("loggedInUser");
		if(authEmailId == null)
		{
			return null;
		}
		
		ProfilePicture profilePicture = profilePictureDAO.getProfilePicture(emailId);
		if(profilePicture == null)
			return null;
		else
			return profilePicture.getImage();
	}

}
