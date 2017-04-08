
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	

	$routeProvider
	
	.when('/estoque/entrada', {
		templateUrl:"views/pages/modulo_estoque/entrada.html",
	})	
	
	.when('/estoque/listagem', {
		templateUrl:"views/pages/modulo_estoque/listagem.html",
	})
	.when('/estoque/alteracao/:idProdutoEstoque', {
		templateUrl:"views/pages/modulo_estoque/alteracao.html",
	})
	.when('/estoque/informacoes/estoque/alto', {
		templateUrl:"views/pages/modulo_estoque/informacoesEstoqueAlto.html",
	})
	
	.when('/estoque/informacoes/estoque/baixo', {
		templateUrl:"views/pages/modulo_estoque/informacoesEstoqueBaixo.html",
	})
	.when('/estoque/relatorio', {
		templateUrl:"views/pages/modulo_estoque/estoque.relatorio.html",
	})
	

}]);




