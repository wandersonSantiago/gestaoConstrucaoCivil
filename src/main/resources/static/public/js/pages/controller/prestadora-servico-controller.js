app.controller('PrestadoraServicoController', function($scope,PrestadoraServicoService, $routeParams){
	
	var self = this;
		
	$scope.listaPrestadoraServico = [];
	
	
	 self.cadastrarPrestadoraServico = function(prestadoraServico){
		 console.log(self.prestadoraServico);
		 prestadoraServicoService.prestadoraServicoCreate(self.prestadoraServico);
	}
	 
	 self.buscarPrestadoraServico = function(){
		 prestadoraServicoService.prestadoraServicoFindAll().
			then(function(t){
				self.listaPrestadoraServico = t;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar prestadora de servico');
			});
		};
	 
		 
	
});