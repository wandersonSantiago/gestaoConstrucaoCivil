app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('matriz', {
			abstract : true,
			url : '/empresa/matriz',
			templateUrl : 'views/pages/modulo_estoque/empresa_matriz/empresa_matriz.index.html',
			redirectTo : 'empresa.listar',
			ncyBreadcrumb: {
					parent: 'estoque.menu',
				    label: 'Empresa Matriz'
				  }
		})
		
		.state('matriz.consultar', {
			url : "/consulta",
			templateUrl : "views/pages/modulo_estoque/empresa_matriz/empresa_matriz.list.html",
			controller : "EmpresaMatrizListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'matriz',
				    label: 'Empresas'
				  }
		})
				
		.state('matriz.cadastrar', {
			url : "/cadastrar",
			params: {
				cnpj: null
			  },
			templateUrl : "views/pages/modulo_estoque/empresa_matriz/empresa_matriz.form.html",
			controller : "EmpresaMatrizCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'matriz',
			    label: 'Cadastrar'
			  }
		})
		
		.state('matriz.editar', {			
			url : "/:idEmpresaMatriz/editar",
			templateUrl : "views/pages/modulo_estoque/empresa_matriz/empresa_matriz.form.html",
			controller : "EmpresaMatrizEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'matriz.consultar',
				    label: 'Editar'
				  }
		
		})
		
		.state('matriz.visualizar', {
			url : "/:idEmpresaMatriz",
			templateUrl : "views/pages/modulo_estoque/empresa_matriz/empresa_matriz.show.html",
			controller : "EmpresaMatrizController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'matriz.consultar',
				    label: 'Visualizar'
				  }
		})
		.state('matriz.identificar', {
			requiresAuthentication: true,
			url : "/empresa/identificar",
			params: {
				proximaPagina: null
			  },
			templateUrl : "views/pages/modulo_estoque/empresa_matriz/empresa.identificar.html",
			controller : "EmpresaMatrizCadastarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'matriz',
				    label: 'Empresa'
				  }
		})

});