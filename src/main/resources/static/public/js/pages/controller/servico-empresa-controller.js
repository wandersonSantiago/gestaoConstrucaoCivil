app.controller('servicoEmpresaController', function($scope,servicoEmpresaService, $routeParams){
	
	var self = this;
		
	
	
	 self.salva = function(servicoEdifico){
		 servicoEmpresaService.salva(self.servicoEdifico).
			then(function(response){
				self.servicoEdificio = null;
				}, function(errResponse){
			});
		}
	 
	 self.altera = function(servicoEmpresa){
		 servicoEmpresaService.altera(self.servicoEmpresa).
			then(function(response){
				self.servicoEmpresa = null;
				}, function(errResponse){
			});
		}
	 
	 self.lista = function(){
		 servicoEmpresaService.lista().
			then(function(t){
				self.listaServicoEmpresa = t;
				console.log(self.listaServicoEmpresa);
				}, function(errResponse){
			});
		};
		
		$scope.tipo = {
				tipo : ''	
			};		
			self.tipoConf = function(){
			if($scope.tipo.tipo == "edificio"){
				$scope.casa = false;
				$scope.comunitaria = false;
				$scope.edificio = true;
		}
			if($scope.tipo.tipo == "comunitaria"){
				$scope.casa = false;
				$scope.edificio = false;
				$scope.comunitaria = true;
			}
			if($scope.tipo.tipo == "casa"){
				$scope.comunitaria = false;
				$scope.edificio = false;
				$scope.casa = true;
				
			}
			}

	
		
});