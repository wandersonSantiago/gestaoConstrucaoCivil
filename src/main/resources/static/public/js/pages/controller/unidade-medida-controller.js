app.controller('unidadeMedidaController', function($scope,unidadeMedidaService, $routeParams){
	
	var self = this;
	
	
	 self.cadastrarUnidadeMedida = function(unidadeMedida){
		 unidadeMedidaService.unidadeMedidaCreate(self.unidadeMedida);
		 self.unidadeMedida = unidadeMedida;
	}
	 
	 self.buscarUnidadeMedidas = function(){
		 unidadeMedidaService.unidadeMedidaFindAll().
			then(function(t){
				self.listaUnidadeMedida = t;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar unidade medida');
			});
		};
	
		
});