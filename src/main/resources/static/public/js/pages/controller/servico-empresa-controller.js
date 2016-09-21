app.controller('servicoEmpresaController', function($scope,servicoEmpresaService, $routeParams){
	
	var self = this;
		
	
	
	 self.salva = function(servicoEdifico){
		 console.log(self.servicoEdifico);
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
				}, function(errResponse){
			});
		};
		
		self.verifica = function(verifica){
			
			if($scope.torreCheck == true){
				$scope.torre = true;
			}
			if($scope.andarCheck == true){
				$scope.andar = true;
				$scope.torre = true;
			}if($scope.apartamentoCheck == true){
				$scope.andar = true;
				$scope.torre = true;
				$scope.apartamento = true;
			}
			if($scope.torreCheck == false && $scope.andarCheck == false
					&& $scope.apartamentoCheck == false){
				$scope.torre = false;
			}
			if($scope.andarCheck == false && $scope.apartamentoCheck == false){
				$scope.andar = false;
				
			}if($scope.apartamentoCheck == false){
				
				$scope.apartamento = false;
			}
		}
	
		
});