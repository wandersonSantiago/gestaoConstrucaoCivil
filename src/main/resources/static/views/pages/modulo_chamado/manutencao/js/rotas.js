app.config(['$routeProvider','$httpProvider', function($routeProvider, $httpProvider) {
	$routeProvider
		
	.when("/chamado/manutencao/cadastrar",{
		templateUrl : "views/pages/modulo_chamado/manutencao/cadastrar.html"
	})
		
	.when("/chamado/manutencao/lista",{
		templateUrl : "views/pages/modulo_chamado/manutencao/lista.html"
	})
	
	.when("/chamado/manutencao/atendimento/:idChamadoManutencao",{
		templateUrl : "views/pages/modulo_chamado/manutencao/atendimento.html"
	})
	
	.when("/chamado/manutencao/suporte/atendimento/:idChamadoManutencao",{
		templateUrl : "views/pages/modulo_chamado/manutencao/suporte/atendimento.html"
	})
	
	.when("/chamado/manutencao/suporte/lista",{
		templateUrl : "views/pages/modulo_chamado/manutencao/suporte/lista.html"
	})
	
	.when("/chamado/manutencao/suporte/relatorio",{
		templateUrl : "views/pages/modulo_chamado/manutencao/suporte/relatorio.html"
	})
	
	.when("/chamado/manutencao/editar/:idChamadoManutencao",{
		templateUrl : "views/pages/modulo_chamado/manutencao/editar.html"
	})
	
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth) {

auth.init('/', '/login', '/logout');

});

