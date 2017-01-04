
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	

	$routeProvider
	
	
	
	.when('/servicos/vincular/cadastro', {
		templateUrl:"views/pages/modulo_servicos/vincular/cadastro.html",		
	})
	.when('/servicos/vincular/listagem', {
		templateUrl:"views/pages/modulo_servicos/vincular/listagem.html",		
	})
	.when('/servicos/vincular/visualizar/casa/:idServicoCasa', {
		templateUrl:"views/pages/modulo_servicos/vincular/visualizarCasa.html",		
	})
	.when('/servicos/vincular/visualizar/edificio/:idServicoEdificio', {
		templateUrl:"views/pages/modulo_servicos/vincular/visualizarEdificio.html",		
	})
	.when('/servicos/vincular/visualizar/comunitario/:idServicoComunitario', {
		templateUrl:"views/pages/modulo_servicos/vincular/visualizarComunitario.html",		
	})
	
}]);



