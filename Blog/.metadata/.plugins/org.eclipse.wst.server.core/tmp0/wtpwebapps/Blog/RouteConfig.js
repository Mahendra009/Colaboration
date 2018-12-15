var app = angular.module('myBlog', ['ngRoute','ngCookies']);
app.config (function($routeProvider)
{
	alert("Route Provider");
	
	$routeProvider
	.when("/home",{controller:'HomeCtrl',templateUrl : "Home.html"})
	.when("/login",{controller:'UserCtrl',templateUrl : "User/Login.html"})
	.when("/register",{controller:'UserCtrl',templateUrl : "User/Register.html"})
	.when("/updateProfile",{controller:'UserCtrl',templateUrl : "User/UpdateUser.html"})
	.when("/job",{controller:'JobCtrl',templateUrl : "Job/JobForm.html"})
	.when("/listJobs",{controller:'JobCtrl',templateUrl : "Job/ViewJobs.html"})
	.when("/addBlog",{controller:'BlogCtrl',templateUrl : "Blog/BlogForm.html"})
	.when("/listApprovedBlogs",{controller:'BlogCtrl',templateUrl : "Blog/ListApprovedBlog.html"})
	.when("/getBlog/:blogId",{controller:'BlogDetailCtrl',templateUrl : "Blog/BlogDetail.html"})
	.when("/listBlogsWaitingForApproval",{controller:'BlogCtrl',templateUrl : "Blog/ListBlogsWaitingForApproval.html"})
	.when("/listBlogsWaitingForApproval/:blogId",{controller:'BlogDetailCtrl',templateUrl : "Blog/BlogApprovalForm.html"})
	.when("/getNotification/:notificationID",{controller:'NotificationCtrl',templateUrl : "Notification/NotificationDetail.html"})
	.when("/suggestedUsers",{controller:'FriendCtrl',templateUrl : "Friend/SuggestedUsers.html"})
	.when("/pendingRequests",{controller:'FriendCtrl',templateUrl : "Friend/PendingRequests.html"})
	.when("/listOfFriends",{controller:'FriendCtrl',templateUrl : "Friend/FriendList.html"})
	.when("/uploadProfilePicture",{templateUrl : "ProfilePicture/UploadProfilePicture.html"})
	.when("/chat",{templateUrl : "Chat/Chat.html"})
	.otherwise({templateUrl : "Home.html"}) 
	
});
// Angular Module gets instantiated , app.run will execute
// restore userDetail to the variable user in  $rootScope.
app.run(function($rootScope, $cookieStore,UserService,$location){
			if($rootScope.user == undefined)
				$rootScope.user =  $cookieStore.get('userDetails');
			
			$rootScope.logout=function()
			{
				/* alert('Entering in logout function')*/
				 UserService.logout().then(
						 function(response){
						// remove the user from $rootScope.
						//	clear the cookie
						//	redirect the user to login page
							 
							 delete $rootScope.user
							 $cookieStore.remove('userDetails')
							$location.path('/login')
						 },function(response){
						
							 //	 if $rootScope.user is not undefined then clear the cookies and redirect to login page.
							 if($rootScope.user != undefined)
							 delete $rootScope.user
							 $cookieStore.remove('userDetails')
							 $location.path('/login')
						 })
			}
	
});