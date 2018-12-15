package com.niit.BlogBackEnd.DAO.Impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.BlogBackEnd.DAO.ProfilePictureDAO;
import com.niit.BlogBackEnd.model.ProfilePicture;

@Repository("profilePictureDAO")
@Transactional
public class ProfilePictureDAOImpl implements ProfilePictureDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean uploadProfilePicture(ProfilePicture profilePicture) {
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(profilePicture);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public ProfilePicture getProfilePicture(String emailId) {
		try
		{
			Session session = sessionFactory.getCurrentSession();
			ProfilePicture profilePicture = session.get(ProfilePicture.class,emailId);
			return profilePicture;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
