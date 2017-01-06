
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {

	$routeProvider
	
	.when('/cotacao/cadastro/:idCotacaoAberta', {
		templateUrl:"views/pages/modulo_compras/cotacao/cadastro.html",
	})
	.when('/cotacao/vencedores/:idVencedores', {
		templateUrl:"views/pages/modulo_compras/cotacao/vencedores.html",
	})
	.when('/cotacao/vencedor', {
		templateUrl:"views/pages/modulo_compras/cotacao/visualizarVencedores.html",
	})
	.when('/cotacao/concorrentes/:idConcorrentes', {
		templateUrl:"views/pages/modulo_compras/cotacao/concorrentes.html",
	})
	.when('/cotacao/concorrente', {
		templateUrl:"views/pages/modulo_compras/cotacao/visualizarConcorretes.html",
	})
	.when('/cotacao/em_aberto', {
		templateUrl:"views/pages/modulo_compras/cotacao/em_aberto.html",
	})
	.when('/cotacao/gerar', {
		templateUrl:"views/pages/modulo_compras/cotacao/gerar.html",
	})
	
	
}]);




