app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('prestadora', {
			abstract : true,
			url : '/empresa/prestadora',
			templateUrl : 'views/pages/modulo_servicos/prestadora/prestadora.index.html',
			redirectTo : 'servico.menu',
			ncyBreadcrumb: {
				    label: 'Prestadora'
				  }
		})
		
		.state('prestadora.consultar', {
			url : "/consulta",
			templateUrl : "views/pages/modulo_servicos/prestadora/prestadora.list.html",
			controller : "PrestadoraListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'prestadora.menu',
				    label: 'Consulta'
				  }
		})
				
		.state('prestadora.cadastrar', {
			url : "/cadastrar",
			params: {
				empresa: null
			  },
			templateUrl : "views/pages/modulo_servicos/prestadora/prestadora.form.html",
			controller : "PrestadoraCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'prestadora.menu',
			    label: 'Cadastrar'
			  }
		})
		
		.state('prestadora.editar', {
			url : "/:idPrestadora/editar",
			templateUrl : "views/pages/modulo_servicos/prestadora/prestadora.form.html",
			controller : "PrestadoraEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'prestadora.consultar',
				    label: 'Editar'
				  }
		
		})
		
		.state('prestadora.visualizar', {
			url : "/visualizar/:idPrestadora",
			templateUrl : "views/pages/modulo_servicos/prestadora/prestadora.show.html",
			controller : "PrestadoraVisualizarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'prestadora.menu',
				    label: 'Visualizar'
				  }
		})
		.state('prestadora.menu', {
			requiresAuthentication: true,
			url : "/produto/menu",
			templateUrl : "views/pages/modulo_servicos/prestadora/home.html",
			ncyBreadcrumb: {
				 	parent: 'servicos',
				    label: 'Prestadora'
				  }
		})
		
		.state('prestadora.identificar', {
			requiresAuthentication: true,
			url : "/prestadora/identificar",
			params: {
				proximaPagina: null
			  },
			templateUrl : "views/pages/modulo_estoque/empresa_matriz/empresa.identificar.html",
			controller : "EmpresaMatrizCadastarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'prestadora.menu',
				    label: 'Prestadora'
				  }
		})

});