app.controller('NotificationCtrl',function($scope,$routeParams,NotificationService,$location,$rootScope)
	{
		var notificationID = $routeParams.notificationID
		
		NotificationService.getNotification(notificationID).then(
				function(response)
				{
					$scope.notification=response.data
				},function(response)
				{
					if(response.status==401)
						$location.path("/login")
				})
	
				NotificationService.updateNotification(notificationID).then(
						function(response)
						{
							listNotificationNotViewed()
						},function(response)
						{
							if(response.status==401)
								$location.path("/login")
						})
						
					function listNotificationNotViewed()
					{
						NotificationService.listNotificationNotViewed().then(
								function(response)
								{
									$rootScope.listNotificationNotViewed=response.data
									$rootScope.notificationCount=$rootScope.listNotificationNotViewed.length
								},function(response)
								{
									if(response.status==401)
										$location.path("/login")
								})
					}



});


