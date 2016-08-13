app.controller('adminSistemaController', function($scope, adminSistemaService, buscaCepService, $routeParams){
	
	var self = this;
  
	self.empresa = null;
	var idEmpresa =  $routeParams.idEmpresa;

	$scope.cep = "99150000", $scope.city = null;
	self.findCep = function () {
		
		console.log("teste");
		buscaCepService.get({'cep': self.cep}).$promise
		.then(function success(result){
			$scope.city = result;
			$scope.empresa.dadoEmpresa.endereco.cidade = $scope.city.localidade;
			$scope.empresa.dadoEmpresa.endereco.bairro = $scope.bairro.localidade;
			$scope.empresa.dadoEmpresa.endereco.cidade = $scope.city.localidade;
			$scope.empresa.dadoEmpresa.endereco.cidade = $scope.city.localidade;
			$scope.empresa.dadoEmpresa.endereco.cidade = $scope.city.localidade;
			$scope.empresa.dadoEmpresa.endereco.cidade = $scope.city.localidade;
			$scope.empresa.dadoEmpresa.endereco.cidade = $scope.city.localidade;
			
		}).catch(function error(msg) {
			console.error('Error');
		});
    }
	
	
	self.createEmpresa = function(empresa, sucesso){
		console.log(self.empresa);
		adminSistemaService.empresaCreate(self.empresa);
		self.empresa = empresa;
		
	}
	
	self.updateEmpresa = function(empresa, sucesso){
		console.log(self.empresa);
		adminSistemaService.empresaUpdate(self.empresa);
	
		self.empresa = empresa;
		
	}
//carrega a lista de empresa, quando acessa o controller
	self.buscarEmpresas = function(){
		adminSistemaService.empresaFindAll().
		then(function(e){
			self.listaEmpresa = e;
			console.log("to aqui",self.listaEmpresa);
		}, function(errResponse){
			toastr.error('Erro ao buscar empresas');
		});
	};

	
//busca a empresa atraves do id
	self.buscarEmpresaPorId = function(id){
		if(!id)return;
		adminSistemaService.buscarEmpresa(id).
		then(function(p){
			self.empresa = p;
			console.log(idEmpresa);
		}, function(errResponse){
			toastr.error('Erro ao buscar empresas');
		});
	};
//verifica se o params esta com o ide executa o metodo de busca 	
	if(idEmpresa){
		self.buscarEmpresaPorId(idEmpresa);
		
	}
	
	/*if(self.ativarBusca == 1){
		
		self.buscarEmpresas();
	}*/
	
	$scope.maskFone= '(99) 9999 - 999?9';
	$scope.maskCnpj= '99.999.999/9999-99';
	$scope.maskIscEstadual= '999.999.999.999';
	$scope.maskCep = '99999-999';
});