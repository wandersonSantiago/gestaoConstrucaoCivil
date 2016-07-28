app.controller('cargoController', function($scope, cargoService, $routeParams){
	
	var self = this;
		
	
	
	
	 self.createCargo = function(cargo){
		cargoService.cargoCreate(self.cargo);
	}
	 
	 self.buscarCargos = function(){
		 cargoService.cargoFindAll().
			then(function(c){
				self.listaCargo = c;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar cargo');
			});
		};
	
		 
	
});