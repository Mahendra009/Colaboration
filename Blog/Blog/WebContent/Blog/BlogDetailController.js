app.controller('BlogDetailCtrl',function($scope,$location,BlogService,$routeParams,$sce)
	{
		var blogId=$routeParams.blogId
		
		BlogService.getBlog(blogId).then(
				function(response)
				{
					$scope.blogPost=response.data
					$scope.content=$sce.trustAsHtml($scope.blogPost.blogContent)
				},function(response)
				{
					if(response.status==401)
					$location.path("/login")
				})
	
				$scope.approveBlogPost = function(blogPost)
				{
					BlogService.approveBlogPost(blogPost).then(
					function(response)
					{
						$location.path("/listBlogsWaitingForApproval")
					},function(response)
					{
						if(response.status==401)
							$location.path("/login")
					})
				}
		
				$scope.rejectBlogPost = function (blogPost)
				{
					BlogService.rejectBlogPost(blogPost,$scope.rejectionReason).then(
							function(response)
							{
								$location.path("/listBlogsWaitingForApproval")
							},function(response)
							{
								if(response.status==401)
									$location.path("/login")
							})
				}
				
				$scope.showTextArea=function()
				{
					$scope.isRejected=!$scope.isRejected
				}
				
				$scope.addBlogComment= function(blog,commentText)
				{
					if(commentText == undefined || commentText == "")
						$scope.error='Please Enter comment first'	
					else
							
					BlogService.addBlogComment(blog,commentText).then
					(	
							function(response)
							{
								alert("your comment added successfully")
								$scope.commentText=""
								$scope.error=""	
								$scope.blogComment = response.data
							},function(response)
							{
								if(response.status==401)
									$location.path("/login")
							}
					)
				}

				$scope.getBlogComments = function(blogId)
				{
					BlogService.getBlogComments(blogId).then
					(
						function(response)
						{
							$scope.blogComments=response.data
						},
						function(response)
						{
							if(response.status==401)
								$location.path("/login")
						})
				}


});




