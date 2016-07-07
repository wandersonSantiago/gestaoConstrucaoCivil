app.controller('cargoController', function($scope, cargoService, $routeParams){
	
	var self = this;
	
	
	   
	 self.createCargo = function(cargo){
	
		cargoService.cargoCreate(self.cargo);
	}
	
});