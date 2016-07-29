app.controller('empreendimentoController', function($scope, empreendimentoService,  $routeParams){
	
	var self = this;
  
	
		
	self.createEmpreendimento = function(empreendimento){
		empreendimentoService.empreendimentoCreate(self.empreendimento);
		self.empreendimento = empreendimento;
		
	}
	

	 self.buscarEmpreendimentos = function(){
		 empreendimentoService.empreendimentoFindAll().
			then(function(t){
				$scope.listarEmpreendimento = t;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar empreendimento');
			});
		};
		
				
	$scope.maskFone = '(99) 9999 - 999?9';
	$scope.maskCnpj = '99.999.999/9999-99';
	$scope.maskIscEstadual = '999.999.999.999';
	$scope.maskCep = '99999-999';
	$scope.maskData = '9999-99-99';
	
});