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
			 fornecedorService.fornecedorCreate(self.fornecedor);
			 self.fornecedor = fornecedor;
	}
	 
	
	 self.alterarFornecedor = function(fornecedor){
		  fornecedorService.fornecedorUpdate(self.fornecedor);
		  self.fornecedor = fornecedor;
	}
	 
	 
	 self.buscarFornecedor = function(){
		 fornecedorService.fornecedorFindAll().
			then(function(t){
				self.fornecedores = t;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar fornecedor');
			});
		};
		
		self.buscarFornecedorPorId = function(id){
			if(!id)return;
			fornecedorService.fornecedorFindOne(id).
			then(function(p){
				self.fornecedor = p;
				}, function(errResponse){
				toastr.error('Erro ao buscar fornecedor');
			});
		};
		
	
		if(idFornecedor){
			
			self.buscarFornecedorPorId(idFornecedor);
		}
		
});