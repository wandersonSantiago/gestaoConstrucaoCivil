app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('pacote', {
			abstract : true,
			url : '/empresa/pacote',
			templateUrl : 'views/pages/modulo_servicos/pacotes/pacote.index.html',
			redirectTo : 'servico.menu',
			ncyBreadcrumb: {
				    label: 'Pacote'
				  }
		})
		
		.state('pacote.consultar', {
			url : "/consulta",
			templateUrl : "views/pages/modulo_servicos/pacotes/pacote.list.html",
			controller : "PacoteListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'pacote.menu',
				    label: 'Consulta'
				  }
		})
				
		.state('pacote.cadastrar', {
			url : "/cadastrar",
			params: {
				empresa: null
			  },
			templateUrl : "views/pages/modulo_servicos/pacotes/pacote.form.html",
			controller : "PacoteCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'pacote.menu',
			    label: 'Cadastrar'
			  }
		})
		
		.state('pacote.editar', {
			url : "/:idPacote/editar",
			templateUrl : "views/pages/modulo_servicos/pacotes/pacote.form.html",
			controller : "PacoteEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'pacote.consultar',
				    label: 'Editar'
				  }
		
		})
		
		.state('pacote.visualizar', {
			url : "/visualizar/:idPacote",
			templateUrl : "views/pages/modulo_servicos/pacotes/pacote.show.html",
			controller : "PacoteVisualizarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'pacote.menu',
				    label: 'Visualizar'
				  }
		})
		.state('pacote.menu', {
			requiresAuthentication: true,
			url : "/produto/menu",
			templateUrl : "views/pages/modulo_servicos/pacotes/home.html",
			ncyBreadcrumb: {
				 	parent: 'servicos',
				    label: 'Pacote'
				  }
		})
		

});