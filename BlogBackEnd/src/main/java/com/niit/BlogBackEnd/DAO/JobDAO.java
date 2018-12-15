package com.niit.BlogBackEnd.DAO;

import java.util.List;

import com.niit.BlogBackEnd.model.Job;

public interface JobDAO {
	
	public boolean saveJob(Job job);
    public boolean deleteJob(Job job);
    public boolean updateJob(Job job);
    public Job getJob(int jobId);
    public List<Job> listJobs();

}
