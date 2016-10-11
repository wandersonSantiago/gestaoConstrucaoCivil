app.controller('verificaDadosController', function($scope, $rootScope, toastr, verificaDadosService, $http, $routeParams){
	
	var self = this;
		
	
			
		self.verificaCnpj = function(cnpj){
			console.log(cnpj);
			verificaDadosService.verificaCnpj(cnpj).
			then(function(v){
				self.verificado = v;				
				if(self.verificado){
					sweetAlert({ timer : 105000,  text :"Já existe cadastro deste CNPJ!!!",  type : "info", width: 300, higth: 300, padding: 20});
					self.limpaCampo();
				}
				
				}, function(errResponse){
			});
		};
		self.verificaIe = function(ie){
			verificaDadosService.verificaIe(ie).
			then(function(v){
				self.verificado = v;				
				if(self.verificado){
					sweetAlert({ timer : 105000,  text :"Já existe Cadastro desta Inscrição Estadual",  type : "info", width: 300, higth: 300, padding: 20});
					self.limpaCampo();
					
				}
				}, function(errResponse){
			});
		};
		
		
		self.verificaCpf = function(cpf){
			verificaDadosService.verificaCpf(cpf).
			then(function(v){
				self.verificado = v;				
				if(self.verificado){
					sweetAlert({ timer : 105000,  text :"CPF ja consta cadastrado em nosso banco de dados",  type : "info", width: 300, higth: 300, padding: 20});
					self.limpaPessoa();
				}
				
				}, function(errResponse){
			});
		};
		
		self.verificaRg = function(rg){
			verificaDadosService.verificaRg(rg).
			then(function(v){
				self.verificado = v;				
				if(self.verificado){
					sweetAlert({ timer : 105000,  text :"RG ja consta cadastrado em nosso banco de dados",  type : "info", width: 300, higth: 300, padding: 20});
					self.limpaPessoa();
				}
				
				}, function(errResponse){
			});
		};
	
		self.limpaCampo = function(){
			try{
				$scope.adminCtrl.empresa = null;
			}catch(exception){
			}			
			try{
				$scope.prestadoraCtrl.prestadoraServico = null;
			}catch(exception){
			}
			try{
				$scope.fabricanteCtrl.fabricante = null;
			}catch(exception){
			}
			try{
				$scope.fornecedorCtrl.fornecedor = null;
			}catch(exception){
			}
		
			
		}
		self.limpaPessoa = function(){
			try{
				$scope.cadFuncCtrl.funcionario.cpf = null;
				
			}catch(exception){}
			try{
				$scope.cadFuncCtrl.funcionario.rg = null;
			}catch(exception){}
		}

});