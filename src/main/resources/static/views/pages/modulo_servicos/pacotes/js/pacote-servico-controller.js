app.controller('pacoteServicoController', function($scope, pacoteServicoService, $location,  $routeParams){
	
	var self = this;
	
	var idPacoteServico = $routeParams.idPacoteServico;
	
	
	
	 self.salva = function(pacoteServico){
		 pacoteServicoService.salva(self.pacoteServico).
			then(function(response){
				self.pacoteServico = null;
				}, function(errResponse){
			});
		 
	}
	 
	 self.altera = function(pacoteServico){
		 pacoteServicoService.altera(self.pacoteServico).
			then(function(response){
				self.pacoteServico = null;
				$location.path('/servicos/pacotes/lista');
				}, function(errResponse){
			});
		 
	}
	 
	 self.lista = function(){
		 pacoteServicoService.lista().
			then(function(f){
				self.listaPacoteServicos = f;
				}, function(errResponse){
			});
		};
		
		self.buscaPorId = function(id){
			if(!id)return;
			pacoteServicoService.buscaPorId(id).
			then(function(p){
				self.pacoteServico = p;
				}, function(errResponse){
			});
		};
		
	
		if(idPacoteServico){
			
			self.buscaPorId(idPacoteServico);
		}
		
		
	
});