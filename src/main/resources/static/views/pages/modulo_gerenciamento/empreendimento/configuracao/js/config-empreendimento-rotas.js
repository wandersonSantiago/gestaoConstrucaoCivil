
app.config(['$routeProvider', '$httpProvider', function($routeProvider,  $httpProvider) {
	

	
	$routeProvider
	
	
	//CONFIGURAÇÃO EMPREENDIMENTO
	
	.when('/empreendimento/configuracao/cadastro',{
		templateUrl : "views/pages/modulo_gerenciamento/empreendimento/configuracao/cadastro.html",
	})
	.when('/empreendimento/configuracao/alteracao/:idConfigEmpreendimento',{
		templateUrl : "views/pages/modulo_gerenciamento/empreendimento/configuracao/alteracao.html",
	})
	.when('/empreendimento/configuracao/listagem',{
		templateUrl : "views/pages/modulo_gerenciamento/empreendimento/configuracao/listagem.html",
	})
	
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth ) {

auth.init('/', '/login', '/logout');
});



