app.controller('cargoController', function($scope, cargoService, $routeParams){
	
	var self = this;
	
	
	
	self.createEmpresa = function(cargo){
		console.log("Teste");
		console.log(self.cargo);
		cargoService.cargoCreate(self.cargo);
	}
	
});