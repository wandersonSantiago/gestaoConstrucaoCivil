app.controller('adminSistemaController', function($scope, adminSistemaService, $routeParams){
	
	var self = this;
  
	
	self.createEmpresa = function(empresa){
		console.log("funfou o controle");
		console.log(self.empresa);
		adminSistemaService.empresaCreate(self.empresa);
		
		self.empresa = empresa;
	}
	
});