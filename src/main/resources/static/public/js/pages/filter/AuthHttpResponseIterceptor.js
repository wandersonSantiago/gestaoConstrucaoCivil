app.factory('authHttpResponseInterceptor',['$q','$location','$rootScope','$injector', 'blockUI', function($q, $location, $rootScope,$injector, blockUI){
    return {
        response: function(response){
            if (response.status === 401) {
            }
            return response || $q.when(response);
        },
        responseError: function(rejection) {
        	var state = $injector.get("$state");
            if (rejection.status === 401) {
            	$rootScope.user  = null;
            	$rootScope.authenticated = false;
            	state.go('login', {'returnTo': $location.path()});
            }
            blockUI.stop();
            return $q.reject(rejection);
        }
    };
}])
.config(['$httpProvider',function($httpProvider) {
    $httpProvider.interceptors.push('authHttpResponseInterceptor');
}]);