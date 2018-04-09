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
				 	parent: 'empreendimento',
				    label: 'Empreendimentos'
				  }
		})
				
		.state('empreendimento.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/pages/modulo_gerenciamento/empreendimento/empreendimento.form.html",
			controller : "EmpreendimentoCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'empreendimento',
			    label: 'Cadastrar'
			  }
		})
		
		.state('empreendimento.editar', {
			url : "/:idEmpreendimento/editar",
			templateUrl : "views/pages/modulo_gerenciamento/empreendimento/empreendimento.form.html",
			controller : "EmpreendimentoEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'empreendimento.listar',
				    label: 'Editar'
				  }
		
		})
		
		.state('empreendimento.visualizar', {
			url : "/:idEmpreendimento",
			templateUrl : "views/pages/modulo_gerenciamento/empreendimento/empreendimento.show.html",
			controller : "EmpreendimentoController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'empreendimento.listar',
				    label: 'Visualizar'
				  }
		})

});