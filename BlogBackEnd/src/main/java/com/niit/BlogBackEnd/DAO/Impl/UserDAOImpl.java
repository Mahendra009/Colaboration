package com.niit.BlogBackEnd.DAO.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.BlogBackEnd.DAO.UserDAO;
import com.niit.BlogBackEnd.model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl() {
		System.out.println("UserDAOImpl Bean Created");
	}

	@Override
	public boolean saveUser(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getUser(String emailId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			User user = session.get(User.class, emailId);
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> listUsers() {
		try {
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("FROM User");
			List<User> listUsers = query.list();
			session.close();
			return listUsers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean isEmailIDUnique(String emailId) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where emailId=:emailId")
													.setParameter("emailId",emailId);
		
		User user = (User) query.uniqueResult();
		if (user != null) {
			return false; //duplicate emailID
		} else {
			return true; //unique emailID
		}

	}

	@Override
	public User login(User user) {
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from User where emailId=:emailId and password=:password");
			query.setParameter("emailId", user.getEmailId());
			query.setParameter("password", user.getPassword());
			return (User) query.uniqueResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
