app.controller('verificaDadosController', function($scope, toastr, verificaDadosService, $http, $routeParams){
	
	var self = this;
		
			
		self.verificaCnpj = function(cnpj){
			console.log(cnpj);
			verificaDadosService.verificaCnpj(cnpj).
			then(function(p){
				self.verificado = p;				
				if(self.verificado){
					sweetAlert({ timer : 105000,  text :"CNPJ ja consta cadastrado em nosso banco de dados",  type : "info", width: 300, higth: 300, padding: 20});
					self.limpaCampo();
				}
				
				}, function(errResponse){
			});
		};
		self.verificaIe = function(ie){
			verificaDadosService.verificaIe(ie).
			then(function(p){
				self.verificado = p;				
				if(self.verificado){
					sweetAlert({ timer : 105000,  text :"Inscrição Estadual ja consta cadastrado em nosso banco de dados",  type : "info", width: 300, higth: 300, padding: 20});
					self.limpaCampo();
					
				}
				}, function(errResponse){
			});
		};
	
		self.limpaCampo = function(){
			$scope.adminCtrl.empresa.dadoEmpresa.cnpj = null;
			$scope.adminCtrl.empresa.dadoEmpresa.inscricaoEstadual = null;
		}
	
});