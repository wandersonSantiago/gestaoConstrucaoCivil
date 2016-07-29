app.controller('fornecedorController', function($scope,fornecedorService, $routeParams){
	
	var self = this;
		
	$scope.listaFornecedor = [];
	
	
	 self.cadastrarFornecedor = function(fornecedor){
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