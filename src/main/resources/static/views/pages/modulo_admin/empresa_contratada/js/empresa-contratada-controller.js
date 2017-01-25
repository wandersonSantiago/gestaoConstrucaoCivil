app.controller('adminSistemaController', function($scope, adminSistemaService, buscaCepService, $routeParams){
	
	var self = this;
  
	self.empresa = null;
	self.getPage=0;
	self.totalPages = 0;
	self.totalElements = 0;
	$scope.maxResults = 1;
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
	
	
	self.lista = function(pages, maxResults){
		self.totalPages = [];
		self.getPage=pages;
		
		adminSistemaService.buscaTodosComPaginacao(pages, maxResults).
		then(function(e){			
			self.listaEmpresa = e.content;
			$scope.totalPages = e.totalPages;
			self.totalElements = e.totalElements;
			for(i = 0; i < $scope.totalPages ; i++){
				self.totalPages.push(i);
			}
		}, function(errResponse){
		});
	};

	
	self.buscaPorId = function(id){
		if(!id)return;
		adminSistemaService.buscaPorId(id).
		then(function(p){
			self.empresa = p;
		}, function(errResponse){
		});
	};	
	if(idEmpresa){
		self.buscaPorId(idEmpresa);
	}
	
	
});