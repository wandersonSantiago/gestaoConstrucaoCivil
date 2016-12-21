app.controller('areaProdutoController', function($scope, areaProdutoService, $routeParams){
	
	var self = this;
		
	var idAreaProduto = $routeParams.idAreaProduto;
	
	
	
	 self.salva = function(areaProduto){
		 areaProdutoService.salva(self.areaProduto).
		then(function(c){
			self.areaProduto = null;
			}, function(errResponse){
		});
	}
	 
	 self.altera = function(areaProduto){
		 areaProdutoService.altera(self.areaProduto).
			then(function(c){
				self.areaProduto = null;
				}, function(errResponse){
			});
		}
	 
	 self.lista = function(){
		 areaProdutoService.lista().
			then(function(c){
				self.listaAreaProduto = c;
				}, function(errResponse){
			
			});
		};
		self.buscaPorId = function(id){
			if(!id)return;
			areaProdutoService.buscaPorId(id).
				then(function(c){
					self.areaProduto = c;
					}, function(errResponse){
				
				});
			};
			if(idAreaProduto){
				self.buscaPorId(idAreaProduto);
			}
		 
	
});