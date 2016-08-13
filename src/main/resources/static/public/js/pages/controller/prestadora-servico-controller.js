app.controller('prestadoraServicoController', function($scope, buscaCepService, prestadoraServicoService, $routeParams){
	
	var self = this;
		
	$scope.listaPrestadoraServico = [];
	
	


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
	
	 self.cadastrarPrestadoraServico = function(prestadoraServico){
		 console.log(self.prestadoraServico);
		 prestadoraServicoService.prestadoraServicoCreate(self.prestadoraServico);
	}
	 
	 self.buscarPrestadoraServico = function(){
		 prestadoraServicoService.prestadoraServicoFindAll().
			then(function(t){
				self.listaPrestadoraServico = t;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar prestadora de servico');
			});
		};
	 
		 
	
});