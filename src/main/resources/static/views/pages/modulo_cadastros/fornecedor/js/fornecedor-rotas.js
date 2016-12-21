
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	
	
	//$locationProvider.html5Mode(true);
	
	$routeProvider

	.when('/fornecedor/cadastro', {
		templateUrl:"views/pages/modulo_cadastros/fornecedor/cadastro.html",
	})
	.when('/fornecedor/edita/:idFornecedor', {
		templateUrl:"views/pages/modulo_cadastros/fornecedor/edita.html",
	})
	.when('/fornecedor/lista', {
		templateUrl:"views/pages/modulo_cadastros/fornecedor/lista.html",
	})
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth ) {

auth.init('/', '/login', '/logout');
});


