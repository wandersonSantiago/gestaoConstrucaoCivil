app.controller('adminSistemaController', function($scope, adminSistemaService, $routeParams){
	
	var self = this;
  
	self.empresa = null;
	var idEmpresa =  $routeParams.idEmpresa;
	
	$scope.listaEmpresa = [];
	
	console.log(idEmpresa);
	self.createEmpresa = function(empresa, sucesso){
		console.log("funfou o controle");
		console.log(self.empresa);
		adminSistemaService.empresaCreate(self.empresa);
	
		self.empresa = empresa;
		
	}
//carrega a lista de empresa, quando acessa o controller
	adminSistemaService.empresaFindAll().
	  then(function(c){
		  console.log("funfou o controle");
		  console.log(idEmpresa);
		  $scope.listaEmpresa = c;
	  }, function(errResponse){
			console.error('Erro ao tentar buscar empresa');
			return $q.reject(errResponse);
		});
	
//busca a empresa atraves do id
	self.buscarEmpresa = function(id){
		if(!id)return;
		adminSistemaService.buscarEmpresa(id).
		then(function(p){
			self.empresa = p;
			console.log(idEmpresa);
		}, function(errResponse){
			toastr.error('Erro ao buscar empresas');
		});
	};
//verifica se o params esta com o id		
	if(idEmpresa){
		self.buscarEmpresa(idEmpresa);
	}
	
	$scope.maskFone= '(99) 9999 - 999?9';
	$scope.maskCnpj= '99.999.999/9999-99';
	$scope.maskIscEstadual= '999.999.999.999';
	$scope.maskCep = '99999-999';
});