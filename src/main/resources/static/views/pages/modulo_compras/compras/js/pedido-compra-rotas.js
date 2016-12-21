
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	

	$routeProvider
	
	

	.when('/compras/pedido', {
		templateUrl:"views/pages/modulo_compras/compras/pedido.html",
	})
	
	
	
$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth ) {

auth.init('/', '/login', '/logout');
});



