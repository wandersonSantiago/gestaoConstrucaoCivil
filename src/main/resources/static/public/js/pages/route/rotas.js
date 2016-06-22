app.config(['$routeProvider','$httpProvider', function($routeProvider, $httpProvider) {
	$routeProvider
	
	.when('/', {
		templateUrl : "views/home.html"
	})
	
		.when('/login', {
		templateUrl : "views/login.html"
	})

	
	.when('/cadastrarEmpreendimento', {
		templateUrl : "views/pages/adminEmpresa/cadastrarEmpreendimento.html",
	})

	.when('/cadastrarEmpresa', {
		templateUrl : "views/pages/adminSistema/cadastrarEmpresa.html",
	})
		
	
	//Rota de erro
	.otherwise({
		redirectTo : "/404"
	})
}]);

