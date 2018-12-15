app.factory('NotificationService',function($http){
	var notificationService={}
	var BASE_URL="http://localhost:8081/BlogMiddleWare"
		
	notificationService.getNotification=function(notificationID)
	{
		return $http.get(BASE_URL + "/notification/" + notificationID)
	}
	
	notificationService.listNotificationNotViewed= function()
	{
		return $http.get(BASE_URL + "/notifications")
	}
	
	notificationService.updateNotification= function(notificationID)
	{
		return $http.put(BASE_URL + "/updateNotification/" + notificationID)
	}

	return notificationService;

});

