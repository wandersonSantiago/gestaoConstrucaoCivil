app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('cotacao', {
			url : '/cotacao',
			templateUrl : 'views/pages/modulo_compras/cotacao/cotacao.index.html',
			redirectTo : 'cotacao.consultar',
			ncyBreadcrumb: {
				parent: 'cotacao.menu'
				  }
		})
		.state('cotacao.consultar', {
			url : "/consultar",
			templateUrl : "views/pages/modulo_compras/cotacao/cotacao.list.html",
			controller : "CotacaoEmpresaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'cotacao.menu',
				    label: 'Pesquisa'
				  }
		})
		
		.state('cotacao.cadastrar', {
			requiresAuthentication: true,
			url : "/cadastrar",
			templateUrl : "views/pages/modulo_compras/cotacao/cotacao.form.html",
			controller : "CotacaoEmpresaCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'cotacao.menu',
			    label: 'Cadastrar'
			  }
		})
		.state('cotacao.editar', {
			requiresAuthentication: true,
			url : "/:idCotacao/editar",
			templateUrl : "views/pages/modulo_compras/cotacao/cotacao.form.html",
			controller : "CotacaoEmpresaEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'cotacao.consultar',
				    label: 'Editar'
				  }
		})
		
		.state('cotacao.editar-empresa', {
			requiresAuthentication: true,
			url : "/:idCotacaoEmpresa/empresa/editar",
			templateUrl : "views/pages/modulo_compras/cotacao/cotacao.lancar.html",
			controller : "CotacaoEmpresaLancarEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'cotacao.consultar',
				    label: 'Editar'
				  }
		})
		.state('cotacao.lancar', {
				requiresAuthentication: true,
				url : "/:idCotacao/lancar",
				templateUrl : "views/pages/modulo_compras/cotacao/cotacao.lancar.html",
				controller : "CotacaoEmpresaLancarController as ctrl",
				ncyBreadcrumb: {
					 	parent: 'cotacao.consultar',
					    label: 'Lançar'
					  }
			})
			
			.state('cotacao.show', {
				requiresAuthentication: true,
				url : "/:idCotacao/show",
				templateUrl : "views/pages/modulo_compras/cotacao/cotacao.show.html",
				controller : "CotacaoEmpresaShowController as ctrl",
				ncyBreadcrumb: {
					 	parent: 'cotacao.consultar',
					    label: 'Visualizar'
					  }
			})
			
		.state('cotacao.menu', {
			requiresAuthentication: true,
			url : "/menu",
			templateUrl : "views/pages/modulo_compras/home.html",
			ncyBreadcrumb: {
				 	parent: 'home',
				    label: 'Cotacao'
				  }
		})
		
		
});