package com.niit.BlogBackEnd.DAO;

import com.niit.BlogBackEnd.model.ProfilePicture;

public interface ProfilePictureDAO {
	
	public boolean uploadProfilePicture(ProfilePicture profilePicture);
	public ProfilePicture getProfilePicture(String emailId);

}
