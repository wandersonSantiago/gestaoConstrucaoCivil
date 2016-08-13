app.controller('fornecedorController', function($scope,fornecedorService, buscaCepService, $routeParams){
	
	var self = this;
		
	$scope.listaFornecedor = [];
	var idFornecedor = $routeParams.idFornecedor;
	
	
self.findCep = function () {
		
		self.cep = $scope.fornecedorCtrl.fornecedor.dadoEmpresa.endereco.cep;
		console.log(self.cep );
		buscaCepService.get({'cep': self.cep}).$promise
		.then(function success(result){
			$scope.fornecedorCtrl.fornecedor.dadoEmpresa.endereco = result;
		

	
		}).catch(function error(msg) {
			console.error('Error');
		});
		
    }
	
	
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