app.controller('cargoController', function($scope, cargoService, $routeParams){
	
	var self = this;
		
	var idCargo = $routeParams.idCargo;
	
	
	
	 self.salva = function(cargo){
		cargoService.salva(self.cargo).
		then(function(c){
			self.cargo = null;
			}, function(errResponse){
		});
	}
	 
	 self.altera = function(cargo){
			cargoService.altera(self.cargo).
			then(function(c){
				self.cargo = null;
				}, function(errResponse){
			});
		}
	 
	 self.lista = function(){
		 cargoService.lista().
			then(function(c){
				$scope.cargos = c;
				}, function(errResponse){
			
			});
		};
		self.buscaPorId = function(id){
			if(!id)return;
			 cargoService.buscaPorId(id).
				then(function(c){
					self.cargo = c;
					}, function(errResponse){
				
				});
			};
			if(idCargo){
				self.buscaPorId(idCargo);
			}
		 
	
});