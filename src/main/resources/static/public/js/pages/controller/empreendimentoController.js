app.controller('empreendimentoController', function($scope, tipoEmpreendimentoService, $routeParams){
	
	var self = this;
  
	$scope.listarTipoEmpreendimento = [];
		
	self.createEmpreendimento = function(empreendimento){
		console.log("funfou o controle");
		console.log(self.empreendimento);
		empreendimentoService.empreendimentoCreate(self.empreendimento);
	
		self.empreendimento = empreendimento;
		
	}
	

	tipoEmpreendimentoService.tipoEmpreendimentoFindAll().
	  then(function(t){
		  $scope.listarTipoEmpreendimento = t;
	  }, function(errResponse){
			console.error('Erro ao tentar buscar tipo');
			return $q.reject(errResponse);
		});
	
	$scope.maskFone = '(99) 9999 - 999?9';
	$scope.maskCnpj = '99.999.999/9999-99';
	$scope.maskIscEstadual = '999.999.999.999';
	$scope.maskCep = '99999-999';
	$scope.maskData = '9999-99-99';
});