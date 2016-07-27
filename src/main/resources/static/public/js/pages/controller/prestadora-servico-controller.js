app.controller('PrestadoraServicoController', function($scope,PrestadoraServicoService, $routeParams){
	
	var self = this;
		
	$scope.listaPrestadoraServico = [];
	
	
	 self.cadastrarPrestadoraServico = function(prestadoraServico){
		 console.log(self.prestadoraServico);
		 prestadoraServicoService.prestadoraServicoCreate(self.prestadoraServico);
	}
	 
	
	 prestadoraServicoService.prestadoraServicoFindAll().
	  then(function(prestadoraServico){
		  $scope.listaPrestadoraServico = prestadoraServico;
	  }, function(errResponse){
			console.error('Erro ao tentar buscar prestadora de servico');
			return $q.reject(errResponse);
		});
	 
	
});