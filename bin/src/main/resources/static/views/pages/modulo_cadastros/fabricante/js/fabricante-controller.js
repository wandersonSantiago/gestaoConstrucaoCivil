app.controller('fabricanteController', function($scope, fabricanteService, buscaCepService, $routeParams){
	
	var self = this;
  
	self.fornecedor = null;
	
	var idFabricante =  $routeParams.idFabricante;
	


	self.findCep = function () {
		
		self.cep = $scope.fabricanteCtrl.fabricante.dadoEmpresa.endereco.cep;
		console.log(self.cep );
		buscaCepService.get({'cep': self.cep}).$promise
		.then(function success(result){
			$scope.fabricanteCtrl.fabricante.dadoEmpresa.endereco = result;
		}).catch(function error(msg) {
		});
   }
	
	
	self.salva = function(fabricante, sucesso){
		
		fabricanteService.salva(self.fabricante).
		then(function(response){
			self.fabricante = fabricante;
		}, function(errResponse){
			
		});
		
		
	}
	
	self.altera = function(fabricante, sucesso){
		fabricanteService.altera(self.fabricante).
		then(function(e){
			self.fabricante = null;
		}, function(errResponse){
		});
}
	
//carrega a lista de empresa, quando acessa o controller
	self.lista = function(){
		fabricanteService.lista().
		then(function(e){
			self.listaFabricante = e;
		}, function(errResponse){
		});
	};

	
//busca a empresa atraves do id
	self.buscaPorId = function(id){
		if(!id)return;
		fabricanteService.buscaPorId(id).
		then(function(p){
			self.fabricante = p;
		}, function(errResponse){
		});
	};
//verifica se o params esta com o ide executa o metodo de busca 	
	if(idFabricante){
		self.buscaPorId(idFabricante);
	}
	
	
});