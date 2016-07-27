app.controller('pacoteServicoController', function($scope,pacoteServicoService, $routeParams){
	
	var self = this;
		
	$scope.listaPacoteServico = [];
	
	
	 self.cadastrarPacoteServico = function(pacoteServico){
		 console.log(self.pacoteServico);
		 pacoteServicoService.pacoteServicoCreate(self.pacoteServico);
	}
	 
	
	 pacoteServicoService.pacoteServicoFindAll().
	  then(function(pacoteServico){
		  $scope.listaPacoteServico = pacoteServico;
	  }, function(errResponse){
			console.error('Erro ao tentar buscar os pacotes de serviço');
			return $q.reject(errResponse);
		});
	 
	
});