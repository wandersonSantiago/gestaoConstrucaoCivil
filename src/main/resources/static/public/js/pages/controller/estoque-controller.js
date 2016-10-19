app.controller('estoqueController', function($scope,estoqueService, produtoService, $routeParams, $timeout, $q, $log){
	
	var self = this;
	
		self.listaProduto =[];
		self.baixaEstoque = [];
		self.listaProdutos = [];
		var idProdutoEstoque = $routeParams.idProdutoEstoque;
		self.lista = function(){
			 produtoService.lista().
				then(function(t){
					self.produtos = t;
					}, function(errResponse){
				});
			};
			
			self.buscaPorId = function(id){
				if(!id)return;
				estoqueService.buscaPorId(id).
				then(function(p){
					self.produtoEstoque = p;
				}, function(errResponse){
				});
			};
			
			self.buscaPorCodigoBarras = function(codigoBarras){
				estoqueService.buscaPorCodigoBarras(codigoBarras).
				then(function(p){
					self.listaProdutosComEstoques = p;
					for(i = 0; i < self.listaProdutosComEstoques.length; i++ ){
						
						self.produto = self.listaProdutosComEstoques[i].produto;
						self.quantidade = self.listaProdutosComEstoques[i].quantidade;
					self.listaProdutos.push({
								produto : self.produto,
								quantidade : self.quantidade
							
						});
					}
				});
			};
			self.listaProdutosComEstoque = function(){
				estoqueService.listaProdutosComEstoque().
					then(function(t){
						self.listaProdutosComEstoques = t;
						
						for(i = 0; i < self.listaProdutosComEstoques.length; i++ ){
						
								self.produto = self.listaProdutosComEstoques[i].produto;
								self.quantidade = self.listaProdutosComEstoques[i].quantidade;
							self.listaProdutos.push({
										produto : self.produto,
										quantidade : self.quantidade									
								});						
						}						
							}, function(errResponse){
					});
				};
			
	
			
		
			
			
		
		
		if(idProdutoEstoque){
			self.buscaPorId(idProdutoEstoque);
		}
		
		
});