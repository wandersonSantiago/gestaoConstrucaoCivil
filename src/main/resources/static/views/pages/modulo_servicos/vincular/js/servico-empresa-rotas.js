
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	

	$routeProvider
	
	
	
	.when('/servicos/vincular/cadastro', {
		templateUrl:"views/pages/modulo_servicos/vincular/cadastro.html",		
	})
	.when('/servicos/vincular/listagem', {
		templateUrl:"views/pages/modulo_servicos/vincular/listagem.html",		
	})
	
	
}]);



