app.controller('tipoEmpreendimentoController', function($scope, tipoEmpreendimentoService, $routeParams){
	
	var self = this;
  
	$scope.listarTipoEmpreendimento = [];
		
	self.createTipoEmpreendimento = function(tipoEmpreendimento){
		console.log("funfou o controle");
		console.log(self.tipoEmpreendimento);
		tipoEmpreendimentoService.tipoEmpreendimentoCreate(self.tipoEmpreendimento);
	
		self.tipoEmpreendimento = tipoEmpreendimento;
		
	}
	

	tipoEmpreendimentoService.tipoEmpreendimentoFindAll().
	  then(function(t){
		  $scope.listarTipoEmpreendimento = t;
	  }, function(errResponse){
			console.error('Erro ao tentar buscar tipo');
			return $q.reject(errResponse);
		});
	
	
	
});