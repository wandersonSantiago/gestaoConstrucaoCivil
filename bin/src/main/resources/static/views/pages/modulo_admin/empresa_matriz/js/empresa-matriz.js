app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('matriz', {
			abstract : true,
			url : '/empresa/matriz',
			templateUrl : 'views/pages/modulo_admin/empresa_matriz/empresa_matriz.index.html',
			redirectTo : 'empresa.listar',
			ncyBreadcrumb: {
					parent: 'home',
				    label: 'Empresa Matriz'
				  }
		})
		
		.state('matriz.consultar', {
			url : "/consulta",
			templateUrl : "views/pages/modulo_admin/empresa_matriz/empresa_matriz.list.html",
			controller : "EmpresaMatrizListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'matriz',
				    label: 'Empresas'
				  }
		})
				
		.state('matriz.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/pages/modulo_admin/empresa_matriz/empresa_matriz.form.html",
			controller : "EmpresaMatrizCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'matriz',
			    label: 'Cadastrar'
			  }
		})
		
		.state('matriz.editar', {
			url : "/:idMatriz/editar",
			templateUrl : "views/pages/modulo_admin/empresa_matriz/empresa_matriz.form.html",
			controller : "EmpresaMatrizEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'matriz.listar',
				    label: 'Editar'
				  }
		
		})
		
		.state('matriz.visualizar', {
			url : "/:idMatriz",
			templateUrl : "views/pages/modulo_admin/empresa_matriz/empresa_matriz.show.html",
			controller : "EmpresaMatrizController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'matriz.listar',
				    label: 'Visualizar'
				  }
		})

});