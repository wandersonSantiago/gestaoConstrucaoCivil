
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
	.when('/semAcesso', {
		templateUrl : "views/erros/permissao.html"
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
	
	.when('/servicos/vincular/vincular', {
		templateUrl:"views/pages/servicos/vincular/vincularServicos.html",		
	})
	.when('/servicos/vincular/lista', {
		templateUrl:"views/pages/servicos/vincular/lista.html",		
	})
	
	//PRESTADORA DE SERVIÇOS
	
	.when('/servicos/prestadoraServico/cadastrar', {
		templateUrl : "views/pages/servicos/prestadoraServico/cadastrar.html",
	})
	.when('/servicos/prestadoraServico/editar/:idPrestadoraServico', {
		templateUrl : "views/pages/servicos/prestadoraServico/editar.html",
	})
	.when('/servicos/prestadoraServico/lista', {
		templateUrl : "views/pages/servicos/prestadoraServico/lista.html",
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
	
	.when('/almoxarifado/produto/categoria/cadastrar', {
		templateUrl:"views/pages/almoxarifado/produto/categoria/cadastrar.html",
	})
	.when('/almoxarifado/produto/categoria/editar/:idCategoria', {
		templateUrl:"views/pages/almoxarifado/produto/categoria/editar.html",
	})
	.when('/almoxarifado/produto/categoria/lista', {
		templateUrl:"views/pages/almoxarifado/produto/categoria/lista.html",
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
	
	.when('/almoxarifado/produto/area/cadastrar', {
		templateUrl:"views/pages/almoxarifado/produto/area/cadastrar.html",
	})
	.when('/almoxarifado/produto/area/editar/:idAreaProduto', {
		templateUrl:"views/pages/almoxarifado/produto/area/editar.html",
	})
	.when('/almoxarifado/produto/area/lista', {
		templateUrl:"views/pages/almoxarifado/produto/area/lista.html",
	})
	
	//TIPO DO PRODUTO
	
	.when('/almoxarifado/produto/tipo/cadastrar', {
		templateUrl:"views/pages/almoxarifado/produto/tipo/cadastrar.html",
	})
	.when('/almoxarifado/produto/tipo/editar/:idTipoProduto', {
		templateUrl:"views/pages/almoxarifado/produto/tipo/editar.html",
	})
	.when('/almoxarifado/produto/tipo/lista', {
		templateUrl:"views/pages/almoxarifado/produto/tipo/lista.html",
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
	
		.when('/almoxarifado/estoque/lista', {
		templateUrl:"views/pages/almoxarifado/estoque/lista.html",
	})
			.when('/almoxarifado/estoque/editar/:idProdutoEstoque', {
		templateUrl:"views/pages/almoxarifado/estoque/editar.html",
	})
		.when('/almoxarifado/estoque/informacoes', {
		templateUrl:"views/pages/almoxarifado/estoque/informacoes.html",
	})
	//TRANSFERENCIA
	.when('/almoxarifado/estoque/transferencia/cadastrar', {
		templateUrl:"views/pages/almoxarifado/estoque/transferencia/salvar.html",
	})
		.when('/almoxarifado/estoque/transferencia/enviadas', {
		templateUrl:"views/pages/almoxarifado/estoque/transferencia/enviadas.html",
	})
		.when('/almoxarifado/estoque/transferencia/recebidas', {
		templateUrl:"views/pages/almoxarifado/estoque/transferencia/recebidas.html",
	})
		.when('/almoxarifado/estoque/transferencia/vizualizarEnviados/:idEnviados', {
		templateUrl:"views/pages/almoxarifado/estoque/transferencia/vizualizarEnviados.html",
	})
		.when('/almoxarifado/estoque/transferencia/vizualizarRecebidos/:idRecebidos', {
		templateUrl:"views/pages/almoxarifado/estoque/transferencia/vizualizarRecebidos.html",
	})
	//REQUISICAO
	
	.when('/almoxarifado/estoque/requisicao/cadastrar', {
		templateUrl:"views/pages/almoxarifado/estoque/requisicao/cadastrar.html",
	})	
	.when('/almoxarifado/estoque/requisicao/lista', {
		templateUrl:"views/pages/almoxarifado/estoque/requisicao/lista.html",
	})
	
	//COMPRAS
	
	.when('/almoxarifado/compras/cadastrarCotacao/:idCotacaoAberta', {
		templateUrl:"views/pages/almoxarifado/compras/cadastrarCotacao.html",
	})
	.when('/almoxarifado/compras/cotacaoAberta', {
		templateUrl:"views/pages/almoxarifado/compras/cotacaoAberta.html",
	})
	.when('/almoxarifado/compras/gerarCotacao', {
		templateUrl:"views/pages/almoxarifado/compras/gerarCotacao.html",
	})
	.when('/almoxarifado/compras/pedidoCompra', {
		templateUrl:"views/pages/almoxarifado/compras/pedidoCompra.html",
	})
	
	
	//GERENCIAMENTO
	
	.when('/almoxarifado/gerenciamento/cancelamentoNota', {
		templateUrl:"views/pages/almoxarifado/gerenciamento/cancelamentoNota.html",
	})
	.when('/almoxarifado/gerenciamento/iventario', {
		templateUrl:"views/pages/almoxarifado/gerenciamento/iventario.html",
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

}]).run(function(auth, role) {

// Initialize auth module with the home page and login/logout path
// respectively
auth.init('/', '/login', '/logout');
role.permission();
});



