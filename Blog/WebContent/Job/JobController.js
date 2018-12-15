app.controller('JobCtrl',function($scope,$location,JobService){
	$scope.showJob = false
 $scope.saveJob=function(job)
 {
		console.log(job);
	 // to save jobs
	 JobService.saveJob(job).then(
			 function(response)
			 {
				alert("Job Detail Inserted Successfully") 
				$location.path('/home')
			 },
			 function(response)
			 {
				if(response.data.error==4)
					$location.path('/login')
				$scope.error=response.data
			 }) 
 }
 
 		// list jobs and data from controller to view
 		JobService.listJobs().then(
 				function(response)
 				{
 					$scope.jobs = response.data
 					
 				}, 
 				function(response)
 				{
 					if(response.status == 401)
 						$location.path('/login')	
 				})
	
	
 				$scope.showJobDetail = function(jobId)
 				{
 					$scope.jobId = jobId
 					$scope.showJob =! $scope.showJob // true-false, false true.
 				}
	
	
	
})