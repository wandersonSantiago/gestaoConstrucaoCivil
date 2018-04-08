
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	

	
	$routeProvider
	
	.when('/home', {
		templateUrl : "views/home.html"
	})
	.when('/404', {
		templateUrl : "views/erros/404.html"
	})
		.when('/login', {
		templateUrl : "views/login.html"
	})
	.when('/semAcesso', {
		templateUrl : "views/erros/permissao.html"
	})

	.when('/',{
		redirectTo : "/home"
	})
	
	
		.otherwise({
			redirectTo : "/404"
		});
	
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth ) {

auth.init('/', '/login', '/logout');
});



