app.config(function($stateProvider, $urlRouterProvider, $httpProvider) {

			 $urlRouterProvider.otherwise("/404");

			$stateProvider.state('home', {
				url : "",
				templateUrl : "views/home.html",
				controller : "HomeListaController as ctrl",
				ncyBreadcrumb : {
					label : 'Home'
				}})
			
			.state('/', {
				url : "/home",
				templateUrl : "views/home.html",
				controller : "HomeListaController as ctrl",
				ncyBreadcrumb : {
					parent : 'home',
					label : 'Religioso'
				}
			})

			.state('login', {
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