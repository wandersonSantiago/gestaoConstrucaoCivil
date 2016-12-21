app.controller('prestadoraServicoController', function($scope, buscaCepService, prestadoraServicoService, $routeParams){
	
	var self = this;
		
	var idPrestadoraServico = $routeParams.idPrestadoraServico;
	
	

	

//BUSCA CEP
	self.findCep = function () {
		
		self.cep = $scope.prestadoraCtrl.prestadoraServico.dadoEmpresa.endereco.cep;
		buscaCepService.get({'cep': self.cep}).$promise
		.then(function success(result){
			$scope.prestadoraCtrl.prestadoraServico.dadoEmpresa.endereco = result;
			}).catch(function error(msg) {
		});
		
    }
	
	
	
//CADASTRAR
	 self.salva = function(prestadoraServico){
		 prestadoraServicoService.salva(self.prestadoraServico).
			then(function(response){
				self.prestadoraServico = null;
				}, function(errResponse){
			});
		 
	}
	 
	
//ALTERAR	 
	 	self.altera = function(prestadoraServico){
			prestadoraServicoService.altera(self.prestadoraServico).
			then(function(response){
				self.prestadoraServico = null;
				}, function(errResponse){
			});
		}
	 	

//BUSCAR
		 self.lista = function(){
			 prestadoraServicoService.lista().
				then(function(p){
					self.prestadoraServicos = p;
					}, function(errResponse){
				});
			};
			
			
			self.buscaPorId = function(id){
				if(!id)return;
				prestadoraServicoService.buscaPorId(id).
					then(function(p){
						self.prestadoraServico = p;
							}, function(errResponse){
					});
				};
				
				if(idPrestadoraServico){
					
					self.buscaPorId(idPrestadoraServico);
				}
			 
	
});