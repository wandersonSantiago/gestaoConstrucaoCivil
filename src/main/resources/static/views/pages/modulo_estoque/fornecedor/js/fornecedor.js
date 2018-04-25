app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('fornecedor', {
			abstract : true,
			url : '/empresa/fornecedor',
			templateUrl : 'views/pages/modulo_estoque/fornecedor/fornecedor.index.html',
			redirectTo : 'empresa.listar',
			ncyBreadcrumb: {
					parent: 'fornecedor.menu',
				    label: 'Fornecedor'
				  }
		})
		
		.state('fornecedor.consultar', {
			url : "/consulta",
			templateUrl : "views/pages/modulo_estoque/fornecedor/fornecedor.list.html",
			controller : "FornecedorListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'fornecedor.menu',
				    label: 'Consulta'
				  }
		})
				
		.state('fornecedor.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/pages/modulo_estoque/fornecedor/fornecedor.form.html",
			controller : "FornecedorCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'fornecedor.menu',
			    label: 'Cadastrar'
			  }
		})
		
		.state('fornecedor.editar', {
			url : "/:idFornecedor/editar",
			templateUrl : "views/pages/modulo_estoque/fornecedor/fornecedor.form.html",
			controller : "FornecedorEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'fornecedor.listar',
				    label: 'Editar'
				  }
		
		})
		
		.state('fornecedor.visualizar', {
			url : "/:idFornecedor",
			templateUrl : "views/pages/modulo_estoque/fornecedor/fornecedor.show.html",
			controller : "FornecedorController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'fornecedor.listar',
				    label: 'Visualizar'
				  }
		})
		.state('fornecedor.menu', {
			requiresAuthentication: true,
			url : "/produto/menu",
			templateUrl : "views/pages/modulo_estoque/fornecedor/home.html",
			ncyBreadcrumb: {
				 	parent: 'estoque.menu',
				    label: 'Fornecedor'
				  }
		})

});