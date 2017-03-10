
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {

	$routeProvider

	.when('/servicos/prestadora/cadastro', {
		templateUrl : "views/pages/modulo_servicos/prestadora_servicos/cadastro.html",
	})
	.when('/servicos/prestadora/alteracao/:idPrestadoraServico', {
		templateUrl : "views/pages/modulo_servicos/prestadora_servicos/alteracao.html",
	})
	.when('/servicos/prestadora/listagem', {
		templateUrl : "views/pages/modulo_servicos/prestadora_servicos/listagem.html",
	})

	.when('/servicos/prestadora/relatorio', {
		templateUrl : "views/pages/modulo_servicos/prestadora_servicos/prestadora.servicos.relatorio.html",
	})
	
}]);



