
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	
	

	$routeProvider
	
	.when('/servicos/pacotes/cadastro', {
		templateUrl:"views/pages/modulo_servicos/pacotes/cadastro.html",		
	})
	.when('/servicos/pacotes/edita/:idPacoteServico', {
		templateUrl : "views/pages/modulo_servicos/pacotes/edita.html"
	})
	.when('/servicos/pacotes/lista', {
		templateUrl:"views/pages/modulo_servicos/pacotes/lista.html",		
	})
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth ) {

auth.init('/', '/login', '/logout');
});



