app.controller('adminSistemaController', function($scope, adminSistemaService, $routeParams){
	
	var self = this;
	
	//self.dadoEmpresa = {razaoSocial: "", nomeFantasia: "", cnpj: "", inscricaoEstadual:"", endereco:"", telefone:"", email:"",complemento:"" }
    
	
	self.createEmpresa = function(empresa){
		console.log("funfou o controle");
		console.log(self.empresa);
		adminSistemaService.empresaCreate(self.empresa);
	}
	
});