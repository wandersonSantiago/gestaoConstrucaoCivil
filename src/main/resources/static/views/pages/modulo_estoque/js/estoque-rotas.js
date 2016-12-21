
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	

	$routeProvider
	
	.when('/estoque/entrada', {
		templateUrl:"views/pages/modulo_estoque/entrada.html",
	})	
	
	.when('/estoque/listagem', {
		templateUrl:"views/pages/modulo_estoque/listagem.html",
	})
	.when('/estoque/alteracao/:idProdutoEstoque', {
		templateUrl:"views/pages/modulo_estoque/alteracao.html",
	})
	.when('/estoque/informacoes', {
		templateUrl:"views/pages/modulo_estoque/informacoes.html",
	})
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth ) {

auth.init('/', '/login', '/logout');
});



