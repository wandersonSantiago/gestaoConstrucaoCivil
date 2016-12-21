
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {

	$routeProvider
	
	
	
	.when('/empresa_contratada/cadastro', {
		templateUrl : "views/pages/modulo_admin/empresa_contratada/cadastro.html",
		
	})
	
	.when('/empresa_contratada/alteracao/:idEmpresa', {
		templateUrl : "views/pages/modulo_admin/empresa_contratada/alteracao.html",
	})
					
	.when('/empresa_contratada/listagem', {
		templateUrl : "views/pages/modulo_admin/empresa_contratada/listagem.html",

	})
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth ) {

auth.init('/', '/login', '/logout');
});



