
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	

	$routeProvider
	

	.when('/estoque/requisicao/cadastro', {
		templateUrl:"views/pages/modulo_estoque/requisicao/cadastro.html",
	})	
	.when('/estoque/requisicao/listagem', {
		templateUrl:"views/pages/modulo_estoque/requisicao/listagem.html",
	})
	.when('/estoque/visualizar/casa/:idRequisicaoCasa', {
		templateUrl:"views/pages/modulo_estoque/requisicao/visualizar_casa.html",
	})
	.when('/estoque/visualizar/edificio/:idRequisicaoEdificio', {
		templateUrl:"views/pages/modulo_estoque/requisicao/visualizar_edificio.html",
	})
	.when('/estoque/visualizar/outros/:idRequisicaoOutros', {
		templateUrl:"views/pages/modulo_estoque/requisicao/visualizar_outros.html",
	})
	
	
}]);



