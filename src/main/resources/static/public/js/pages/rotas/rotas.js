app
		.config(function($stateProvider, $urlRouterProvider, $httpProvider) {

			$urlRouterProvider.otherwise("/404");

			$stateProvider.state('home', {
				url : "",
				templateUrl : "views/home.html",
				controller : "HomeListaController as ctrl",
				ncyBreadcrumb : {
					label : 'Home'
				}
			})

			.state('/', {
				url : "/home",
				templateUrl : "views/home.html",
				controller : "HomeListaController as ctrl",
				ncyBreadcrumb : {
					parent : 'home',
					label : 'Menu'
				}
			})

			.state('gerenciamento', {
				url : "/gerenciamento/menu",
				templateUrl : "views/pages/modulo_gerenciamento/home.html",
				ncyBreadcrumb : {
					parent : '/',
					label : 'Gerenciamento'
				}
			}).state('compras', {
				url : "/compras/menu",
				templateUrl : "views/pages/modulo_compras/home.html",
				ncyBreadcrumb : {
					parent : '/',
					label : 'Compras'
				}
			}).state('servicos', {
				url : "/servicos/menu",
				templateUrl : "views/pages/modulo_servicos/home.html",
				ncyBreadcrumb : {
					parent : '/',
					label : 'Servicos'
				}
			}).state('login', {
				templateUrl : "views/login.html",
				ncyBreadcrumb : {
					skip : true
				}
			}).state('/500', {
				url : "/500",
				templateUrl : "views/erros/permissao.html",
				ncyBreadcrumb : {
					skip : true
				}
			})
			$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

		});