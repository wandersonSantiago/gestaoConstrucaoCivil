app.controller('cotacaoCompraController', function($scope,cotacaoCompraService, $routeParams){
	
		var self = this;
		self.listaCotacao = [];
		self.existe = true;	
	
	 self.salva = function(cotacaoCompra){
		 self.cotacao = self.listaCotacao;	
		 cotacaoCompraService.salva(self.cotacao).
				then(function(response){
					self.limpaCampos();					
					}, function(errResponse){
				});
	 }
	 self.altera = function(cotacaoCompra){
		 self.cotacao.itens = self.listaCotacao;
		 cotacaoCompraService.altera(self.cotacao).
				then(function(response){
					self.limpaCampos();	
					}, function(errResponse){
				});
	 }
	 
	 self.lista = function(){
		 cotacaoCompraService.lista().
			then(function(t){
				self.cotacoes = t;
				}, function(errResponse){
			});
		};
		
		
		
	/*	self.adicionarProdutos = function(produto){
		if(self.listaCotacao.length == 0){				
				self.salvaNotaNaLista(produto);				
			}
			else{
				self.verificaProdutoRepetido(produto);				
			}
		}*/
			
		//cria uma lista de Produtos na nota fiscal
		self.adicionarProdutos = function(produto, quantidade){
			self.listaCotacao.push({
				produto :  produto,
				quantidade : quantidade
			});
			console.log(produto);
			$scope.cotacao = null;
			$scope.visialuzarTable = true;
		}
			
	
	/*self.verificaProdutoRepetido = function(produto){
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
	}*/
	
	
	
		/*self.SomaTotal = function(listaCotacao){
			var totalSoma = 0;
			for(i =0; i < listaCotacao.length ; i ++){
				var total = listaCotacao[i];
				totalSoma += parseFloat(total.valorTotal);	
				$scope.valorTotalCotacao = totalSoma;
					}
			}
		
		$scope.somaUnitario = function(quantidade, valorUnitario){
			return $scope.valorTotal = quantidade * valorUnitario;
		}*/
		
		
		
		self.ativarExcluirLote = function(listaCotacao){
			self.listaCotacao.filter(function(f){
			if(f.selecionado){
				$scope.ativadoExcluirLote = true; }
			});
		}
			

		//apagar outros empreendimentos, somente da lista de front
		self.apagarProdutos = function(listaCotacao){
				self.listaCotacao = self.listaCotacao.filter(function(f){
				if(!f.selecionado) return f;
				$scope.valorTotalCotacao -= f.valorTotal;
				$scope.ativadoExcluirLote = false;
			});
		}
		
		self.limpaCampos = function(){
			
			/*$scope.notaFiscalCtrl.notaFiscal = null;
			$scope.listaItensNota = self.listaItensNota = [];
			$scope.valorTotalNota = 0;
			$scope.notaFiscalCtrl.produto = "";
			$scope.notaFiscalCtrl.notaFiscalProduto.fornecedor = "";
			$scope.visialuzarTable = false;*/
		}
});