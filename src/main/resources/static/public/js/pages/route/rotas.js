
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	
	
//	$locationProvider.html5Mode(true);
	
	$routeProvider
	
	.when('/home', {
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
	
	.when('/cadastrarPrestadoraServico', {
		templateUrl : "views/pages/adminEmpresa/cadastrar/cadastrarPrestadoraDeServico.html",
	//	controller : "empreendimento-controller.js",
			
	})
	
	.when('/cadastrarUsuario', {
		templateUrl : "views/pages/adminEmpresa/cadastrar/cadastrarUsuario.html",
	
			
	})
	
			//LISTAR
    	
	.when('/listaEmpreendimento', {
		templateUrl : "views/pages/adminEmpresa/lista/listaEmpreendimento.html",
	//	controller : "empreendimento-controller.js",
	})
	
	.when('/listaPrestadoraServico', {
		templateUrl : "views/pages/adminEmpresa/lista/listaPrestadoraServico.html",
	//	controller : "empreendimento-controller.js",
	})
	
	.when('/listaUsuario', {
		templateUrl : "views/pages/adminEmpresa/lista/listaUsuario.html",
	
	})
	
	
		//EDITAR
	.when('/editarEmpreendimento/:idEmpreendimento',{
		templateUrl : "views/pages/adminEmpresa/editar/editarEmpreendimento.html",
	})
	
	
	.when('/editarPrestadoraServico/:idPrestadoraServico',{
		templateUrl : "views/pages/adminEmpresa/editar/editarPrestadoraServico.html",
	})
	
	.when('/editarUsuario/:idUsuario',{
		templateUrl : "views/pages/adminEmpresa/editar/editarUsuario.html",
	})
	
	
		//Vizualização
	
	.when('/vizualizarEmpreendimento/:idEmpreendimento',{
		templateUrl : "views/pages/adminEmpresa/vizualizar/vizualizarEmpreendimento.html",
	})
	
		//CONFIGURACAO
	.when('/configEmpreendimento',{
		templateUrl : "views/pages/adminEmpresa/configuracao/configEmpreendimento.html",
	})
    
	
	
//SERVIÇOS
	
		//CADASTRAR
	
	.when('/cadastrarPacotesServicos', {
		templateUrl:"views/pages/servicos/cadastrar/cadastrarPacoteServico.html",		

	})
	
	//CONSULTA
	
		.when('/listaPacoteServico', {
		templateUrl:"views/pages/servicos/lista/listaPacoteServico.html",		

	})
	//EDITAR
	.when('/editarPacotesServico/:idPacoteServico', {
		templateUrl : "views/pages/servicos/editar/editarPacoteServico.html"
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
	
	//CADASTRAR
	.when('/cadastrarUnidadeMedida', {
		templateUrl:"views/pages/almoxarifado/cadastrar/cadastrarUnidadeMedida.html",
	})
	.when('/cadastrarProduto', {
		templateUrl:"views/pages/almoxarifado/cadastrar/cadastrarProduto.html",
	})
	.when('/cadastrarFornecedor', {
		templateUrl:"views/pages/almoxarifado/cadastrar/cadastrarFornecedor.html",
	})
	.when('/cadastrarCategoria', {
		templateUrl:"views/pages/almoxarifado/cadastrar/cadastrarCategoria.html",
	})
	
	//CONSULTAR
	.when('/consultarCategoria', {
		templateUrl:"views/pages/almoxarifado/consultar/consultarCategoria.html",
	})
	.when('/consultarProduto', {
		templateUrl:"views/pages/almoxarifado/consultar/consultarProduto.html",
	})
	.when('/consultarFornecedor', {
		templateUrl:"views/pages/almoxarifado/consultar/consultarFornecedor.html",
	})
	
	//EDIATR
	.when('/editarCategoria/:idCategoria', {
		templateUrl:"views/pages/almoxarifado/editar/editarCategoria.html",
	})
	.when('/editarProduto/:idProduto', {
		templateUrl:"views/pages/almoxarifado/editar/editarProduto.html",
	})
	.when('/editarFornecedor/:idFornecedor', {
		templateUrl:"views/pages/almoxarifado/editar/editarFornecedor.html",
	})
		
	
	
	.when('/',{
		redirectTo : "/home"
	})
	
	
		.otherwise({
			redirectTo : "/404"
		});
	
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth) {

// Initialize auth module with the home page and login/logout path
// respectively
auth.init('/', '/login', '/logout');

});



