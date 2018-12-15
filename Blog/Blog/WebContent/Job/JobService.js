
app.factory('JobService',function($http){
	
	var jobService={}
	var BASE_URL="http://localhost:8081/BlogMiddleWare"
	jobService.saveJob=function(job)
	{
		var url= BASE_URL + "/saveJob"
		return $http.post(url,job)
	}
	
	jobService.listJobs = function(job)
	{
		var url= BASE_URL + "/getAllJobs"
		return $http.get(url,job)
	}
	
	
	return jobService;
	
	
	
	
})