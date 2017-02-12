
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	

	
	$routeProvider
	
	
	
	.when('/usuario/cadastro', {
		templateUrl : "views/pages/modulo_gerenciamento/usuario/cadastro.html",
	})
	.when('/usuario/alteracao/:idUsuario', {
		templateUrl : "views/pages/modulo_gerenciamento/usuario/alteracao.html",
	})
	.when('/usuario/listagem', {
		templateUrl : "views/pages/modulo_gerenciamento/usuario/listagem.html",
	})
	.when('/usuario/permissao/editar/:idUsuario', {
		templateUrl : "views/pages/modulo_gerenciamento/usuario/permissao.html",
	})
	
	
	
	
}]);


