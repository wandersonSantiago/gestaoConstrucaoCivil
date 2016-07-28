app.config(['$routeProvider','$httpProvider', function($routeProvider, $httpProvider) {
	$routeProvider
	
	.when('/', {
		templateUrl : "views/home.html"
	})
	
		.when('/login', {
		templateUrl : "views/login.html"
	})

	
//ROTAS ADMIN SISTEMA
	
				//CADASTRAR
	.when('/cadastrarEmpresa', {
		templateUrl : "views/pages/adminSistema/cadastrar/cadastrarEmpresa.html",
	//	controller : "adminSistemaController",
		
	})
	
	.when('/cadastrarAdminEmpresa', {
		templateUrl : "views/pages/adminSistema/cadastrar/cadastrarAdmiEmpresa.html",
	//	controller : "adminSistemaController",
	})
	
				//LISTAR
	.when('/listaAdminEmpresa', {
		templateUrl : "views/pages/adminSistema/lista/listaAdminEmpresa.html",
	//	controller : "adminSistemaController",
	})
	
				
	.when('/listaEmpresa', {
		templateUrl : "views/pages/adminSistema/lista/listaEmpresa.html",
	//	controller : "adminSistemaController",
	})
				//EDITAR
	
	.when('/editarEmpresa/:idEmpresa', {
		templateUrl : "views/pages/adminSistema/editar/editarEmpresa.html",
	//	controller : "adminSistemaController",
	})
	
	
//ROTAS ADMINISTRADOR EMPRESA
	
			//CADASTRAR
	.when('/cadastrarEmpreendimento', {
		templateUrl : "views/pages/adminEmpresa/cadastrar/cadastrarEmpreendimento.html",
	//	controller : "empreendimento-controller.js",
			
	})
	.when('/cadastrarTipoEmpreendimento', {
		templateUrl : "views/pages/adminEmpresa/cadastrar/cadastrarTipoEmpreendimento.html",
	//	controller : "tipo-empreendimento-controller.js",
	})
	
			//LISTAR
    	.when('/listaTipoEmpreendimento', {
		templateUrl : "views/pages/adminEmpresa/lista/listaTipoEmpreendimento.html",
//		controller : "tipo-empreendimento-controller.js",
	})
	.when('/listaEmpreendimento', {
		templateUrl : "views/pages/adminEmpresa/lista/listaEmpreendimento.html",
	//	controller : "empreendimento-controller.js",
	})
    
//ROTAS RECURSOS HUMANOS
	
		//CADASTRAR
	.when('/cadastrarFuncionario', {
		templateUrl:"views/pages/recursosHumanos/cadastrar/cadastrarFuncionario.html",		
//		controller : "funcionario-controller.js"
	})
		.when('/cadastrarCargo', {
		templateUrl : "views/pages/recursosHumanos/cadastrar/cadastrarCargo.html",
	//	controller : "cargo-controller.js"
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

