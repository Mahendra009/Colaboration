package com.niit.BlogBackEnd.DAO.Impl;

import java.util.List;

import javax.transaction.Transactional;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.BlogBackEnd.DAO.BlogCommentDAO;
import com.niit.BlogBackEnd.model.BlogComment;


@Repository("blogCommentDAO")
@Transactional
public class BlogCommentDAOImpl implements BlogCommentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void addBlogComment(BlogComment blogComment) {
		Session session = sessionFactory.getCurrentSession();
		session.save(blogComment);

	}
	@Override
	public List<BlogComment> getBlogComments(int blogId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BlogComment where blogPost.blogId=:blogId");
		query.setParameter("blogId", blogId);
		List<BlogComment> blogComments= query.list();
		return blogComments;
		
	}
	
	/*query.setParameter("blogId", new Integer(blogId));
	List<BlogComment> blogComments= query.list();
	return blogComments;*/
	
	
	

}
