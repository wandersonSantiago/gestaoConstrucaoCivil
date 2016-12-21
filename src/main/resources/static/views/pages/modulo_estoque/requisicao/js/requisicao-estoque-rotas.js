
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	

	$routeProvider
	

	.when('/estoque/requisicao/cadastro', {
		templateUrl:"views/pages/modulo_estoque/requisicao/cadastro.html",
	})	
	.when('/estoque/requisicao/listagem', {
		templateUrl:"views/pages/modulo_estoque/requisicao/listagem.html",
	})
	
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth ) {

auth.init('/', '/login', '/logout');
});



