app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('categoriaFinanceiro', {
			url : '/categoriaFinanceiro',
			templateUrl : 'views/pages/modulo_financeiro/categoria/categoria.index.html',
			ncyBreadcrumb: {
				parent: 'lancamento.menu'
				  }
		})
		.state('categoriaFinanceiro.consultar', {
			url : "/consultar",
			templateUrl : "views/pages/modulo_financeiro/categoria/categoria.list.html",
			controller : "CategoriaFinanceiroListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'categoriaFinanceiro.menu',
				    label: 'Lista'
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
			 	parent: 'categoriaFinanceiro.menu',
			    label: 'Cadastrar'
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
				    label: 'categoriaFinanceiro'
				  }
		})

});