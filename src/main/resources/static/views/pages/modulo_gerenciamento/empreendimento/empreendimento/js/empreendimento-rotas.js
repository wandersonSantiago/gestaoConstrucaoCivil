
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	

	
	$routeProvider
	
	
	.when('/empreendimento/cadastro', {
		templateUrl : "views/pages/modulo_gerenciamento/empreendimento/empreendimento/cadastro.html",
})
	.when('/empreendimento/alteracao/:idEmpreendimento',{
		templateUrl : "views/pages/modulo_gerenciamento/empreendimento/empreendimento/alteracao.html",
	})
	.when('/empreendimento/listagem', {
		templateUrl : "views/pages/modulo_gerenciamento/empreendimento/empreendimento/listagem.html",
	})
	.when('/empreendimento/visualizacao/:idEmpreendimento',{
		templateUrl : "views/pages/modulo_gerenciamento/empreendimento/empreendimento/visualizacao.html",
	})
	
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth ) {

auth.init('/', '/login', '/logout');
});



