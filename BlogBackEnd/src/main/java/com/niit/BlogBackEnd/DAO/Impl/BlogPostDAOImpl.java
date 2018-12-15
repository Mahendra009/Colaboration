 package com.niit.BlogBackEnd.DAO.Impl;

import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.BlogBackEnd.DAO.BlogPostDAO;
import com.niit.BlogBackEnd.model.BlogPost;

@Repository("blogPostDAO")
@Transactional
public class BlogPostDAOImpl implements BlogPostDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveBlog(BlogPost blogPost) {
		try
		{
			sessionFactory.getCurrentSession().save(blogPost);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateBlog(BlogPost blogPost) {
		try
		{
			sessionFactory.getCurrentSession().update(blogPost);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteBlog(BlogPost blogPost) {
		try
		{
			sessionFactory.getCurrentSession().delete(blogPost);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public BlogPost getBlog(int blogId) {
		try
		{
			Session session = sessionFactory.getCurrentSession();
			BlogPost blogPost = session.get(BlogPost.class, blogId);
			return blogPost;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BlogPost> listApprovedBlogs() {
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("From BlogPost Where approvalStatus = true");
			List<BlogPost> listapprovedBlogs = query.list();
			session.close();
			return listapprovedBlogs;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BlogPost> listBlogsWaitingForApproval() {
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("From BlogPost Where approvalStatus = false");
			List<BlogPost> listBlogsWaitingForApproval = query.list();
			session.close();
			return listBlogsWaitingForApproval;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
