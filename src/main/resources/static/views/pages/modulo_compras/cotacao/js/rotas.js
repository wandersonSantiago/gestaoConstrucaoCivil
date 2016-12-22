
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {

	$routeProvider
	
	.when('/cotacao/cadastro/:idCotacaoAberta', {
		templateUrl:"views/pages/modulo_compras/cotacao/cadastro.html",
	})
	.when('/cotacao/em_aberto', {
		templateUrl:"views/pages/modulo_compras/cotacao/em_aberto.html",
	})
	.when('/cotacao/gerar', {
		templateUrl:"views/pages/modulo_compras/cotacao/gerar.html",
	})
	
	
}]);




