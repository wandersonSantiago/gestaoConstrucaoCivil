app.controller('servicoEmpresaController', function($scope,servicoEmpresaService, $routeParams){
	
	var self = this;
		
	
	
	 self.salva = function(servicoEmpresa){
		 servicoEmpresaService.salva(self.servicoEmpresa).
			then(function(response){
				self.servicoEmpresa = null;
				}, function(errResponse){
			});
		}
	 
	 self.altera = function(servicoEmpresa){
		 servicoEmpresaService.altera(self.servicoEmpresa).
			then(function(response){
				self.servicoEmpresa = null;
				}, function(errResponse){
			});
		}
	 
	 self.lista = function(){
		 servicoEmpresaService.lista().
			then(function(t){
				self.listaServicoEmpresa = t;
				}, function(errResponse){
			});
		};
	
		
});