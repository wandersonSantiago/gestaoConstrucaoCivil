
app.controller('LoginController', function($rootScope, $scope, $location, $http, $state, Auth,  blockUI) {
	
	$scope.user = [];
	 
	  $scope.login = function() {
		  blockUI.start();
		  Auth.login($scope.user, function() {
	        if ($rootScope.authenticated) {
	          $scope.mensagemErro = false;
	          $state.go("home");
	        } else {
	          $scope.mensagemErro = true;
	          $state.go("login");
	        }
	        blockUI.stop();
	      });
	  };
	
		$scope.logout = function(){
			Auth.logout();
		};
	
}).run(function($rootScope, $http, $location, $state, $urlRouter, Auth) {
	  Auth.init();
	  $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams, options) {
		     if (!Auth.checkPermissionForView(toState)) {
		    	 event.preventDefault(); 
		         $state.go('/500');
		     }  
		   });
});
