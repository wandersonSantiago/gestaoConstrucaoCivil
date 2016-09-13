app.controller('verificaDadosController', function($scope, toastr, verificaDadosService, $http, $routeParams){
	
	var self = this;
		
			
		self.verificaCnpj = function(cnpj){
			console.log(cnpj);
			verificaDadosService.verificaCnpj(cnpj).
			then(function(p){
				self.verificado = p;
				console.log(self.verificado);
				}, function(errResponse){
			});
		};
		self.verificaIe = function(ie){
			verificaDadosService.verificaIe(ie).
			then(function(p){
				self.usuario = p;
				}, function(errResponse){
			});
		};
	
	
});