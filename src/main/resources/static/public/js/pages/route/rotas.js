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
			
		//CONFIGURAÇÕES
	
	
			/*	//CADASTRAR
	
	.when('/cadastrarTipoEmpreendimento', {
		templateUrl : "views/pages/adminSistema/configuracao/cadastrar/cadastrarTipoEmpreendimento.html",
	//	controller : "tipo-empreendimento-controller.js",
	})
	
				//LISTAR
	.when('/listaTipoEmpreendimento', {
		templateUrl : "views/pages/adminSistema/configuracao/listar/listaTipoEmpreendimento.html",
		//controller : "tipo-empreendimento-controller.js",
	})*/
	
	
	
//ROTAS ADMINISTRADOR EMPRESA
	
			//CADASTRAR
	.when('/cadastrarEmpreendimento', {
		templateUrl : "views/pages/adminEmpresa/cadastrar/cadastrarEmpreendimento.html",
	//	controller : "empreendimento-controller.js",
			
	})
	
			//LISTAR
    	
	.when('/listaEmpreendimento', {
		templateUrl : "views/pages/adminEmpresa/lista/listaEmpreendimento.html",
	//	controller : "empreendimento-controller.js",
	})
	
	
	
		//EDITAR
	.when('/editarEmpreendimento/:idEmpreendimento',{
		templateUrl : "views/pages/adminEmpresa/editar/editarEmpreendimento.html",
	})
	
	
		//Vizualização
	
	.when('/vizualizarEmpreendimento/:idEmpreendimento',{
		templateUrl : "views/pages/adminEmpresa/vizualizar/vizualizarEmpreendimento.html",
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
	
	
		//CONSULTA
	.when('/listaFuncionario', {
		templateUrl:"views/pages/recursosHumanos/lista/listaFuncionario.html",		
//		controller : "funcionario-controller.js"
	})
	.when('/listaCargo', {
		templateUrl:"views/pages/recursosHumanos/lista/listaCargo.html",		
//		controller : "funcionario-controller.js"
	})
	
	
	//EDITAR
	
	.when('/editarFuncionario/:idFuncionario', {
		templateUrl:"views/pages/recursosHumanos/editar/editarFuncionario.html",		
//		controller : "funcionario-controller.js"
	})
	
	.when('/editarCargo/:idCargo', {
		templateUrl:"views/pages/recursosHumanos/editar/editarCargo.html",		
//		controller : "funcionario-controller.js"
	})
	
	//Rotas Almoxarifado
	.when('/cadastrarUnidadeMedida', {
		templateUrl:"views/pages/almoxarifado/cadastrarUnidadeMedida.html",
	})
	.when('/cadastrarProduto', {
		templateUrl:"views/pages/almoxarifado/cadastrarProduto.html",
	})
	.when('/cadastrarFornecedor', {
		templateUrl:"views/pages/almoxarifado/cadastrarFornecedor.html",
	})
	.when('/cadastrarCategoria', {
		templateUrl:"views/pages/almoxarifado/cadastrarCategoria.html",
	})
	//Rota de erro
	.otherwise({
		redirectTo : "/"
	})
}]);

