package com.niit.BlogMiddleWare.RestController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.BlogBackEnd.DAO.JobDAO;
import com.niit.BlogBackEnd.DAO.UserDAO;
import com.niit.BlogBackEnd.model.ErrorClazz;
import com.niit.BlogBackEnd.model.Job;
import com.niit.BlogBackEnd.model.User;

@RestController
public class JobController {
	
	@Autowired
	private JobDAO jobDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	/*@PostMapping(value="/addJob")
	public ResponseEntity<?> addJob(@RequestBody Job job)
	{
		System.out.println("Adding Jobs");
		job.setLastDateApply(new Date());
		
		if(jobDAO.saveJob(job))
			return new ResponseEntity<String> ("Adding Job Successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String> ("Can't Add Job", HttpStatus.NOT_FOUND);
		
	}*/
	
	/*@PutMapping(value="/updateJob/{jobId}")
	public ResponseEntity<?> updateJob(@RequestBody Job job,@PathVariable("jobId") int jobId)
	{
		System.out.println("Update Job Successfully");
		Job job1 = jobDAO.getJob(jobId);
		job1.setCompanyName(job.getCompanyName());
		job1.setJobDescription(job.getJobDescription());
		job1.setJobDesignation(job.getJobDesignation());
		job1.setLastDateApply(new Date());
		job1.setLocation(job.getLocation());
		job1.setSalary(job.getSalary());
		
		if(jobDAO.updateJob(job1))
			return new ResponseEntity<String>("Update job successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Can not update job",HttpStatus.NOT_FOUND);
		
	}*/
	
	/*@DeleteMapping(value="/deleteJob/{jobId}")
	public ResponseEntity<?> deleteJob(@RequestBody Job job, @PathVariable("jobId") int jobId)
	{
		System.out.println("Delete Job");
		Job job1= jobDAO.getJob(jobId);
		
		if(jobDAO.deleteJob(job1))
			return new ResponseEntity<String>("Job Deleted Successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Can not delete job",HttpStatus.NOT_FOUND);
		
	}*/
	
	/*@GetMapping(value="/getJob/{jobId}")
	public ResponseEntity<?> getJob(@PathVariable("jobId") int jobId)
	{
		System.out.println("Get Job");
		Job job = jobDAO.getJob(jobId);
		
		if(job != null)
			return new ResponseEntity<String>("Get Job Successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Fail to get Job",HttpStatus.NOT_FOUND);
	}*/
	
	/*@GetMapping(value="/listJobs")
	public ResponseEntity<?> listJob()
	{
		System.out.println("Get All Job List");
		List<Job> listJobs = jobDAO.listJobs();
		
		if(listJobs != null)
			return new ResponseEntity<List<Job>> (listJobs,HttpStatus.OK);
		else
			return new ResponseEntity<String> ("Fail to get all list", HttpStatus.NOT_FOUND);
			
	}*/
	
	@PostMapping(value="/saveJob")
	public ResponseEntity<?> saveJob(@RequestBody Job job, HttpSession session)
	{
		
		String emailId = (String) session.getAttribute("loggedInUser");
		if (emailId == null)
		{
			ErrorClazz errorClazz = new ErrorClazz(4, "Unauthorised access..Please login with valid credentials");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		/*String emailId = "ilovemyindia.mahendra2009@gmail.com";*/
		// check for authorization (role)
		User user = userDAO.getUser(emailId);
		if(!user.getRole().equals("Admin"))
		{
			ErrorClazz errorClazz = new ErrorClazz(5, "Access Denied");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		try
		{
			/*job.setLastDateApply(new Date());*/
			jobDAO.saveJob(job);
		}
		catch(Exception e)
		{
			ErrorClazz errorClazz = new ErrorClazz(6, "Can not post job details");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Job>(job,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/getAllJobs")
	public ResponseEntity<?> getAllJobs(HttpSession session)
	{
		String emailId = (String) session.getAttribute("loggedInUser");
		
		if(emailId == null)
		{
			ErrorClazz errorClazz = new ErrorClazz(4, "Unauthorised access..Please login with valid credentials");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		List<Job> listJobs = jobDAO.listJobs();
		return new ResponseEntity<List<Job>>(listJobs,HttpStatus.OK);
	}
 
}
