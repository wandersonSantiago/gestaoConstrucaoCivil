app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('transferencia', {
			url : '/transferencia',
			templateUrl : 'views/pages/modulo_estoque/transferencia/transferencia.index.html',
			redirectTo : 'transferencia.consultar',
			ncyBreadcrumb: {
				parent: 'transferencia.menu'
				  }
		})
		.state('transferencia.enviados', {
			url : "/:tipo/consulta",
			templateUrl : "views/pages/modulo_estoque/transferencia/transferencia.list.html",
			controller : "TransferenciaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'transferencia.menu',
				    label: 'Enviados'
				  }
		})
		
		.state('transferencia.recebidos', {
			url : "/:tipo/consulta",
			templateUrl : "views/pages/modulo_estoque/transferencia/transferencia.list.html",
			controller : "TransferenciaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'transferencia.menu',
				    label: 'Recebidos'
				  }
		})
		.state('transferencia.cadastrar', {
			requiresAuthentication: true,
			url : "/cadastrar",
			templateUrl : "views/pages/modulo_estoque/transferencia/transferencia.form.html",
			controller : "TransferenciaCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'transferencia.home',
			    label: 'Cadastrar'
			  }
		})
		.state('transferencia.editar', {
			requiresAuthentication: true,
			url : "/:idTransferencia/editar",
			templateUrl : "views/pages/modulo_estoque/transferencia/transferencia.form.html",
			controller : "TransferenciaEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'transferencia.consultar',
				    label: 'Editar'
				  }
		})
	
				
		.state('transferencia.menu', {
			requiresAuthentication: true,
			url : "/menu",
			templateUrl : "views/pages/modulo_estoque/transferencia/home.html",
			ncyBreadcrumb: {
				 	parent: 'estoque.menu',
				    label: 'Transferencia'
				  }
		})
		
		.state('transferencia.home', {
			requiresAuthentication: true,
			url : "/home",
			templateUrl : "views/pages/modulo_estoque/transferencia/home.html",
			ncyBreadcrumb: {
				 	parent: 'transferencia.menu',
				    label: 'Menu'
				  }
		})
		.state('transferencia.show', {
			requiresAuthentication: true,
			url : "/:idTransferencia/show",
			templateUrl : "views/pages/modulo_estoque/transferencia/transferencia.show.html",
			controller : "TransferenciaShowController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'transferencia.menu',
				    label: 'Transferencia Show'
				  }
		})
		
});