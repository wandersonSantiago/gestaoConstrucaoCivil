app.controller('notaFiscalController', function($scope,notaFiscalService, $routeParams){
	
	var self = this;
		self.listaItensNota = [];
		
	
	 self.salva = function(notaFiscalProduto){
		 self.notaFiscalProduto.notaFiscal = self.notaFiscal;
		self.notaFiscalProduto.notaFiscal.valorTotal  = $scope.valorTotalNota;
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
		
		self.SomaTotal = function(){
			
			var totalSoma = 0;
			for(i =0; i < self.listaItensNota.length ; i ++){
				var total = self.listaItensNota[i];
				totalSoma += parseFloat(total.valorTotal);	
				$scope.valorTotalNota = totalSoma;
				console.log($scope.valorTotalNota);
				
			}
		
			}
		
		$scope.somaUnitario = function(quantidade, valorUnitario){
			return $scope.valorTotal = quantidade * valorUnitario;
		}
		
		
		
		self.ativarExcluirLote = function(listaItensNota){
			self.listaItensNota.filter(function(f){
			if(f.selecionado){
				$scope.ativadoExcluirLote = true;
			}
			});
		}
			

		//apagar outros empreendimentos, somente da lista de front
		self.apagarProdutos = function(listaItensNota){
			
				
				self.listaItensNota = self.listaItensNota.filter(function(f){
					$scope.somaUnitario(f.quantidade, f.valorUnitario );
					self.SomaTotal();
				if(!f.selecionado) return f;
				$scope.ativadoExcluirLote = false;
			});
		}
});