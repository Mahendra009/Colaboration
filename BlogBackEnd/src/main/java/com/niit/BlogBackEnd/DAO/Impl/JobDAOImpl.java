package com.niit.BlogBackEnd.DAO.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.BlogBackEnd.DAO.JobDAO;
import com.niit.BlogBackEnd.model.Job;

@Repository("jobDAO")
@Transactional

public class JobDAOImpl implements JobDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveJob(Job job) {
		try
		{
			sessionFactory.getCurrentSession().save(job);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteJob(Job job) {
		try
		{
			sessionFactory.getCurrentSession().delete(job);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateJob(Job job) {
		try
		{
			sessionFactory.getCurrentSession().update(job);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Job getJob(int jobId) {
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Job job = session.get(Job.class, jobId);
			return job;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Job> listJobs() {
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("From Job");
			List<Job> listJobs = query.list();
			session.close();
			return listJobs;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
