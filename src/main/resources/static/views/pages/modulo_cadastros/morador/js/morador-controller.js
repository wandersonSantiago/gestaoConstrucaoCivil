app.controller('moradorController', function($scope, $rootScope,   moradorService, $routeParams) {

	var self = this;
	self.getPage=0;
	self.totalPages = 0;
	self.totalElements = 0;
	$scope.maxResults = 15;	
	var idClienteMorador = $routeParams.idClienteMorador;
			
	self.salvar = function() {
		if($rootScope.tipoEmpreendimento){
			var clienteMoradorEdificio = self.clienteMorador;
			moradorService.salvarEdificio(clienteMoradorEdificio).
			then(function(response){
				self.clienteMorador = null;
				});
		}else if(!$rootScope.tipoEmpreendimento){
			var clienteMoradorCasa = self.clienteMorador;
			moradorService.salvarCasa(clienteMoradorCasa).
			then(function(response){
				self.clienteMorador = null;
				});	
		}
	}
	
	self.alterar = function() {
		if($rootScope.tipoEmpreendimento){
			var clienteMoradorEdificio = self.clienteMorador;
			moradorService.alterarEdificio(clienteMoradorEdificio).
			then(function(response){
				self.clienteMorador = null;
				});
		}else if(!$rootScope.tipoEmpreendimento){
			var clienteMoradorCasa = self.clienteMorador;
			moradorService.alterarCasa(clienteMoradorCasa).
			then(function(response){
				self.clienteMorador = null;
				});	
		}
	}

	 self.listar = function(){
		 moradorService.listar().
			then(function(f){
				self.listaClienteMorador = f;				
				}, function(errResponse){
			});
		};
		
		 self.listaComPaginacao = function(pages, maxResults){
				self.totalPages = [];
				self.getPage=pages;	
				moradorService.listaComPaginacao(pages, maxResults).
				then(function(t){
					self.listaClienteMorador = t.content;
					$scope.totalPages = t.totalPages;
					self.totalElements = t.totalElements;
					for(i = 0; i < $scope.totalPages ; i++){
						self.totalPages.push(i);
					}			
					}, function(errResponse){
				});
			};
		
				
			
		
		self.buscarPorId = function(id){
			if(!id)return;
			moradorService.buscarPorId(id).
			then(function(p){
				self.clienteMorador = p;
				self.clienteMorador.dataNascimento = new Date(p.dataNascimento);
		}, function(errResponse){
			});
		};
		if(idClienteMorador){
			self.buscarPorId(idClienteMorador);
			
		}
		
		
});