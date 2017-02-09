app.controller('prestadoraServicoController', function($scope, buscaCepService, prestadoraServicoService, $routeParams){
	
	var self = this;
	self.getPage=0;
	self.totalPages = 0;
	self.totalElements = 0;
	$scope.maxResults = 15;		
	var idPrestadoraServico = $routeParams.idPrestadoraServico;
	
	

	

//BUSCA CEP
	self.findCep = function () {
		
		self.cep = $scope.prestadoraCtrl.prestadoraServico.dadoEmpresa.endereco.cep;
		buscaCepService.get({'cep': self.cep}).$promise
		.then(function success(result){
			$scope.prestadoraCtrl.prestadoraServico.dadoEmpresa.endereco = result;
			}).catch(function error(msg) {
		});
		
    }
	
	
	
//CADASTRAR
	 self.salva = function(prestadoraServico){
		 prestadoraServicoService.salva(self.prestadoraServico).
			then(function(response){
				self.prestadoraServico = null;
				}, function(errResponse){
			});
		 
	}
	 
	
//ALTERAR	 
	 	self.altera = function(prestadoraServico){
			prestadoraServicoService.altera(self.prestadoraServico).
			then(function(response){
				self.prestadoraServico = null;
				}, function(errResponse){
			});
		}
	 	

//BUSCAR		 
			self.buscaTodosComPaginacao = function(pages, maxResults){
				self.totalPages = [];
				self.getPage=pages;			
				prestadoraServicoService.buscaTodosComPaginacao(pages, maxResults).
				then(function(e){			
					self.prestadoraServicos = e.content;
					$scope.totalPages = e.totalPages;
					self.totalElements = e.totalElements;
					for(i = 0; i < $scope.totalPages ; i++){
						self.totalPages.push(i);
					}
				}, function(errResponse){
				});
			};
			
			self.lista = function(){	
				prestadoraServicoService.lista().
				then(function(e){			
					self.prestadoraServicos = e;					
				}, function(errResponse){
				});
			};
			
			self.buscaPorId = function(id){
				if(!id)return;
				prestadoraServicoService.buscaPorId(id).
					then(function(p){
						self.prestadoraServico = p;
							}, function(errResponse){
					});
				};
				
				if(idPrestadoraServico){
					
					self.buscaPorId(idPrestadoraServico);
				}
			 
	
});