
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	
$routeProvider	
	
	.when('/produto/tipo/cadastro', {
		templateUrl:"views/pages/modulo_cadastros/produto/tipo/cadastro.html",
	})
	.when('/produto/tipo/edita/:idTipoProduto', {
		templateUrl:"views/pages/modulo_cadastros/produto/tipo/edita.html",
	})
	.when('/produto/tipo/lista', {
		templateUrl:"views/pages/modulo_cadastros/produto/tipo/lista.html",
	})
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth ) {

auth.init('/', '/login', '/logout');
});


