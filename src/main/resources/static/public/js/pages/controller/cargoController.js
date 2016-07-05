app.controller('cargoController', function($scope, cargoService, $routeParams){
	
	var self = this;
	
	self.cargo = {descricao:""}
	   
	 self.createCargo = function(cargo){
		
		
		cargoService.cargoCreate(self.cargo);
	}
	
});