app.controller('estoqueController', function($scope,estoqueService, produtoService, $routeParams){
	
	var self = this;
	
		self.listaProduto =[];
	
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
		self.adicionarProduto = function(){
			self.listaProduto.push({
				descricao : descricaoProduto.value,
				quantidade : quantidadeProduto.value
			});
						descricaoProduto.value = "";
						$scope.produto = null;
			}
		
});