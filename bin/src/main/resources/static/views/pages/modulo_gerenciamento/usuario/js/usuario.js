app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('usuario', {
			url : '/usuario',
			templateUrl : 'views/pages/modulo_gerenciamento/usuario/usuario.index.html',
			redirectTo : 'usuario.listar',
			ncyBreadcrumb: {
				parent: 'usuario.menu'
				  }
		})
		.state('usuario.listar', {
			url : "/listar",
			templateUrl : "views/pages/modulo_gerenciamento/usuario/usuario.list.html",
			controller : "UsuarioListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'usuario.menu',
				    label: 'Lista'
				  }
		})
		.state('usuario.perfil', {
			url : "/perfil",
			templateUrl : "views/pages/modulo_gerenciamento/usuario/usuario.perfil.html",
			controller : "UsuarioPerfilController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'usuario',
				    label: 'Perfil'
				  }
		})
		
		.state('usuario.cadastrar', {
			requiresAuthentication: true,
			url : "/cadastrar",
			templateUrl : "views/pages/modulo_gerenciamento/usuario/usuario.form.html",
			controller : "UsuarioCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'usuario.menu',
			    label: 'Cadastrar'
			  }
		})
		.state('usuario.editar', {
			requiresAuthentication: true,
			url : "/:idUsuario/editar",
			templateUrl : "views/pages/modulo_gerenciamento/usuario/usuario.form.html",
			controller : "UsuarioEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'usuario.listar',
				    label: 'Editar'
				  }
		})
		.state('usuario.permissao', {
			requiresAuthentication: true,
			url : "/:idUsuario/permissao",
			templateUrl : "views/pages/modulo_gerenciamento/usuario/usuario.permissao.html",
			controller : "UsuarioPermissaoController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'usuario.listar',
				    label: 'Permissao'
				  }
		})
		.state('usuario.menu', {
			requiresAuthentication: true,
			url : "/usuario/menu",
			templateUrl : "views/pages/modulo_gerenciamento/usuario/home.html",
			ncyBreadcrumb: {
				 	parent: 'gerenciamento',
				    label: 'usuario'
				  }
		})

});