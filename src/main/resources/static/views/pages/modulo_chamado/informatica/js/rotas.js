app.config(['$routeProvider','$httpProvider', function($routeProvider, $httpProvider) {
	$routeProvider
	
	.when("/chamado/informatica/cadastrar",{
		templateUrl : "views/pages/modulo_chamado/informatica/cadastrar.html"
	})
		
	.when("/chamado/informatica/lista",{
		templateUrl : "views/pages/modulo_chamado/informatica/lista.html"
	})
	
	.when("/chamado/informatica/suporte/lista",{
		templateUrl : "views/pages/modulo_chamado/informatica/suporte/lista.html"
	})
	
	.when("/chamado/informatica/suporte/relatorio",{
		templateUrl : "views/pages/modulo_chamado/informatica/suporte/relatorio.html"
	})	

	.when("/chamado/informatica/atendimento/:idChamadoTi",{
		templateUrl : "views/pages/modulo_chamado/informatica/atendimento.html"
	})
	
	.when("/chamado/informatica/suporte/atendimento/:idChamadoTi",{
		templateUrl : "views/pages/modulo_chamado/informatica/suporte/atendimento.html"
	})
	
		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth) {

auth.init('/', '/login', '/logout');

});

