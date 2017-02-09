app.controller('estoqueController', function($scope,estoqueService, produtoService, $routeParams, $timeout, $q, $log){
	
	var self = this;
	
		self.listaProduto =[];
		self.baixaEstoque = [];
		self.listaProdutos = [];
		self.getPage=0;
		self.totalPages = 0;
		self.totalElements = 0;
		$scope.maxResults = 15;
		$scope.verificado = false;
		var idProdutoEstoque = $routeParams.idProdutoEstoque;
		
		self.editarProdutoEstoque = function(produtoEstoque){
			verificaCampo();
			if(!$scope.verificado){
				estoqueService.editarProdutoEstoque(self.produtoEstoque)
			}
			$scope.verificado = false;
		};
		
		verificaCampo = function(produtoEstoque){
			if(self.produtoEstoque.quantidadeMinima == 0 || self.produtoEstoque.quantidadeMaxima == 0 ){
				$scope.verificado = true;
				sweetAlert({ timer : 10000, text :"Quantidade tem que ser maior que zero!", type : "info", width: 300, higth: 100, padding: 20});
			}else if(self.produtoEstoque.quantidadeMinima >= self.produtoEstoque.quantidadeMaxima){
				sweetAlert({ timer : 10000, text :"Quantidade miníma tem que ser menor que quantidade máxima!", type : "info", width: 300, higth: 100, padding: 20});
				$scope.verificado = true;
			}
		}
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
				console.log("tstando");
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
			
				self.listaProdutosComEstoqueComPaginacao = function(pages, maxResults){
					self.totalPages = [];
					self.getPage=pages;	
					estoqueService.listaProdutosComEstoqueComPaginacao(pages, maxResults).
						then(function(t){
							self.listaProdutosComEstoques = t.content;
							$scope.totalPages = t.totalPages;
							self.totalElements = t.totalElements;
							for(i = 0; i < $scope.totalPages ; i++){
								self.totalPages.push(i);
							}						
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
		
		$scope.buscar = $scope.buscarPorDescricao;
		
		self.filtro = function(busca){
			if(busca.descricao){
				$scope.buscar = busca.descricao;
			}else{
				$scope.buscar = busca;
			}
			
		}
		
});