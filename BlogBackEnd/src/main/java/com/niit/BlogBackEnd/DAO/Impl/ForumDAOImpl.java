package com.niit.BlogBackEnd.DAO.Impl;

import java.util.List;

import javax.transaction.Transactional;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.BlogBackEnd.DAO.ForumDAO;
import com.niit.BlogBackEnd.model.Forum;

@Repository("forumDAO")
@Transactional
public class ForumDAOImpl implements ForumDAO {
	
	@Autowired
	private SessionFactory sessionFactory; 

	@Override
	public boolean saveForum(Forum forum) {
		try
		{
			sessionFactory.getCurrentSession().save(forum);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateForum(Forum forum) {
		try
		{
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteForum(Forum forum) {
		try
		{
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Forum getForum(int forumId) {
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Forum forum = session.get(Forum.class, forumId);
			return forum;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Forum> listForums(String userName) {
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("FROM Forum where userName=:username");
			query.setParameter("username", userName);
			List<Forum> listForums = query.list();
			session.close();
			return listForums;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
