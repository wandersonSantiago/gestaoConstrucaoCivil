app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('categoria', {
			url : '/categoria',
			templateUrl : 'views/pages/modulo_estoque/produto/categoria/categoria.index.html',
			redirectTo : 'categoria.consultar',
			ncyBreadcrumb: {
				parent: 'categoria.menu'
				  }
		})
		.state('categoria.consultar', {
			url : "/consultar",
			templateUrl : "views/pages/modulo_estoque/produto/categoria/categoria.list.html",
			controller : "CategoriaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'categoria.menu',
				    label: 'Lista'
				  }
		})
		
		.state('categoria.cadastrar', {
			requiresAuthentication: true,
			url : "/cadastrar",
			templateUrl : "views/pages/modulo_estoque/produto/categoria/categoria.form.html",
			controller : "CategoriaCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'categoria.menu',
			    label: 'Cadastrar'
			  }
		})
		.state('categoria.editar', {
			requiresAuthentication: true,
			url : "/:idCategoria/editar",
			templateUrl : "views/pages/modulo_estoque/produto/categoria/categoria.form.html",
			controller : "CategoriaEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'categoria.consultar',
				    label: 'Editar'
				  }
		})
	
		.state('categoria.menu', {
			requiresAuthentication: true,
			url : "/menu",
			templateUrl : "views/pages/modulo_estoque/produto/categoria/home.html",
			ncyBreadcrumb: {
				 	parent: 'produto.menu',
				    label: 'Categoria'
				  }
		})

});