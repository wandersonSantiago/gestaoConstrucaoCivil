
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	
	$routeProvider
	
	
	.when('/cargo/cadastro', {
		templateUrl : "views/pages/modulo_recursos_humanos/cargo/cadastro.html",
	})
	.when('/cargo/edita/:idCargo', {
		templateUrl : "views/pages/modulo_recursos_humanos/cargo/edita.html",
	})
	.when('/cargo/lista', {
		templateUrl : "views/pages/modulo_recursos_humanos/cargo/lista.html",
	})
	
}]);



