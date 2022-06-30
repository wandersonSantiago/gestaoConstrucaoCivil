app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('requisicao', {
			url : '/requisicao',
			templateUrl : 'views/pages/modulo_estoque/requisicao/requisicao.index.html',
			redirectTo : 'requisicao.consultar',
			ncyBreadcrumb: {
				parent: 'requisicao.menu'
				  }
		})
		.state('requisicao.minhas', {
			url : "/:tipo/consulta",
			templateUrl : "views/pages/modulo_estoque/requisicao/requisicao.list.html",
			controller : "RequisicaoListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'requisicao.menu',
				    label: 'Minhas'
				  }
		})
		
		.state('requisicao.full', {
			url : "/:tipo/consultas",
			templateUrl : "views/pages/modulo_estoque/requisicao/requisicao.list.html",
			controller : "RequisicaoListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'requisicao.menu',
				    label: 'LIBERAR'
				  }
		})
		.state('requisicao.cadastrar', {
			requiresAuthentication: true,
			url : "/cadastrar",
			templateUrl : "views/pages/modulo_estoque/requisicao/requisicao.form.html",
			controller : "RequisicaoCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'requisicao.home',
			    label: 'Cadastrar'
			  }
		})
		.state('requisicao.editar', {
			requiresAuthentication: true,
			url : "/:idRequisicao/editar",
			templateUrl : "views/pages/modulo_estoque/requisicao/requisicao.form.html",
			controller : "RequisicaoEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'requisicao.consultar',
				    label: 'Editar'
				  }
		})
	
				
		.state('requisicao.menu', {
			requiresAuthentication: true,
			url : "/menu",
			templateUrl : "views/pages/modulo_estoque/requisicao/home.html",
			ncyBreadcrumb: {
				 	parent: 'estoque.menu',
				    label: 'Requisição'
				  }
		})
		
		.state('requisicao.home', {
			requiresAuthentication: true,
			url : "/home",
			templateUrl : "views/pages/modulo_estoque/requisicao/home.html",
			ncyBreadcrumb: {
				 	parent: 'requisicao.menu',
				    label: 'Menu'
				  }
		})
		.state('requisicao.show', {
			requiresAuthentication: true,
			url : "/:idRequisicao/show",
			templateUrl : "views/pages/modulo_estoque/requisicao/requisicao.show.html",
			controller : "RequisicaoShowController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'requisicao.menu',
				    label: 'Requisicao Show'
				  }
		})
		
});