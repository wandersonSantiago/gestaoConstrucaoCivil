app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
		.state('financeiro', {
			requiresAuthentication: true,
			url : "/fincanceiro",
			templateUrl : "views/pages/modulo_financeiro/home.html",
			ncyBreadcrumb: {
				 	parent: 'home',
				    label: 'Fincanceiro'
				  }
		})
		
		
});