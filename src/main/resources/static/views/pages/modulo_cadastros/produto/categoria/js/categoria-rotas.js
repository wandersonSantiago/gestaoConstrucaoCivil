
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {

	$routeProvider
	.when('/produto/categoria/cadastro', {
		templateUrl:"views/pages/modulo_cadastros/produto/categoria/cadastro.html",
	})
	.when('/produto/categoria/edita/:idCategoria', {
		templateUrl:"views/pages/modulo_cadastros/produto/categoria/edita.html",
	})
	.when('/produto/categoria/lista', {
		templateUrl:"views/pages/modulo_cadastros/produto/categoria/lista.html",
	})
	
	
}]);



