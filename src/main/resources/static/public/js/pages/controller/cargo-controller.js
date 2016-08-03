app.controller('cargoController', function($scope, cargoService, $routeParams){
	
	var self = this;
		
	var idCargo = $routeParams.idCargo;
	
	
	
	 self.createCargo = function(cargo){
		cargoService.cargoCreate(self.cargo);
		self.cargo = cargo;
	}
	 
	 self.updateCargo = function(cargo){
			cargoService.cargoUpdate(self.cargo);
			self.cargo = cargo;
		}
	 
	 self.buscarCargos = function(){
		 cargoService.cargoFindAll().
			then(function(c){
				$scope.cargos = c;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar cargo');
			});
		};
		self.buscarCargoPorId = function(id){
			if(!id)return;
			 cargoService.cargoFindOne(id).
				then(function(c){
					self.cargo = c;
					}, function(errResponse){
					toastr.error('Erro ao tentar buscar cargo');
				});
			};
			if(idCargo){
				self.buscarCargoPorId(idCargo);
			}
		 
	
});