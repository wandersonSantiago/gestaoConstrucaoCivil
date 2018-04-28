app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('empreendimento', {
			abstract : true,
			url : '/empreendimento',
			templateUrl : 'views/pages/modulo_gerenciamento/empreendimento/empreendimento.index.html',
			redirectTo : 'empreendimento.listar',
			ncyBreadcrumb: {
					parent: 'home',
				    label: 'Empreendimento'
				  }
		})
		
		.state('empreendimento.consultar', {
			url : "/consulta",
			templateUrl : "views/pages/modulo_gerenciamento/empreendimento/empreendimento.list.html",
			controller : "EmpreendimentoListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'empreendimento.menu',
				    label: 'Consulta'
				  }
		})
				
		.state('empreendimento.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/pages/modulo_gerenciamento/empreendimento/empreendimento.form.html",
			controller : "EmpreendimentoCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'empreendimento.menu',
			    label: 'Cadastrar'
			  }
		})
		
		.state('empreendimento.editar', {
			url : "/:idEmpreendimento/editar",
			templateUrl : "views/pages/modulo_gerenciamento/empreendimento/empreendimento.form.html",
			controller : "EmpreendimentoEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'empreendimento.consultar',
				    label: 'Editar'
				  },
				  resolve:{
			        	empreendimentoPrepService: empreendimentoPrepService
					  }
		
		})
		
		.state('empreendimento.show', {
			url : "/:idEmpreendimento",
			templateUrl : "views/pages/modulo_gerenciamento/empreendimento/empreendimento.show.html",
			controller : "EmpreendimentoShowController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'empreendimento.consultar',
				    label: 'Visualizar'
				  }
		})
		.state('empreendimento.menu', {
			requiresAuthentication: true,
			url : "/gerenciamento/menu",
			templateUrl : "views/pages/modulo_gerenciamento/empreendimento/home.html",
			ncyBreadcrumb: {
				 	parent: 'gerenciamento',
				    label: 'Empreendimento'
				  }
		})

});
empreendimentoPrepService.$inject = ['$stateParams','EmpreendimentoService']

function empreendimentoPrepService($stateParams, EmpreendimentoService){
	return EmpreendimentoService.buscarPorId($stateParams.idEmpreendimento);
}