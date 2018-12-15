package com.niit.BlogBackEnd.DAO;

import java.util.List;

import com.niit.BlogBackEnd.model.Forum;

public interface ForumDAO {
	
	public boolean saveForum(Forum forum);
	public boolean updateForum(Forum forum);
	public boolean deleteForum(Forum forum);
	public Forum getForum(int forumId);
	public List<Forum> listForums(String userName);

}
