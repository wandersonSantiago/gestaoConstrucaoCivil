app.controller('pacoteServicoController', function($scope,pacoteServicoService, $routeParams){
	
	var self = this;
		
	$scope.listaPacoteServico = [];
	
	
	 self.cadastrarPacoteServico = function(pacoteServico){
		 console.log(self.pacoteServico);
		 pacoteServicoService.pacoteServicoCreate(self.pacoteServico);
	}
	 
	 self.buscarPacoteServicos = function(){
		 pacoteServicoService.pacoteServicoFindAll().
			then(function(f){
				self.listaPacoteServicos = f;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar os pacotes de serviço');
			});
		};
	
	
	  
});