app.controller('servicoEmpresaController', function($scope,servicoEmpresaService, $routeParams){
	
	var self = this;
		
	
	
	 self.cadastrarServicoEmpresa = function(servicoEmpresa){
		 console.log(self.servicoEmpresa);
		 servicoEmpresaService.servicoEmpresaCreate(self.servicoEmpresa);
	}
	 
	 self.buscarServicoEmpresas = function(){
		 servicoEmpresaService.servicoEmpresaFindAll().
			then(function(t){
				self.listaServicoEmpresa = t;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar os servicos das empresas');
			});
		};
	
		
});