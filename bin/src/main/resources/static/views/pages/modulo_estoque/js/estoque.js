app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('estoque', {
			url : '/estoque',
			templateUrl : 'views/pages/modulo_estoque/estoque.index.html',
			redirectTo : 'estoque.consultar',
			ncyBreadcrumb: {
				parent: 'estoque.menu'
				  }
		})
		.state('estoque.consultar', {
			url : "/consultar",
			templateUrl : "views/pages/modulo_estoque/estoque.list.html",
			controller : "EstoqueListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'estoque.menu',
				    label: 'Lista'
				  }
		})
		
		.state('estoque.cadastrar', {
			requiresAuthentication: true,
			url : "/cadastrar",
			templateUrl : "views/pages/modulo_estoque/estoque.form.html",
			controller : "EstoqueCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'estoque.home',
			    label: 'Cadastrar'
			  }
		})
		.state('estoque.editar', {
			requiresAuthentication: true,
			url : "/:idEstoque/editar",
			templateUrl : "views/pages/modulo_estoque/estoque.form.html",
			controller : "EstoqueEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'estoque.consultar',
				    label: 'Editar'
				  }
		})
	
		.state('estoque.configuracao', {
			requiresAuthentication: true,
			url : "/:idEstoque/configuracao",
			templateUrl : "views/pages/modulo_estoque/estoque.configuracao.html",
			controller : "EstoqueConfiguracaoController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'estoque.consultar',
				    label: 'Configuração'
				  }
		})
		
		.state('estoque.menu', {
			requiresAuthentication: true,
			url : "/menu",
			templateUrl : "views/pages/modulo_estoque/home.html",
			ncyBreadcrumb: {
				 	parent: 'home',
				    label: 'Estoque'
				  }
		})
		
		.state('estoque.home', {
			requiresAuthentication: true,
			url : "/home",
			templateUrl : "views/pages/modulo_estoque/estoque.home.html",
			ncyBreadcrumb: {
				 	parent: 'estoque.menu',
				    label: 'Menu'
				  }
		})
		
		.state('estoque.nota-consultar', {
			url : "/nota/consultar",
			templateUrl : "views/pages/modulo_estoque/estoque.nota.list.html",
			controller : "EstoqueNotaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'estoque.menu',
				    label: 'Lista de Notas'
				  }
		})

		.state('estoque.nota-editar', {
			requiresAuthentication: true,
			url : "/:idNota/nota/editar",
			templateUrl : "views/pages/modulo_estoque/estoque.form.html",
			controller : "EstoqueNotaEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'estoque.menu',
				    label: 'Nota Editar'
				  }
		})
		.state('estoque.nota-show', {
			requiresAuthentication: true,
			url : "/:idNota/show",
			templateUrl : "views/pages/modulo_estoque/estoque.nota.show.html",
			controller : "EstoqueNotaShowController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'estoque.menu',
				    label: 'Nota Show'
				  }
		})
});