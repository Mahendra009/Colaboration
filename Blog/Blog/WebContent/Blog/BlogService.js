app.factory('BlogService',function($http)
{
	var blogService = {}
	var BASE_URL="http://localhost:8081/BlogMiddleWare"
	blogService.addBlogPost= function(blogPost)
	{
		var url = BASE_URL + "/saveBlog"
		return $http.post (url,blogPost)
	}
	
	blogService.listapprovedBlogs=function()
	{
		var url=BASE_URL + "/listBlogs"
		return $http.get(url)
	}
	
	blogService.getBlog=function(blogId)
	{
		return $http.get(BASE_URL + "/getBlog/" + blogId)
	}
		
	blogService.listBlogsWaitingForApproval= function()
	{
		return $http.get(BASE_URL + "/listBlogsWaitingForApproval")
	}

	blogService.approveBlogPost = function(blogPost)
	{
		return $http.put(BASE_URL + "/updateBlog",blogPost)
	}
	
	blogService.rejectBlogPost = function(blogPost,rejectionReason)
	{
		return $http.put(BASE_URL + "/deleteBlog?rejectionReason="+rejectionReason,blogPost)
	}
	
	blogService.listNotificationNotViewed= function()
	{
		return $http.get(BASE_URL + "/notifications")
	}
	
	// services for blog comment
	
	// blog is blogPost object , commentText is String
	blogService.addBlogComment=function(blog,commentText)
	{
		return $http.post(BASE_URL + "/addComment?commentText=" + commentText,blog)
	}

	blogService.getBlogComments= function(blogId)
	{
		return $http.get(BASE_URL + "/listComment/" + blogId)
	}
	return blogService;

});

