
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	
	
//	$locationProvider.html5Mode(true);
	
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

//==================================================================================	
//ROTAS ADMIN SISTEMA
	
	//EMPRESA CONTRATADA
	
	.when('/empresaContratada/cadastrar', {
		templateUrl : "views/pages/empresaContratada/cadastrar.html",
		
	})
	
	.when('/empresaContratada/editar/:idEmpresa', {
		templateUrl : "views/pages/empresaContratada/editar.html",
	})
					
	.when('/empresaContratada/lista', {
		templateUrl : "views/pages/empresaContratada/lista.html",

	})
				
//===========================================================================================	
	
//ROTAS ADMINISTRADOR EMPRESA
	
	//EMPREENDIMENTO
	.when('/empreendimento/empreendimento/cadastrar', {
		templateUrl : "views/pages/empreendimento/empreendimento/cadastrar.html",
})
	.when('/empreendimento/empreendimento/editar/:idEmpreendimento',{
		templateUrl : "views/pages/empreendimento/empreendimento/editar.html",
	})
	.when('/empreendimento/empreendimento/lista', {
		templateUrl : "views/pages/empreendimento/empreendimento/lista.html",
	})
	.when('/empreendimento/empreendimento/vizualizar/:idEmpreendimento',{
		templateUrl : "views/pages/empreendimento/empreendimento/vizualizar.html",
	})
	
	//CONFIGURAÇÃO EMPREENDIMENTO
	
	.when('/empreendimento/configuracao/cadastrar',{
		templateUrl : "views/pages/empreendimento/configuracao/cadastrar.html",
	})
	.when('/empreendimento/configuracao/editar/:idConfigEmpreendimento',{
		templateUrl : "views/pages/empreendimento/configuracao/editar.html",
	})
	.when('/empreendimento/configuracao/lista',{
		templateUrl : "views/pages/empreendimento/configuracao/lista.html",
	})
	
	//PRESTADORA DE SERVIÇOS
	
	.when('/empreendimento/prestadoraServico/cadastrar', {
		templateUrl : "views/pages/empreendimento/prestadoraServico/cadastrar.html",
	})
	.when('/empreendimento/prestadoraServico/editar/:idPrestadoraServico', {
		templateUrl : "views/pages/empreendimento/prestadoraServico/cadastrar.html",
	})
	.when('/empreendimento/prestadoraServico/lista', {
		templateUrl : "views/pages/empreendimento/prestadoraServico/lista.html",
	})
	
	
//=====================================================================================
//ROTAS USUARIOS
	
	.when('/usuario/cadastrar', {
		templateUrl : "views/pages/usuario/cadastrar.html",
	})
	.when('/usuario/editar/:idUsuario', {
		templateUrl : "views/pages/usuario/editar.html",
	})
	.when('/usuario/lista', {
		templateUrl : "views/pages/usuario/lista.html",
	})
	.when('/usuario/permissao', {
		templateUrl : "views/pages/usuario/permissao.html",
	})
	
	
//================================================================================		
//SERVIÇOS
	
	.when('/servicos/pacotes/cadastrar', {
		templateUrl:"views/pages/servicos/pacotes/cadastrar.html",		
	})
	.when('/servicos/pacotes/editar/:idPacoteServico', {
		templateUrl : "views/pages/servicos/pacotes/editar.html"
	})
	.when('/servicos/pacotes/lista', {
		templateUrl:"views/pages/servicos/pacotes/lista.html",		
	})
	
	
//====================================================================================	
//ROTAS RECURSOS HUMANOS
	
		//CARGO
	
	.when('/recursosHumanos/cargo/cadastrar', {
		templateUrl : "views/pages/recursosHumanos/cargo/cadastrar.html",
	})
	.when('/recursosHumanos/cargo/editar/:idCargo', {
		templateUrl : "views/pages/recursosHumanos/cargo/editar.html",
	})
	.when('/recursosHumanos/cargo/lista', {
		templateUrl : "views/pages/recursosHumanos/cargo/lista.html",
	})
	
	//FUNCIONARIO
	
	.when('/recursosHumanos/funcionario/cadastrar', {
		templateUrl:"views/pages/recursosHumanos/funcionario/cadastrar.html",		
	})
	.when('/recursosHumanos/funcionario/editar/:idFuncionario', {
		templateUrl:"views/pages/recursosHumanos/funcionario/editar.html",		
	})
	.when('/recursosHumanos/funcionario/lista', {
		templateUrl:"views/pages/recursosHumanos/funcionario/lista.html",		
	})
	
	
//==================================================================================
	
//ROTAS ALMOXARIFADO
	
	//CATEGORIA
	
	.when('/almoxarifado/categoria/cadastrar', {
		templateUrl:"views/pages/almoxarifado/categoria/cadastrar.html",
	})
	.when('/almoxarifado/categoria/editar/:idCategoria', {
		templateUrl:"views/pages/almoxarifado/categoria/editar.html",
	})
	.when('/almoxarifado/categoria/lista', {
		templateUrl:"views/pages/almoxarifado/categoria/lista.html",
	})
	
	
	//FORNECEDOR
	
	.when('/almoxarifado/fornecedor/cadastrar', {
		templateUrl:"views/pages/almoxarifado/fornecedor/cadastrar.html",
	})
	.when('/almoxarifado/fornecedor/editar/:idFornecedor', {
		templateUrl:"views/pages/almoxarifado/fornecedor/editar.html",
	})
	.when('/almoxarifado/fornecedor/lista', {
		templateUrl:"views/pages/almoxarifado/fornecedor/lista.html",
	})
	
	
	//PRODUTO
	
	.when('/almoxarifado/produto/cadastrar', {
		templateUrl:"views/pages/almoxarifado/produto/cadastrar.html",
	})
	.when('/almoxarifado/produto/editar/:idProduto', {
		templateUrl:"views/pages/almoxarifado/produto/editar.html",
	})
	.when('/almoxarifado/produto/lista', {
		templateUrl:"views/pages/almoxarifado/produto/lista.html",
	})
	
	//AREA DO PRODUTO
	
	.when('/almoxarifado/areaProduto/cadastrar', {
		templateUrl:"views/pages/almoxarifado/areaProduto/cadastrar.html",
	})
	.when('/almoxarifado/areaProduto/editar/:idAreaProduto', {
		templateUrl:"views/pages/almoxarifado/areaProduto/editar.html",
	})
	.when('/almoxarifado/areaProduto/lista', {
		templateUrl:"views/pages/almoxarifado/areaProduto/lista.html",
	})
	
	//FABRICANTE
	
	.when('/almoxarifado/fabricante/cadastrar', {
		templateUrl:"views/pages/almoxarifado/fabricante/cadastrar.html",
	})
	.when('/almoxarifado/fabricante/editar/:idFabricante', {
		templateUrl:"views/pages/almoxarifado/fabricante/editar.html",
	})
	.when('/almoxarifado/fabricante/lista', {
		templateUrl:"views/pages/almoxarifado/fabricante/lista.html",
	})
	//ESTOQUE
	
	.when('/almoxarifado/estoque/entrada', {
		templateUrl:"views/pages/almoxarifado/estoque/entrada.html",
	})
	.when('/almoxarifado/estoque/saida', {
		templateUrl:"views/pages/almoxarifado/estoque/saida.html",
	})
//==============================================================================
	//ROTAS DE ESCAPE
	
	
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



