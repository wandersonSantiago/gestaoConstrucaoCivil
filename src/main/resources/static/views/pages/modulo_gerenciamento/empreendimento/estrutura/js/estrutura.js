app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('estrutura', {
			abstract : true,
			url : '/estrutura',
			templateUrl : 'views/pages/modulo_gerenciamento/empreendimento/estrutura/estrutura.index.html',
			redirectTo : 'estrutura.listar',
			ncyBreadcrumb: {
					parent: 'empreendimento',
				    label: 'Estrutura'
				  }
		})
		
		.state('estrutura.consultar', {
			url : "/consulta",
			templateUrl : "views/pages/modulo_gerenciamento/empreendimento/estrutura/estrutura.list.html",
			controller : "EstruturaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'estrutura',
				    label: 'Estruturas'
				  }
		})
				
		.state('estrutura.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/pages/modulo_gerenciamento/empreendimento/estrutura/estrutura.form.html",
			controller : "EstruturaCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'empreendimento.menu',
			    label: 'Estrutura'
			  }
		})
		
		.state('estrutura.editar', {
			url : "/:idEstrutura/editar",
			templateUrl : "views/pages/modulo_gerenciamento/empreendimento/estrutura/estrutura.form.html",
			controller : "EstruturaEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'estrutura.consultar',
				    label: 'Editar'
				  },
				  resolve:{
			        	estruturaPrepService: estruturaPrepService
					  }
		
		})
		
		.state('estrutura.visualizar', {
			url : "/:idEstrutura",
			templateUrl : "views/pages/modulo_gerenciamento/empreendimento/estrutura/estrutura.show.html",
			controller : "EstruturaController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'estrutura.consultar',
				    label: 'Visualizar'
				  }
		})

});
estruturaPrepService.$inject = ['$stateParams','EstruturaService']

function estruturaPrepService($stateParams, EstruturaService){
	return EstruturaService.buscarPorId($stateParams.idEstrutura);
}