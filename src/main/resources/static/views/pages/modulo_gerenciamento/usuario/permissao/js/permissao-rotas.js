
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
		
	$routeProvider
		
	.when('/usuario/permissao/cadastro', {
		templateUrl : "views/pages/modulo_gerenciamento/usuario/permissao/cadastro.html",
	})
	.when('/usuario/permissao/alteracao/:idPermissao', {
		templateUrl : "views/pages/modulo_gerenciamento/usuario/permissao/alteracao.html",
	})
	.when('/usuario/permissao/listagem', {
		templateUrl : "views/pages/modulo_gerenciamento/usuario/permissao/listagem.html",
	})
	
	
}]);


