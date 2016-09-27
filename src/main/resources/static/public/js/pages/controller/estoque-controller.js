app.controller('estoqueController', function($scope,estoqueService, produtoService, $routeParams, $timeout, $q, $log){
	
	var self = this;
	
		self.listaProduto =[];
		self.baixaEstoque = [];
		self.listaProdutos = [];
		
		self.lista = function(){
			 produtoService.lista().
				then(function(t){
					self.produtos = t;
					}, function(errResponse){
				});
			};
			self.listaProdutosComEstoque = function(){
				estoqueService.listaProdutosComEstoque().
					then(function(t){
						self.listaProdutosComEstoques = t;
						
						for(i = 0; i < self.listaProdutosComEstoques.length; i++ ){
							for(c = 0; c < self.listaProdutosComEstoques[i].produto.length ; c++){
								self.produto = self.listaProdutosComEstoques[i].produto[c];
								self.quantidade = self.listaProdutosComEstoques[i];
							self.listaProdutos.push({
										produto : self.produto,
										quantidade : self.quantidade
									
								});
							}
						
						}
							}, function(errResponse){
					});
				};
			
		self.ativarExcluirLote = function(listaProduto){
			self.listaProduto.filter(function(produto){
			if(produto.selecionado){
				$scope.ativadoExcluirLote = true;
			}
			});
		}
			
			self.apagarProduto = function(listaProduto){
				self.listaProduto = listaProduto.filter(function(produto){
				if(!produto.selecionado) return produto;
				$scope.ativadoExcluirLote = null;
				if(listaProduto.length == 0){
					$scope.ativaTabela = false;
				}
			});
		}
			
			
			self.funcaoListaProduto = function(quantidadeEstoque, produto, areaProduto, quantidadeSaida, andar, torre , apartamento, NumeroCasa){
				self.listaProduto.push({
					quantidadeEstoque : quantidadeEstoque,
					produto : produto,
					areaProduto : areaProduto,
					quantidadeSaida : quantidadeSaida,
					andar : andar,
					torre : torre,
					apartamento : apartamento,
					NumeroCasa : NumeroCasa
					
				});
			}
		
	self.verificaProdutoRepetido = function(quantidadeEstoque, produto, areaProduto, quantidadeSaida, andar, torre , apartamento, NumeroCasa){
			for(i = 0; i < self.listaProduto.length; i++ ){
				var produto2 = self.listaProduto[i].produto;
				var produto1 = produto2.id			
				if(produto1 != produto.id){
					self.existe = true;
					}else{
						sweetAlert({ timer : 3000,  text :"ja consta este produto na tabela",  type : "info", width: 300, higth: 300, padding: 20});
						self.existe = false;
						var tamanho = self.listaProduto[i];
						i = tamanho[i + 1];
					}			
			}
			if(self.existe == true){
				self.funcaoListaProduto(quantidadeEstoque, produto, areaProduto, quantidadeSaida, andar, torre , apartamento, NumeroCasa);
				}
			}
		
		self.adicionarProduto = function(quantidadeEstoque, produto, areaProduto, quantidadeSaida, andar, torre , apartamento, NumeroCasa){
			
			if(quantidadeEstoque < quantidadeSaida){
				sweetAlert({ timer : 6000,  text :"Quantidade Superior ao estoque",  type : "info", width: 300, higth: 300, padding: 20});
					
				}if(quantidadeSaida < 1){
					sweetAlert({ timer : 6000,  text :"Quantidade tem que ser maior que zero",  type : "info", width: 300, higth: 300, padding: 20});
					
				}else{
					$scope.ativaTabela = true;
					if(self.listaProduto.length == 0){				
						self.funcaoListaProduto(quantidadeEstoque, produto, areaProduto, quantidadeSaida, andar, torre , apartamento, NumeroCasa);				
					}
					else{
						self.verificaProdutoRepetido(quantidadeEstoque, produto, areaProduto, quantidadeSaida, andar, torre , apartamento, NumeroCasa);				
					}
				}
			}
			
		self.salva = function(){
			
			self.baixaEstoque = self.listaProduto;
			estoqueService.salva(self.baixaEstoque)
			.then(function(response){
					console.log("salvou");
					self.listaProdutosComEstoque();
				}, function(errResponse){
					console.log("não salvou");
			});
			
		};


});