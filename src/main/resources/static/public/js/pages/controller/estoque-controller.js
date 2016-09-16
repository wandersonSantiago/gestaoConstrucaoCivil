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
		//cria uma lista de outros
		self.adicionarProduto = function(produto, quantidadeSaida, andar, torre , apartamento, NumeroCasa){
			self.listaProduto.push({
				produto : produto,
				quantidadeSaida : quantidadeSaida,
				andar : andar,
				torre : torre,
				apartamento : apartamento,
				NumeroCasa : NumeroCasa
				
			});
			console.log(self.listaProduto);
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
		
});