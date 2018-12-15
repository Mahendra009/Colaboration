package com.niit.BlogBackEnd.DAO.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.BlogBackEnd.DAO.NotificationDAO;
import com.niit.BlogBackEnd.model.Notification;

@Repository("notificationDAO")
@Transactional
public class NotificationDAOImpl implements NotificationDAO {

	@Autowired
	private SessionFactory sessionFactory; 

	
	@Override
	public boolean addNotification(Notification notification) {
		try
		{
			sessionFactory.getCurrentSession().save(notification);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateNotification(int notificationID) {
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Notification notification = session.get(Notification.class, notificationID);
			notification.setViewed(true);
			session.update(notification);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteNotification(Notification notification) {
		try
		{
			sessionFactory.getCurrentSession().delete(notification);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Notification getNotification(int notificationID) {
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Notification notification = session.get(Notification.class,notificationID);
			return notification;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Notification> listNotificationNotViewed(String emailId) {
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Notification where emailId=:emailId and viewed=0")
					.setParameter("emailId", emailId);
			return query.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}


}
