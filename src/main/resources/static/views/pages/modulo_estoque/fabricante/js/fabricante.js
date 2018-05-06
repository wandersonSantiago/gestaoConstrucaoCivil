app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('fabricante', {
			abstract : true,
			url : '/empresa/fabricante',
			templateUrl : 'views/pages/modulo_estoque/fabricante/fabricante.index.html',
			redirectTo : 'empresa.listar',
			ncyBreadcrumb: {
					parent: 'fabricante.menu',
				    label: 'Fabricante'
				  }
		})
		
		.state('fabricante.consultar', {
			url : "/consulta",
			templateUrl : "views/pages/modulo_estoque/fabricante/fabricante.list.html",
			controller : "FabricanteListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'fabricante.menu',
				    label: 'Consulta'
				  }
		})
				
		.state('fabricante.cadastrar', {
			url : "/cadastrar",
			params: {
				empresa: null
			  },
			templateUrl : "views/pages/modulo_estoque/fabricante/fabricante.form.html",
			controller : "FabricanteCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'fabricante.menu',
			    label: 'Cadastrar'
			  }
		})
		
		.state('fabricante.editar', {
			url : "/:idFabricante/editar",
			templateUrl : "views/pages/modulo_estoque/fabricante/fabricante.form.html",
			controller : "FabricanteEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'fabricante.listar',
				    label: 'Editar'
				  }
		
		})
		
		.state('fabricante.visualizar', {
			url : "/visualizar/:idFabricante",
			templateUrl : "views/pages/modulo_estoque/fabricante/fabricante.show.html",
			controller : "FabricanteVisualizarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'fabricante',
				    label: 'Visualizar'
				  }
		})
		.state('fabricante.menu', {
			requiresAuthentication: true,
			url : "/produto/menu",
			templateUrl : "views/pages/modulo_estoque/fabricante/home.html",
			ncyBreadcrumb: {
				 	parent: 'estoque.menu',
				    label: 'Fabricante'
				  }
		})
		
		.state('fabricante.identificar', {
			requiresAuthentication: true,
			url : "/fabricante/identificar",
			params: {
				proximaPagina: null
			  },
			templateUrl : "views/pages/modulo_estoque/empresa_matriz/empresa.identificar.html",
			controller : "EmpresaMatrizCadastarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'estoque.menu',
				    label: 'Fabricante'
				  }
		})

});