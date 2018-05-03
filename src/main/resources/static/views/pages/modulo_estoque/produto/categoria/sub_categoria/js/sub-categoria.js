app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('sub-categoria', {
			url : '/sub-categoria',
			templateUrl : 'views/pages/modulo_estoque/produto/categoria/sub_categoria/sub.categoria.index.html',
			ncyBreadcrumb: {
				parent: 'sub-categoria.menu'
				  }
		})
		.state('sub-categoria.consultar', {
			url : "/consultar",
			templateUrl : "views/pages/modulo_estoque/produto/categoria/sub_categoria/sub.categoria.list.html",
			controller : "SubCategoriaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'sub-categoria.menu',
				    label: 'Lista'
				  }
		})
		
		.state('sub-categoria.cadastrar', {
			requiresAuthentication: true,
			url : "/cadastrar",
			templateUrl : "views/pages/modulo_estoque/produto/categoria/sub_categoria/sub.categoria.form.html",
			controller : "SubCategoriaCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'sub-categoria.menu',
			    label: 'Cadastrar'
			  }
		})
		.state('sub-categoria.editar', {
			requiresAuthentication: true,
			url : "/:idCategoria/editar",
			templateUrl : "views/pages/modulo_estoque/produto/categoria/sub_categoria/sub.categoria.form.html",
			controller : "SubCategoriaEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'sub-categoria.consultar',
				    label: 'Editar'
				  }
		})
	
		.state('sub-categoria.menu', {
			requiresAuthentication: true,
			url : "/menu",
			templateUrl : "views/pages/modulo_estoque/produto/categoria/sub_categoria/home.html",
			ncyBreadcrumb: {
				 	parent: 'categoria.menu',
				    label: 'Sub-categoria'
				  }
		})

});