app.controller('fornecedorController', function($scope,fornecedorService, $routeParams){
	
	var self = this;
		
	$scope.listaFornecedor = [];
	
	
	 self.cadastrarFornecedor = function(fornecedor){
		 console.log(self.fornecedor);
		 fornecedorService.fornecedorCreate(self.fornecedor);
	}
	 
	
	 fornecedorService.fornecedorFindAll().
	  then(function(fornecedor){
		  $scope.listaFornecedor = fornecedor;
	  }, function(errResponse){
			console.error('Erro ao tentar buscar fornecedor');
			return $q.reject(errResponse);
		});
	 
	
});