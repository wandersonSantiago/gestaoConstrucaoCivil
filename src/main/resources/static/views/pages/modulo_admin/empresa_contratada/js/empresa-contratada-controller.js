app.controller('adminSistemaController', function($scope, adminSistemaService, buscaCepService, $routeParams){
	
	var self = this;
  
	self.empresa = null;
	
	var idEmpresa =  $routeParams.idEmpresa;
	


	self.findCep = function () {
		
		self.cep = $scope.adminCtrl.empresa.dadoEmpresa.endereco.cep;
		console.log(self.cep );
		buscaCepService.get({'cep': self.cep}).$promise
		.then(function success(result){
			$scope.adminCtrl.empresa.dadoEmpresa.endereco = result;
		}).catch(function error(msg) {
		});
   }
	
	
	self.salva = function(empresa, sucesso){
		
		adminSistemaService.salva(self.empresa).
		then(function(e){
			self.empresa = empresa;
		}, function(errResponse){
			
		});
		
		
	}
	
	self.altera = function(empresa, sucesso){
		adminSistemaService.altera(self.empresa).
		then(function(e){
			self.empresa = null;
		}, function(errResponse){
		});
}
	
//carrega a lista de empresa, quando acessa o controller
	self.lista = function(){
		adminSistemaService.lista().
		then(function(e){
			self.listaEmpresa = e;
		}, function(errResponse){
		});
	};

	
//busca a empresa atraves do id
	self.buscaPorId = function(id){
		if(!id)return;
		adminSistemaService.buscaPorId(id).
		then(function(p){
			self.empresa = p;
		}, function(errResponse){
		});
	};
//verifica se o params esta com o ide executa o metodo de busca 	
	if(idEmpresa){
		self.buscaPorId(idEmpresa);
	}
	
	
});