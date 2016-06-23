app.config(['$routeProvider','$httpProvider', function($routeProvider, $httpProvider) {
	$routeProvider
	
	.when('/', {
		templateUrl : "views/home.html"
	})
	
		.when('/login', {
		templateUrl : "views/login.html"
	})

	//Rotas Administrador Sistema
	.when('/cadastrarEmpresa', {
		templateUrl : "views/pages/adminSistema/cadastrarEmpresa.html",
	})
	
	.when('/cadastrarAdminEmpresa', {
		templateUrl : "views/pages/adminSistema/cadastrarAdmiEmpresa.html",
	})
	
	
	//Rotas Administrador Empresa
	.when('/cadastrarEmpreendimento', {
		templateUrl : "views/pages/adminEmpresa/cadastrarEmpreendimento.html",
	})

	
		
	
	//Rota de erro
	.otherwise({
		redirectTo : "/404"
	})
}]);

