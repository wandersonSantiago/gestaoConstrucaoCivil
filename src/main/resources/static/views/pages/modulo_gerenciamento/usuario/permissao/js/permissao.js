app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('permissao', {
			abstract : true,
			url : '/permissao',
			templateUrl : 'views/pages/modulo_gerenciamento/usuario/usuario.index.html',
			redirectTo : 'usuario.listar',
			ncyBreadcrumb: {
				parent: 'home',
				    label: 'home'
				  }
		})
		.state('permissao.listar', {
			url : "",
			templateUrl : "views/pages/modulo_gerenciamento/usuario/permissao/permissao.list.html",
			controller : "PermissaoListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'permissao.cadastrar',
				    label: 'Listar permissões'
				  }
		})
		.state('permissao.cadastrar', {
			requiresAuthentication: true,
			url : "/cadastrar",
			templateUrl : "views/pages/modulo_gerenciamento/usuario/permissao/permissao.form.html",
			controller : "PermissaoCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'permissao',
			    label: 'Cadastrar permissões'
			  }
		})
		.state('permissao.editar', {
			requiresAuthentication: true,
			url : "/:idPermissao/editar",
			templateUrl : "views/pages/modulo_gerenciamento/usuario/permissao/permissao.form.html",
			controller : "PermissaoEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'permissao.listar',
				    label: 'Editar permissões'
				  }
		})
		

});