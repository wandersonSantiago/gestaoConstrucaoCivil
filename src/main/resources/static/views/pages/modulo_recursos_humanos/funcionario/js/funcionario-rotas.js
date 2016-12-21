
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	
	
$routeProvider
	.when('/funcionario/cadastro', {
		templateUrl:"views/pages/modulo_recursos_humanos/funcionario/cadastro.html",		
	})
	.when('/funcionario/edita/:idFuncionario', {
		templateUrl:"views/pages/modulo_recursos_humanos/funcionario/edita.html",		
	})
	.when('/funcionario/lista', {
		templateUrl:"views/pages/modulo_recursos_humanos/funcionario/lista.html",		
	})

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth ) {

auth.init('/', '/login', '/logout');
});



