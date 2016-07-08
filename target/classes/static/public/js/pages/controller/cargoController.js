app.controller('cargoController', function($scope, cargoService, $routeParams){
	
	var self = this;
		
	$scope.listaCargo = [];
	
	
	 self.createCargo = function(cargo){
		cargoService.cargoCreate(self.cargo);
	}
	 
	
	 cargoService.cargoFindAll().
	  then(function(c){
		  $scope.listaCargo = c;
	  }, function(errResponse){
			console.error('Erro ao tentar buscar cargo');
			return $q.reject(errResponse);
		});
	 
	
});