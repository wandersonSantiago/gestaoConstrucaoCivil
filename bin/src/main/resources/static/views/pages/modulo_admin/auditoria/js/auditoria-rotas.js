
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {

	$routeProvider
	
		
	.when('/auditoria/menu', {
		templateUrl : "views/pages/modulo_admin/auditoria/home.html",
		
	})
	
	
	.when('/auditoria/estoque', {
		templateUrl : "views/pages/modulo_admin/empresa_contratada/alteracao.html",
	})
					
	.when('/auditoria/servicos', {
		templateUrl : "views/pages/modulo_admin/empresa_contratada/listagem.html",

	})
	
}]);



