app.controller('adminSistemaController', function($scope, adminSistemaService, $routeParams){
	
	var self = this;
  
	$scope.listaEmpresa = [];
		
	self.createEmpresa = function(empresa, sucesso){
		console.log("funfou o controle");
		console.log(self.empresa);
		adminSistemaService.empresaCreate(self.empresa);
	
		self.empresa = empresa;
		
	}
	
	
	adminSistemaService.empresaFindAll().
	  then(function(c){
		  $scope.listaEmpresa = c;
	  }, function(errResponse){
			console.error('Erro ao tentar buscar empresa');
			return $q.reject(errResponse);
		});
	
	
	$scope.maskFone= '(99) 9999 - 999?9';
	$scope.maskCnpj= '99.999.999/9999-99';
	$scope.maskIscEstadual= '999.999.999.999';
	$scope.maskCep = '99999-999';
	
	
	
});