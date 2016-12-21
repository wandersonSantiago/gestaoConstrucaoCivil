
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	
	$routeProvider
		
	.when('/estoque/transferencia/cadastro', {
		templateUrl:"views/pages/modulo_estoque/transferencia/salvar.html",
	})
		.when('/estoque/transferencia/enviadas', {
		templateUrl:"views/pages/modulo_estoque/transferencia/enviadas.html",
	})
		.when('/estoque/transferencia/recebidas', {
		templateUrl:"views/pages/modulo_estoque/transferencia/recebidas.html",
	})
		.when('/estoque/transferencia/vizualizarEnviados/:idEnviados', {
		templateUrl:"views/pages/modulo_estoque/transferencia/vizualizarEnviados.html",
	})
		.when('/estoque/transferencia/vizualizarRecebidos/:idRecebidos', {
		templateUrl:"views/pages/modulo_estoque/transferencia/vizualizarRecebidos.html",
	})

	
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth ) {

auth.init('/', '/login', '/logout');
});


