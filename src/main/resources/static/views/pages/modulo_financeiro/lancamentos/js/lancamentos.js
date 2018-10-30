app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('lancamentos', {
			url : '/lancamentos',
			templateUrl : 'views/pages/modulo_financeiro/lancamentos/lancamentos.index.html',
			redirectTo : 'lancamentos.consultar',
			ncyBreadcrumb: {
				parent: 'lancamentos.menu'
				  }
		})
		.state('lancamentos.consultar', {
			url : "/consultar",
			templateUrl : "views/pages/modulo_financeiro/lancamentos/lancamentos.list.html",
			controller : "LancamentosListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'lancamentos.menu',
				    label: 'Pesquisa'
				  }
		})
		
		.state('lancamentos.list', {
			url : "/:Tipo/tipos",
			templateUrl : "views/pages/modulo_financeiro/lancamentos/lancamentos.list.html",
			controller : "LancamentosListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'lancamentos.menu',
				    label: 'Pesquisa'
				  }
		})
		
		.state('lancamentos.cadastrar', {
			requiresAuthentication: true,
			url : "/cadastrar",
			templateUrl : "views/pages/modulo_financeiro/lancamentos/lancamentos.form.html",
			controller : "LancamentosCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'lancamentos.menu',
			    label: 'Cadastrar'
			  }
		})
		.state('lancamentos.editar', {
			requiresAuthentication: true,
			url : "/:idLancamentos/editar",
			templateUrl : "views/pages/modulo_financeiro/lancamentos/lancamentos.form.html",
			controller : "LancamentosEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'lancamentos.consultar',
				    label: 'Editar'
				  }
		})
		
		.state('lancamentos.show', {
				requiresAuthentication: true,
				url : "/:idLancamentos/show",
				templateUrl : "views/pages/modulo_financeiro/lancamentos/lancamentos.show.html",
				controller : "LancamentosShowController as ctrl",
				ncyBreadcrumb: {
					 	parent: 'lancamentos.consultar',
					    label: 'Visualizar'
					  }												
			})
			
		.state('lancamentos.menu', {
			requiresAuthentication: true,
			url : "/menu",
			templateUrl : "views/pages/modulo_financeiro/home.html",
			ncyBreadcrumb: {
				 	parent: 'home',
				    label: 'Lancamentos'
				  }
		})
		
		
});