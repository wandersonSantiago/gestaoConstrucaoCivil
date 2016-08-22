app.controller('produtoController', function($scope,produtoService, $routeParams){
	
	var self = this;
		
	
	 self.cadastrarProduto = function(produto){
			 produtoService.produtoCreate(self.produto);
			 self.produto = produto;
	}
	 
	 self.buscarProduto = function(){
		 produtoService.produtoFindAll().
			then(function(t){
				self.produto = t;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar produto');
			});
		};
	
	  
	
});