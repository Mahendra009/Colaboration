
app.factory('UserService',function($http)
{
	var userService={}
	var BASE_URL="http://localhost:8081/BlogMiddleWare"
		
		// for sign-up user
		userService.saveUser=function(user)
		{
		var url=BASE_URL+"/saveUser"
		return $http.post(url,user)
		}
	
		// for login user
	userService.login=function(user)
	{
		var url = BASE_URL+"/login"
		return $http.put(url,user)
	}
	
		// for logout user
	userService.logout=function()
	{
		var url = BASE_URL+"/logout"
		return $http.put(url)
	}
	
	userService.updateProfile = function(user)
	{
		var url = BASE_URL+"/updateProfile"
		return $http.put(url,user)
	}
	
	
	return userService;
})