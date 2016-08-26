app.controller('fornecedorController', function($scope,fornecedorService, buscaCepService, $routeParams){
	
	var self = this;
		
	$scope.listaFornecedor = [];
	
	var idFornecedor = $routeParams.idFornecedor;
	
	
	self.findCep = function () {
		
		self.cep = $scope.fornecedorCtrl.fornecedor.dadoEmpresa.endereco.cep;
		buscaCepService.get({'cep': self.cep}).$promise
		.then(function success(result){
			$scope.fornecedorCtrl.fornecedor.dadoEmpresa.endereco = result;
		}).catch(function error(msg) {
		});
		
    }
	
	
	 self.salva = function(fornecedor){
			 fornecedorService.salva(self.fornecedor);
			 self.fornecedor = fornecedor;
	}
	 
	
	 self.altera = function(fornecedor){
		  fornecedorService.altera(self.fornecedor).
			then(function(response){
				self.fornecedor = null;
				}, function(errResponse){				
			});
}
	 
	 
	 self.lista = function(){
		 fornecedorService.lista().
			then(function(t){
				self.fornecedores = t;
				}, function(errResponse){				
			});
		};
		
		self.buscaPorId = function(id){
			if(!id)return;
			fornecedorService.buscaPorId(id).
			then(function(p){
				self.fornecedor = p;
				}, function(errResponse){
			});
		};
		
	
		if(idFornecedor){
			
			self.buscaPorId(idFornecedor);
		}
		
});