package com.niit.BlogBackEnd.DAO;

import java.util.List;

import com.niit.BlogBackEnd.model.BlogPost;

public interface BlogPostDAO {
	
	public boolean saveBlog(BlogPost blogPost);
	public boolean updateBlog(BlogPost blogPost);
	public boolean deleteBlog(BlogPost blogPost);
	public BlogPost getBlog(int blogId);
	public List<BlogPost> listApprovedBlogs();
	public List<BlogPost> listBlogsWaitingForApproval();

}
