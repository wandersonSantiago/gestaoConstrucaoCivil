app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('vistoria', {
			abstract : true,
			url : '/empresa/vistoria',
			templateUrl : 'views/pages/modulo_servicos/vistoria/vistoria.index.html',
			redirectTo : 'servico.menu',
			ncyBreadcrumb: {
				parent: 'servico.menu',
				    label: 'Vistoria'
				  }
		})
		
		.state('vistoria.consultar', {
			url : "/consulta",
			templateUrl : "views/pages/modulo_servicos/vistoria/vistoria.list.html",
			controller : "VistoriaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'vistoria',
				    label: 'Consulta'
				  }
		})
				
		.state('vistoria.cadastrar', {
			url : "/cadastrar",
			params: {
				empresa: null
			  },
			templateUrl : "views/pages/modulo_servicos/vistoria/vistoria.form.html",
			controller : "VistoriaCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'vistoria.consultar',
			    label: 'Cadastrar'
			  }
		})
		
		.state('vistoria.editar', {
			url : "/:idVistoria/editar",
			templateUrl : "views/pages/modulo_servicos/vistoria/vistoria.form.html",
			controller : "VistoriaEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'vistoria.consultar',
				    label: 'Editar'
				  }		
		})
		
		.state('vistoria.visualizar', {
			url : "/visualizar/:idVistoria",
			templateUrl : "views/pages/modulo_servicos/vistoria/vistoria.show.html",
			controller : "VistoriaVisualizarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'vistoria.consultar',
				    label: 'Visualizar'
				  }
		})
				

});