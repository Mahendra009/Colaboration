package com.niit.BlogBackEnd.DAO;

import java.util.List;

import com.niit.BlogBackEnd.model.User;

public interface UserDAO {
	
	public boolean saveUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(User user);
	public User getUser(String emailId);
	public List<User> listUsers();
	public boolean isEmailIDUnique(String emailId);
	
	public User login(User user);
	

}
