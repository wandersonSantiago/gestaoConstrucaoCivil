
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {

	
	$routeProvider
	.when('/area/cadastro', {
		templateUrl:"views/pages/modulo_cadastros/area/cadastro.html",
	})
	.when('/area/edita/:idAreaProduto', {
		templateUrl:"views/pages/modulo_cadastros/area/edita.html",
	})
	.when('/area/lista', {
		templateUrl:"views/pages/modulo_cadastros/area/lista.html",
	})
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth ) {

auth.init('/', '/login', '/logout');
});



