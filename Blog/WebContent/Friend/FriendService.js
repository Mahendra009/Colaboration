app.factory('FriendService',function($http){
	
	var friendService = {}
	var BASE_URL="http://localhost:8081/BlogMiddleWare"
		
		friendService.getSuggestedUsers= function()
			{
			return $http.get(BASE_URL + "/suggestedUsers")
			}
	
		friendService.sendFriendRequest = function(toId)
			{
				return $http.post(BASE_URL + "/sendRequest",toId) // here toId is a user object 
			}
			
		friendService.getPendingRequests= function()
			{
				return $http.get(BASE_URL + "/pendingRequests")
			}
			
		friendService.acceptRequest= function(pendingRequest)
			{
				console.log(pendingRequest);
				return $http.put(BASE_URL + "/acceptRequest",pendingRequest)
			}
			
		friendService.deleteRequest= function(pendingRequest)
			{
				return $http.put(BASE_URL + "/deleteRequest",pendingRequest)
			}
			
		friendService.listOfFriends=function()
			{
				return $http.get(BASE_URL + "/listOfFriends")
			}
	
	
	return friendService;




})

