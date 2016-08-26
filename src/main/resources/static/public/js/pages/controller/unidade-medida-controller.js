app.controller('unidadeMedidaController', function($scope,unidadeMedidaService, $routeParams){
	
	var self = this;
	
		 
	 self.lista = function(){
		 unidadeMedidaService.lista().
			then(function(t){
				self.listaUnidadeMedida = t;
				}, function(errResponse){
			});
		};
	
		
});