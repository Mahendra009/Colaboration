app.controller('FriendCtrl',function($scope,$location,FriendService){
			function getSuggestedUsers()
			{
				FriendService.getSuggestedUsers().then
				(
						function(response)
						{
							$scope.suggestedUsers= response.data
						},function(response)
						{
							if(response.status==401)
								$location.path('/login')
						}
				)
			}
			$scope.sendFriendRequest=function(FriendRequestToId) // toId is an user object, friend.setToId(toId)
			{
				FriendService.sendFriendRequest(FriendRequestToId).then
				(
						function(response)
						{
							alert('Friend Request has been send successfully')
							getSuggestedUsers()
						},function(response)
						{
							if(response.status==401)
								$location.path('/login')
						})
			}

			function getPendingRequests()
			{
				/*alert('Entering pending')*/
				FriendService.getPendingRequests().then
				(
						function(response)
						{
							$scope.pendingRequests = response.data
						},function(response)
						{
							if(response.status==401)
								$location.path('/login')
						}
				)
			}
				
			$scope.acceptRequest=function(pendingRequest)// pendingRequest is object of type friend 
			{
				console.log(pendingRequest)
				FriendService.acceptRequest(pendingRequest).then
				(
						function(response)
						{
							getPendingRequests()
						},function(response)
						{
							if(response.status==401)
								$location.path('/login')
						})
			}
				
				$scope.deleteRequest= function(pendingRequest)
				{
					FriendService.deleteRequest(pendingRequest).then
					(
							function(response)
							{
								getPendingRequests()
							},function(response)
							{
								if(response.status==401)
									$location.path('/login')
							})
				}
				
				FriendService.listOfFriends().then
				(
						function(response)
						{
							$scope.friendsDetails=response.data
						},
						function(response)
						{
							if(response.status==401)
								$location.path('/login')
						})
				
				
			getSuggestedUsers()
			getPendingRequests()
});
