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
	
	.when('/listaAdminEmpresa', {
		templateUrl : "views/pages/adminSistema/listaAdminEmpresa.html",
	})
	
	
	//Rotas Administrador Empresa
	.when('/cadastrarEmpreendimento', {
		templateUrl : "views/pages/adminEmpresa/cadastrarEmpreendimento.html",
	})
	.when('/cadastrarTipoEmpreendimento', {
		templateUrl : "views/pages/adminEmpresa/cadastrarTipoEmpreendimento.html",
	})
    	.when('/listaTipoEmpreendimento', {
		templateUrl : "views/pages/adminEmpresa/listaTipoEmpreendimento.html",
	})
	.when('/listaEmpreendimento', {
		templateUrl : "views/pages/adminEmpresa/listaEmpreendimento.html",
	})
    
	//Rotas Recursos Humanos
		
	.when('/cadastrarFuncionario', {
		templateUrl:"views/pages/recursosHumanos/cadastrarFuncionario.html",		
	})
		.when('/cadastrarCargo', {
		templateUrl : "views/pages/recursosHumanos/cadastrarCargo.html",
	})
	
	
	//Rotas Produto
	.when('/cadastrarUnidadeMedida', {
		templateUrl:"views/pages/produto/cadastrarUnidadeMedida.html",
	})
	.when('/cadastrarProduto', {
		templateUrl:"views/pages/produto/cadastrarProduto.html",
	})
	
	//Rota de erro
	.otherwise({
		redirectTo : "/"
	})
}]);

