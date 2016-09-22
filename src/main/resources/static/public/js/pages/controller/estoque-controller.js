app.controller('estoqueController', function($scope,estoqueService, produtoService, $routeParams){
	
	var self = this;
	
		self.listaProduto =[];
		self.baixaEstoque = [];
	
		self.lista = function(){
			 produtoService.lista().
				then(function(t){
					self.produtos = t;
					console.log(self.produtos);
					}, function(errResponse){
				});
			};
			self.listaProdutosComEstoque = function(){
				 produtoService.listaProdutosComEstoque().
					then(function(t){
						self.listaProdutosComEstoque = t;
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
			});
		}
			
			self.verificaProdutoRepetido = function(produto){
				for(i = 0; i < self.listaItensNota.length ; i ++){
						var item = self.listaItensNota[i];
						var produto1 = item.produto.id;
					if(produto1 != produto.id){
							self.existe = true;
						}else{
							sweetAlert({ timer : 3000,  text :"ja consta este produto na tabela",  type : "info", width: 300, higth: 300, padding: 20});
							var tamanho = self.listaItensNota[i];
							i = tamanho[i + 1];
							self.existe = false;
						}
					}
				if(self.existe){
				self.salvaNotaNaLista(produto);
				}
			}
		//cria uma lista de outros
		self.adicionarProduto = function(produto, areaProduto, quantidadeSaida, andar, torre , apartamento, NumeroCasa){
			self.listaProduto.push({
				produto : produto,
				areaProduto : areaProduto,
				quantidadeSaida : quantidadeSaida,
				andar : andar,
				torre : torre,
				apartamento : apartamento,
				NumeroCasa : NumeroCasa
				
			});
						produto = "";
						$scope.produto = null;
			}
		
		self.salva = function(){
			
			self.baixaEstoque = self.listaProduto;
			estoqueService.salva(self.baixaEstoque)
			.then(function(response){
					console.log("salvou");
				}, function(errResponse){
					console.log("não salvou");
			});
			
		};
		
self.verifica = function(verifica){
			
			if($scope.torreCheck == true){
				$scope.torre = true;
			}
			if($scope.andarCheck == true){
				$scope.andar = true;
				$scope.torre = true;
			}if($scope.apartamentoCheck == true){
				$scope.andar = true;
				$scope.torre = true;
				$scope.apartamento = true;
			}
			if($scope.torreCheck == false && $scope.andarCheck == false
					&& $scope.apartamentoCheck == false){
				$scope.torre = false;
			}
			if($scope.andarCheck == false && $scope.apartamentoCheck == false){
				$scope.andar = false;
				
			}if($scope.apartamentoCheck == false){
				
				$scope.apartamento = false;
			}
		}
	
		
});