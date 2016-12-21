
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	
	
	$routeProvider
	.when('/fabricante/cadastro', {
		templateUrl:"views/pages/modulo_cadastros/fabricante/cadastro.html",
	})
	.when('/fabricante/edita/:idFabricante', {
		templateUrl:"views/pages/modulo_cadastros/fabricante/edita.html",
	})
	.when('/fabricante/lista', {
		templateUrl:"views/pages/modulo_cadastros/fabricante/lista.html",
	})
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth ) {

auth.init('/', '/login', '/logout');
});



