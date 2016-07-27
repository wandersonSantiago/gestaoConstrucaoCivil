app.controller('servicoEmpresaController', function($scope,servicoEmpresaService, $routeParams){
	
	var self = this;
		
	$scope.listaServicoEmpresa = [];
	
	
	 self.cadastrarServicoEmpresa = function(servicoEmpresa){
		 console.log(self.servicoEmpresa);
		 servicoEmpresaService.servicoEmpresaCreate(self.servicoEmpresa);
	}
	 
	
	 servicoEmpresaService.servicoEmpresaFindAll().
	  then(function(servicoEmpresa){
		  $scope.listaServicoEmpresa = servicoEmpresa;
	  }, function(errResponse){
			console.error('Erro ao tentar buscar os servicos das empresas');
			return $q.reject(errResponse);
		});
	 
	
});