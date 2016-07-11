app.controller('unidadeMedidaController', function($scope,unidadeMedidaService, $routeParams){
	
	var self = this;
		
	$scope.listaUnidadeMedida = [];
	
	
	 self.cadastrarUnidadeMedida = function(unidadeMedida){
		 console.log(self.unidadeMedida);
		 unidadeMedidaService.unidadeMedidaCreate(self.unidadeMedida);
	}
	 
	
	 unidadeMedidaService.unidadeMedidaFindAll().
	  then(function(unidadeMedida){
		  $scope.listaunidadeMedida = unidadeMedida;
	  }, function(errResponse){
			console.error('Erro ao tentar buscar unidade medida');
			return $q.reject(errResponse);
		});
	 
	
});