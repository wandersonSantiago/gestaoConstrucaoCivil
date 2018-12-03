app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('categoriaFinanceiro', {
			abstract : true,
			url : '/categoriaFinanceiro',
			templateUrl : 'views/pages/modulo_financeiro/categoria/categoria.index.html',
			ncyBreadcrumb: {
				parent: 'lancamentos',
			    label: 'Categoria'
				  }
		})
		.state('categoriaFinanceiro.consultar', {
			url : "/consultar",
			templateUrl : "views/pages/modulo_financeiro/categoria/categoria.list.html",
			controller : "CategoriaFinanceiroListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'categoriaFinanceiro',
				    label: 'Categoria / Lista'
				  }
		})
		
		.state('categoriaFinanceiro.cadastrar', {
			requiresAuthentication: true,
			url : "/cadastrar",
			params: {
				backPage: null
			  },
			templateUrl : "views/pages/modulo_financeiro/categoria/categoria.form.html",
			controller : "CategoriaFinanceiroCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'categoriaFinanceiro',
			    label: 'Categoria / Cadastrar'
			  }
		})
		.state('categoriaFinanceiro.editar', {
			requiresAuthentication: true,
			url : "/:idCategoriaFinanceiro/editar",
			templateUrl : "views/pages/modulo_financeiro/categoria/categoria.form.html",
			controller : "CategoriaFinanceiroEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'categoriaFinanceiro.consultar',
				    label: 'Editar'
				  }
		})
	
		.state('categoriaFinanceiro.menu', {
			requiresAuthentication: true,
			url : "/menu",
			templateUrl : "views/pages/modulo_financeiro/categoria/home.html",
			ncyBreadcrumb: {
				 	parent: 'categoriaFinanceiro',
				    label: 'categoria'
				  }
		})

});