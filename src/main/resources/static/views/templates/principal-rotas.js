
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {

	$routeProvider
	
	.when('/administrador/home', {
		templateUrl : "views/pages/modulo_admin/home.html",
		
	})	
	
	.when('/chamado/home', {
		templateUrl : "views/pages/modulo_chamado/home.html",
		
	})	
	
	.when('/gerenciamento/home', {
		templateUrl : "views/pages/modulo_gerenciamento/home.html",
		
	})	
	
	.when('/servicos/home', {
		templateUrl : "views/pages/modulo_servicos/home.html",
		
	})	
	
	.when('/recursos_humanos/home', {
		templateUrl : "views/pages/modulo_recursos_humanos/home.html",
		
	})	
	
	.when('/compras/home', {
		templateUrl : "views/pages/modulo_compras/home.html",
		
	})	
	
	.when('/almoxarifado/home', {
		templateUrl : "views/pages/modulo_estoque/home.html",
		
	})	
	
	.when('/cadastros/home', {
		templateUrl : "views/pages/modulo_cadastros/home.html",
		
	})	
	
	
}]);



