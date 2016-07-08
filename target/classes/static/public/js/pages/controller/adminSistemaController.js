app.controller('adminSistemaController', function($scope, adminSistemaService, $routeParams){
	
	var self = this;
  
	
		
	self.createEmpresa = function(empresa, sucesso){
		console.log("funfou o controle");
		console.log(self.empresa);
		adminSistemaService.empresaCreate(self.empresa);
	
		self.empresa = empresa;
		
	}
	
	
	
	
	
	$scope.maskFone= '(99) 9999 - 999?9';
	$scope.maskCnpj= '99.999.999/9999-99';
	$scope.maskIscEstadual= '999.999.999.999';
	$scope.maskCep = '99999-999';
	
	
	
});