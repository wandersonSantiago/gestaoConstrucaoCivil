app.controller('fornecedorController', function($scope,fornecedorService, $routeParams){
	
	var self = this;
		
	$scope.listaFornecedor = [];
	var idFornecedor = $routeParams.idFornecedor;
	
	 self.createFornecedor = function(fornecedor){
		 console.log(self.fornecedor);
		 fornecedorService.fornecedorCreate(self.fornecedor);
	}
	 
	 
	 self.buscarFornecedores = function(){
		 fornecedorService.fornecedorFindAll().
			then(function(t){
				self.listaFornecedor = t;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar fornecedor');
			});
		};
});