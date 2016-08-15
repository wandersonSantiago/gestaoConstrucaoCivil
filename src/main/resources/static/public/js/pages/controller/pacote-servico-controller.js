app.controller('pacoteServicoController', function($scope, pacoteServicoService, $routeParams){
	
	var self = this;
		
	
	
	
	 self.cadastrarPacoteServico = function(pacoteServico){
	
		 pacoteServicoService.pacoteServicoCreate(self.pacoteServico);
		 
		 self.pacoteServico = pacoteServico = [];
	}
	 
	 self.buscarPacoteServicos = function(){
		 pacoteServicoService.pacoteServicoFindAll().
			then(function(f){
				$scope.listaPacoteServicos = f;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar os pacotes de serviço');
			});
		};
	
	
	  
});