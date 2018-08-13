app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('vincular', {
			abstract : true,
			url : '/empresa/vincular',
			templateUrl : 'views/pages/modulo_servicos/vincular/vincular.index.html',
			redirectTo : 'servico.menu',
			ncyBreadcrumb: {
				    label: 'Vincular'
				  }
		})
		
		.state('vincular.consultar', {
			url : "/consulta",
			templateUrl : "views/pages/modulo_servicos/vincular/vincular.list.html",
			controller : "VincularListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'vincular.menu',
				    label: 'Consulta'
				  }
		})
				
		.state('vincular.cadastrar', {
			url : "/cadastrar",
			params: {
				empresa: null
			  },
			templateUrl : "views/pages/modulo_servicos/vincular/vincular.form.html",
			controller : "VincularCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'vincular.menu',
			    label: 'Cadastrar'
			  }
		})
		
		.state('vincular.editar', {
			url : "/:idVincular/editar",
			templateUrl : "views/pages/modulo_servicos/vincular/vincular.form.html",
			controller : "VincularEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'vincular.consultar',
				    label: 'Editar'
				  }
		
		})
		
		.state('vincular.visualizar', {
			url : "/visualizar/:idVincular",
			templateUrl : "views/pages/modulo_servicos/vincular/vincular.show.html",
			controller : "VincularVisualizarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'vincular.menu',
				    label: 'Visualizar'
				  }
		})
		.state('vincular.menu', {
			requiresAuthentication: true,
			url : "/produto/menu",
			templateUrl : "views/pages/modulo_servicos/vincular/home.html",
			ncyBreadcrumb: {
				 	parent: 'servicos',
				    label: 'Vincular'
				  }
		})
		

});