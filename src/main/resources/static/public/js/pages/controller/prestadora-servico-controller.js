app.controller('prestadoraServicoController', function($scope, buscaCepService, prestadoraServicoService, $routeParams){
	
	var self = this;
		
	var idPrestadoraServico = $routeParams.idPrestadoraServico;
	
	

	

//BUSCA CEP
	self.findCep = function () {
		
		self.cep = $scope.prestadoraCtrl.prestadoraServico.dadoEmpresa.endereco.cep;
		console.log(self.cep );
		buscaCepService.get({'cep': self.cep}).$promise
		.then(function success(result){
			$scope.prestadoraCtrl.prestadoraServico.dadoEmpresa.endereco = result;
			}).catch(function error(msg) {
			console.error('Error');
		});
		
    }
	
	
	
//CADASTRAR
	 self.cadastrarPrestadoraServico = function(prestadoraServico){
		 console.log(self.prestadoraServico);
		 prestadoraServicoService.prestadoraServicoCreate(self.prestadoraServico);
	}
	 
	
//ALTERAR	 
	 	self.editarPrestadoraServico = function(prestadoraServico){
			prestadoraServicoService.prestadoraServicoUpdate(self.prestadoraServico);
						
		}
	 	

//BUSCAR
		 self.buscarPrestadoraServico = function(){
			 prestadoraServicoService.prestadoraServicoFindAll().
				then(function(p){
					$scope.prestadoraServicos = p;
					}, function(errResponse){
					toastr.error('Erro ao tentar buscar prestadora de servico');
				});
			};
			
			
			self.buscarPrestadoraServicoPorId = function(id){
				if(!id)return;
				prestadoraServicoService.prestadoraServicoFindOne(id).
					then(function(p){
						self.prestadoraServico = p;
						
						}, function(errResponse){
						toastr.error('Erro ao tentar buscar prestadora Servico');
					});
				};
				
				if(idPrestadoraServico){
					
					self.buscarPrestadoraServicoPorId(idPrestadoraServico);
				}
			 
	
});