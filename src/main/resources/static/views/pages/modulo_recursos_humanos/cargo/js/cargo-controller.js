app.controller('cargoController', function($scope, cargoService, $routeParams){
	
	var self = this;
	self.getPage=0;
	self.totalPages = 0;
	self.totalElements = 0;
	$scope.maxResults = 15;	
	var idCargo = $routeParams.idCargo;
	
	
	
	 self.salva = function(cargo){
		cargoService.salva(self.cargo).
		then(function(c){
			self.cargo = null;
			}, function(errResponse){
		});
	}
	 
	 self.altera = function(cargo){
			cargoService.altera(self.cargo).
			then(function(c){
				self.cargo = null;
				}, function(errResponse){
			});
		}
	 
	 self.lista = function(){
		 cargoService.lista().
			then(function(c){
				$scope.cargos = c;
				}, function(errResponse){
			
			});
		};
		
		self.listaComPaginacao = function(pages, maxResults){
			self.totalPages = [];
			self.getPage=pages;	
			 cargoService.listaComPaginacao(pages, maxResults).
				then(function(c){
					$scope.cargos = c.content;
					$scope.totalPages = c.totalPages;
					self.totalElements = c.totalElements;
					for(i = 0; i < $scope.totalPages ; i++){
						self.totalPages.push(i);
					}
					}, function(errResponse){
				
				});
			};
			
		self.buscaPorId = function(id){
			if(!id)return;
			 cargoService.buscaPorId(id).
				then(function(c){
					self.cargo = c;
					}, function(errResponse){
				
				});
			};
			if(idCargo){
				self.buscaPorId(idCargo);
			}
		 
	
});