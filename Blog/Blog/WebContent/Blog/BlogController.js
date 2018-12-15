app.controller('BlogCtrl',function($scope,$location,BlogService)
{
	$scope.addBlogPost=function(blogPost)
	{
		BlogService.addBlogPost(blogPost).then(
				function(response)
				{
					alert("Your Blog added successfully and it is waiting for approval")
					$location.path('/home')
				},function(response)
				{
					if(response.status==401)
						$location.path('/login')
					$scope.error=response.data
				})
	}
	
	// list of approved Blogs.
	
	function listapprovedBlogs()
	{
		
		BlogService.listapprovedBlogs().then(
			function(response)
			{
				
				$scope.approvedBlogs=response.data // approvedBlogs is a new variable in which we are storing responce.data.
			},function(response)
			{
				if(response.status==401)
					$location.path('/login')
			})
	
	}
	
	// list of list of approved Blogs too and waiting for approval
			function listBlogsWaitingForApproval()
				{
					BlogService.listBlogsWaitingForApproval().then(
							function(response)
							{
								$scope.blogsWaitingForApproval= response.data
							},function(response)
							{
								if(response.status==401)
									$location.path('/login')
							})
				}
	
	
		listapprovedBlogs()
		
		// call the function only for admin role.
		if($scope.user.role=='Admin')
		listBlogsWaitingForApproval()
	
	
});