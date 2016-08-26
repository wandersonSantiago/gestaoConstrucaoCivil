app.controller('tipoEmpreendimentoController', function($scope, tipoEmpreendimentoService, $routeParams){
	
	var self = this;
  
	
	
	 self.lista = function(){
		 tipoEmpreendimentoService.lista().
			then(function(t){
				$scope.listarTipoEmpreendimento = t;
				}, function(errResponse){
			});
		};
	
		
});