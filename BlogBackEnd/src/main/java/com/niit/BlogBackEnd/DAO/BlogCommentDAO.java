package com.niit.BlogBackEnd.DAO;

import java.util.List;

import com.niit.BlogBackEnd.model.BlogComment;

public interface BlogCommentDAO {
	
	void addBlogComment(BlogComment blogComment);
	List<BlogComment> getBlogComments(int blogId);

}
