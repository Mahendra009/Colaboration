package com.niit.BlogBackEnd.DAO.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.BlogBackEnd.DAO.FriendDAO;
import com.niit.BlogBackEnd.model.Friend;
import com.niit.BlogBackEnd.model.User;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> getSuggestedUsers(String emailId) {
		try
		{
			Session session = sessionFactory.getCurrentSession();
			NativeQuery query = session.createSQLQuery("select * from User_Detail where emailId in "
					+ "(select emailId from User_Detail where emailId!=:emailId"
					+ " minus (select fromId_emailId from Friend_Detail where toId_emailId=:emailId "
					+ "union"
					+ " select toId_emailId from Friend_Detail where fromId_emailId=:emailId))");
			query.setParameter("emailId", emailId);
			query.setParameter("emailId", emailId);
			query.setParameter("emailId", emailId);
			query.addEntity(User.class);
			return query.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
			
			
		}
	}

	@Override
	public void addFriendRequest(Friend friend) {
		try
		{
			sessionFactory.getCurrentSession().save(friend);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
	}

	@Override
	public List<Friend> getPendingRequests(String emailId) {
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Friend f where f.toId.emailId=:emailId and f.status='P'");
			query.setParameter("emailId" , emailId);
			List<Friend> pendingRequests = query.list();
			return pendingRequests;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void acceptFriendRequest(Friend friend) {
		try
		{
			sessionFactory.getCurrentSession().update(friend);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteFriendRequest(Friend friend) {
		try
		{
			sessionFactory.getCurrentSession().delete(friend);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public List<User> listOfFriends(String emailId) {
		try
		{
			// friend request from loggedin User to other users and if it is accepted 
			Session session = sessionFactory.getCurrentSession();
			Query query1 = session.createQuery("select f.toId from Friend f where f.fromId.emailId=:emailId and f.status='A'");
			query1.setParameter("emailId", emailId);
			
			List<User> list1 = query1.list();
			// f is alias
			Query query2 = session.createQuery("select f.fromId from Friend f where f.toId.emailId=:emailId and f.status='A'");
			query2.setParameter("emailId", emailId);
			
			List<User> list2 = query2.list();
			
			list1.addAll(list2);
			return list1;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
