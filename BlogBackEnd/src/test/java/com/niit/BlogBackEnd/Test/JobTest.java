package com.niit.BlogBackEnd.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.BlogBackEnd.DAO.JobDAO;
import com.niit.BlogBackEnd.model.Job;

public class JobTest {
	
	private static AnnotationConfigApplicationContext context;
	private static JobDAO jobDAO;
	private static Job job;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.BlogBackEnd");
		context.refresh();
		job = new Job();
		jobDAO = (JobDAO) context.getBean("jobDAO");
	}

	
	@SuppressWarnings("deprecation")
	
	@Test
	public void testSaveJob() {
		
		Scanner sc =new Scanner(System.in);
		System.out.println("Please input last date of job day month and year one by one");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		job.setCompanyName("NIIT.LTD");
		job.setExperience("5 years Experience in Technology");
		job.setJobDescription("Developer");
		job.setJobDesignation("Web Developer");
		job.setJobQualification("Must have knowledge of JAVA,JAVASCRIPT,HTML5");
		job.setJobTitle("Required Web developer");
		job.setLocation("Gurugram");
		job.setSalary(18000);
		Date date = new Date();
		date.setDate(sc.nextInt());
		date.setMonth(sc.nextInt());
		date.setYear(sc.nextInt());
		job.setLastDateApply(date);
		
		
		assertTrue("Problem in saving Job", jobDAO.saveJob(job));
		
	}

	@Ignore
	@Test
	public void testDeleteJob() {
		job = jobDAO.getJob(-7);
		
		assertTrue("Problem in deleting Job", jobDAO.deleteJob(job));
	}

	@Ignore
	@Test
	public void testUpdateJob() {
		job = jobDAO.getJob(-7);
		job.setJobDescription("Recruter");
		
		assertTrue("Problem in updating Job", jobDAO.updateJob(job));
	}

	@Ignore
	@Test
	public void testGetJob() {
		
		System.out.println("HR");
		assertNotNull("Problrm in getting Job", jobDAO.getJob(-6));
	}

	
	@Test
	public void testListJobs() {
		List<Job> listJobs = jobDAO.listJobs();
		assertTrue("Problem to get entire Job list", jobDAO.listJobs().size()>0);
		
		for(Job job:listJobs)
		{
			System.out.println(job.getCompanyName()+":::");
			System.out.println(job.getJobDescription()+":::");
			System.out.println(job.getJobDesignation()+":::");
			System.out.println(job.getJobId()+":::");
			System.out.println(job.getLocation()+":::");
			System.out.println(job.getSalary()+":::");
			System.out.println(job.getLastDateApply()+":::");
		}
	}

}
