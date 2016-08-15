app.controller('unidadeMedidaController', function($scope,unidadeMedidaService, $routeParams){
	
	var self = this;
	
	
	 self.cadastrarUnidadeMedida = function(unidadeMedida){
		 console.log(self.unidadeMedida);
		 unidadeMedidaService.unidadeMedidaCreate(self.unidadeMedida);
	}
	 
	 self.buscarUnidadeMedidas = function(){
		 unidadeMedidaService.unidadeMedidaFindAll().
			then(function(t){
				$scope.listaunidadeMedida = t;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar unidade medida');
			});
		};
	
		
});