app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('produto', {
			url : '/produto',
			templateUrl : 'views/pages/modulo_estoque/produto/produto.index.html',
			ncyBreadcrumb: {
				parent: 'home.menu',
				label:'teste'
				  }
		})
		.state('produto.consultar', {
			url : "/consulta",
			templateUrl : "views/pages/modulo_estoque/produto/produto.list.html",
			controller : "ProdutoListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'produto.menu',
				    label: 'Consulta'
				  }
		})
		.state('produto.cadastrar', {
			requiresAuthentication: true,
			url : "/cadastrar",
			params: {
				backPage: null
			  },
			templateUrl : "views/pages/modulo_estoque/produto/produto.form.html",
			controller : "ProdutoCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'produto.menu',
			    label: 'Cadastrar'
			  }
		})
		.state('produto.editar', {
			requiresAuthentication: true,
			url : "/:idProduto/editar",
			templateUrl : "views/pages/modulo_estoque/produto/produto.form.html",
			controller : "ProdutoEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'produto.consultar',
				    label: 'Editar'
				  }
		})
		.state('produto.menu', {
			requiresAuthentication: true,
			url : "/produto/menu",
			templateUrl : "views/pages/modulo_estoque/produto/home.html",
			ncyBreadcrumb: {
				 	parent: 'estoque.menu',
				    label: 'Produto'
				  }
		})

});