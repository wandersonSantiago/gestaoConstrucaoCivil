
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	
	
$routeProvider
	.when('/morador/cadastrar', {
		templateUrl:"views/pages/modulo_cadastros/morador/cadastrar.html",		
	})
	.when('/morador/editar/:idClienteMorador', {
		templateUrl:"views/pages/modulo_cadastros/morador/editar.html",		
	})
	.when('/morador/listar', {
		templateUrl:"views/pages/modulo_cadastros/morador/listar.html",		
	})

	
}]);



