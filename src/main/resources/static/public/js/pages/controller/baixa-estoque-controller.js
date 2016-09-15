app.controller('baixaEstoqueController', function($scope,baixaEstoqueService, $routeParams){
	
	var self = this;
	
		self.listaProduto =[];
	
		
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