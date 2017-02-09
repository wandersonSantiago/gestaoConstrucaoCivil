app.controller('pacoteServicoController', function($scope, pacoteServicoService, $location,  $routeParams){
	
	var self = this;
	self.getPage=0;
	self.totalPages = 0;
	self.totalElements = 0;
	$scope.maxResults = 15;	
	var idPacoteServico = $routeParams.idPacoteServico;
	
	
	
	 self.salva = function(pacoteServico){
		 pacoteServicoService.salva(self.pacoteServico).
			then(function(response){
				self.pacoteServico = null;
				}, function(errResponse){
			});
		 
	}
	 
	 self.altera = function(pacoteServico){
		 pacoteServicoService.altera(self.pacoteServico).
			then(function(response){
				self.pacoteServico = null;
				$location.path('/servicos/pacotes/lista');
				}, function(errResponse){
			});
		 
	}	 
	 
		self.buscaTodosComPaginacao = function(pages, maxResults){
			self.totalPages = [];
			self.getPage=pages;			
			pacoteServicoService.buscaTodosComPaginacao(pages, maxResults).
			then(function(e){			
				self.listaPacoteServicos = e.content;
				$scope.totalPages = e.totalPages;
				self.totalElements = e.totalElements;
				for(i = 0; i < $scope.totalPages ; i++){
					self.totalPages.push(i);
				}
			}, function(errResponse){
			});
		};
		
		self.lista = function(){					
			pacoteServicoService.lista().
			then(function(e){			
				self.listaPacoteServicos = e;				
			}, function(errResponse){
			});
		};
		self.buscaPorId = function(id){
			if(!id)return;
			pacoteServicoService.buscaPorId(id).
			then(function(p){
				self.pacoteServico = p;
				}, function(errResponse){
			});
		};
		
	
		if(idPacoteServico){
			
			self.buscaPorId(idPacoteServico);
		}
		
		
	
});