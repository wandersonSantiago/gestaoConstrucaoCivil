app.controller('notaFiscalController', function($scope,notaFiscalService, $routeParams){
	
	var self = this;
		self.listaItensNota = [];
		
	
	 self.salva = function(notaFiscalProduto){
		 self.notaFiscalProduto.notaFiscal = self.notaFiscal;
		 self.notaFiscalProduto.notaFiscal.valorTotal  = self.valorTotal;
		 self.notaFiscalProduto.itens = self.listaItensNota;	
		 notaFiscalService.salva(self.notaFiscalProduto).
				then(function(response){
					console.log(self.notaFiscalProduto);
					self.notaFiscalProduto = null;
					self.listaItensNota = null;
					}, function(errResponse){
				});
	 }
	 self.altera = function(notaFiscalProduto){
		 self.notaFiscal.itemNotaFiscal = self.listaItensNota;
		 notaFiscalService.altera(self.notaFiscalProduto).
				then(function(response){
					self.notaFiscalProduto = null;
					self.listaItensNota = null;
					}, function(errResponse){
				});
	 }
	 
	 self.lista = function(){
		 notaFiscalService.lista().
			then(function(t){
				self.notaFiscalProduto = t;
				}, function(errResponse){
			});
		};
		
		
		//cria uma lista de Produtos
		self.adicionarProdutos = function(produto){
			self.listaItensNota.push({
				produto 
			});
			
	}
		
		
		$scope.somaUnitario = function(quantidade, valorUnitario){
			return $scope.valorTotal = quantidade * valorUnitario;
		
			
		}
		
		self.ativarExcluirLote = function(listaItensNota){
			console.log("ativado");
			self.listaItensNota.filter(function(produto){
			if(produto.selecionado){
				$scope.ativadoExcluirLote = true;
			}
			});
		}
			

		//apagar outros empreendimentos, somente da lista de front
		self.apagarProdutos = function(listaItensNota){
			console.log("apagar");
				self.listaItensNota = listaFornecedores.filter(function(produto){
				if(!produto.selecionado) return produto;
				$scope.ativadoExcluirLote = null;
			});
		}
});