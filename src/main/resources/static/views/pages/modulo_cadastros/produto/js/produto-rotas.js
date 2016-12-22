
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	
	
	$routeProvider	
	.when('/produto/cadastro', {
		templateUrl:"views/pages/modulo_cadastros/produto/cadastro.html",
	})
	.when('/produto/edita/:idProduto', {
		templateUrl:"views/pages/modulo_cadastros/produto/edita.html",
	})
	.when('/produto/lista', {
		templateUrl:"views/pages/modulo_cadastros/produto/lista.html",
	})
		
	
}]);
