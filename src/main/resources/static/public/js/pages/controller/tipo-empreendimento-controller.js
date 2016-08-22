app.controller('tipoEmpreendimentoController', function($scope, tipoEmpreendimentoService, $routeParams){
	
	var self = this;
  
	
		
	self.createTipoEmpreendimento = function(tipoEmpreendimento){
		tipoEmpreendimentoService.tipoEmpreendimentoCreate(self.tipoEmpreendimento);
		self.tipoEmpreendimento = tipoEmpreendimento;
		
	}
	

	 self.buscarTipoEmpreendimentos = function(){
		 tipoEmpreendimentoService.tipoEmpreendimentoFindAll().
			then(function(t){
				$scope.listarTipoEmpreendimento = t;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar tipo empreendimento');
			});
		};
	
		
});