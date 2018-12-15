package com.niit.BlogBackEnd.DAO;

import java.util.List;

import com.niit.BlogBackEnd.model.Friend;
import com.niit.BlogBackEnd.model.User;

public interface FriendDAO {
	
	List<User> getSuggestedUsers(String emailId);
	void addFriendRequest(Friend friend);
	List<Friend> getPendingRequests(String emailId);
	void acceptFriendRequest(Friend friend);
	void deleteFriendRequest(Friend friend);
	List<User> listOfFriends(String emailId);
	

}
