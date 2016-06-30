app.controller('adminSistemaController', function($scope, adminSistemaService, $routeParams){
	
	var self = this;
	
	self.empresa = {razaoSocial: "", nomeFantasia: "", cnpj: "", inscricaoEstadual:"", endereco:"", telefone:"", email:"" }

	
	
	
	self.createEmpresa = function(empresa){
		console.log("funfou o controle");
		adminSistemaService.empresaCreate(self.empresa);
	}
	
});