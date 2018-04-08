app.controller('notaFiscalController', function($scope,notaFiscalService, produtoService,  $routeParams){
	
		var self = this;
		self.listaItensNota = [];
		self.existe = true;	
	
	 self.salva = function(notaFiscalProduto){
		 self.notaFiscalProduto.notaFiscal = self.notaFiscal;
		 self.notaFiscalProduto.notaFiscal.valorTotal  = $scope.valorTotalNota;
		 self.notaFiscalProduto.itens = self.listaItensNota;	
		 notaFiscalService.salva(self.notaFiscalProduto).
				then(function(response){
					self.limpaCampos();					
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
		
		self.buscaPorCodigoBarras = function(codigoBarras){
			produtoService.buscaPorCodigoBarras(codigoBarras).
			then(function(p){
				self.produtos = p;
				if(p){
					$scope.notaFiscalCtrl.produto = null;
					self.adicionarProdutos(self.produtos);						
				}		
			},function(errResponse){
			});
		};
		
		self.adicionarProdutos = function(produto){
			$scope.notaFiscalCtrl.produto = null;
		if(self.listaItensNota.length == 0){				
				self.salvaNotaNaLista(produto);				
			}
			else{
				self.verificaProdutoRepetido(produto);				
			}
		}
			
		//cria uma lista de Produtos na nota fiscal
		self.salvaNotaNaLista = function(produto){
			self.listaItensNota.push({
				produto 
			});
			produto = "";
			$scope.visualizarTable = true;
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
	
	
	
		self.SomaTotal = function(listaItensNota){
			var totalSoma = 0;
			for(i =0; i < listaItensNota.length ; i ++){
				var total = listaItensNota[i];
				totalSoma += parseFloat(total.valorTotal);	
				$scope.valorTotalNota = totalSoma;
					}
			}
		
		$scope.somaUnitario = function(quantidade, valorUnitario){
			return $scope.valorTotal = quantidade * valorUnitario;
		}
		
		
		
		self.ativarExcluirLote = function(listaItensNota){
			self.listaItensNota.filter(function(f){
			if(f.selecionado){
				$scope.ativadoExcluirLote = true; }
			});
		}
			

		//apagar outros empreendimentos, somente da lista de front
		self.apagarProdutos = function(listaItensNota){
				self.listaItensNota = self.listaItensNota.filter(function(f){
				if(!f.selecionado) return f;
				$scope.valorTotalNota -= f.valorTotal;
				$scope.ativadoExcluirLote = false;
			});
		}
		
		self.limpaCampos = function(){
			
			$scope.notaFiscalCtrl.notaFiscal = null;
			$scope.listaItensNota = self.listaItensNota = [];
			$scope.valorTotalNota = 0;
			$scope.notaFiscalCtrl.produto = "";
			$scope.notaFiscalCtrl.notaFiscalProduto.fornecedor = "";
			$scope.visualizarTable = false;
		}
});