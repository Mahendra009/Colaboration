app.controller('HomeCtrl',function($rootScope,BlogService,$location,$scope)
	{
	
		BlogService.listNotificationNotViewed().then(
			function(response)
			{
				
				/*console.log(response.data)*/
				$rootScope.listNotificationNotViewed = response.data
				$rootScope.notificationCount = $rootScope.listNotificationNotViewed.length
				
				/*alert($rootScope.notificationCount)*/
			},function(response)
			{
				if(response.status==401)
					$location.path('/login')
				$scope.error=response.data
			})
	




})


