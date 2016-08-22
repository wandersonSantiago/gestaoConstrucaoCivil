app.controller('pacoteServicoController', function($scope, pacoteServicoService, $routeParams){
	
	var self = this;
	
	var idPacoteServico = $routeParams.idPacoteServico;
	
	
	
	 self.cadastrarPacoteServico = function(pacoteServico){
		 pacoteServicoService.pacoteServicoCreate(self.pacoteServico);
		 self.pacoteServico = pacoteServico;
	}
	 
	 self.buscarPacoteServicos = function(){
		 pacoteServicoService.pacoteServicoFindAll().
			then(function(f){
				$scope.listaPacoteServicos = f;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar os pacotes de serviço');
			});
		};
		
		self.buscarPacoteServicoPorId = function(id){
			if(!id)return;
			pacoteServicoService.pacoteServicoFindOne(id).
			then(function(p){
				self.pacoteServico = p;
				}, function(errResponse){
				toastr.error('Erro ao buscar pacote de serviço');
			});
		};
		
	
		if(idPacoteServico){
			
			self.buscarPacoteServicoPorId(idPacoteServico);
		}
		
		
	
});