app.controller('cotacaoCompraController', function($scope,cotacaoCompraService, $routeParams){
	
		var self = this;
		self.listaCotacao = [];
		self.existe = true;	
		self.cotacaoEmpresa = [];
		var idCotacaoAberta = $routeParams.idCotacaoAberta;
	
		self.salvaCotacaoEmpresa = function(){
			self.cotacaoEmpresa.itens = self.listaCotacao;
			self.cotacaoEmpresa.cotacao = self.cotacao;
			self.cotacaoEmpresa.fornecedor = self.cotacaoEmpresa.fornecedor;
			console.log(self.cotacaoEmpresa);
			cotacaoCompraService.salvaCotacaoEmpresa(self.cotacao).
			then(function(response){
				self.limpaCampos();					
				}, function(errResponse){
			});
		}
	 self.salva = function(cotacao){
		 self.cotacao.itens = self.listaCotacao;
		 cotacaoCompraService.salva(self.cotacao).
				then(function(response){
					self.limpaCampos();					
					}, function(errResponse){
				});
	 }
	 self.altera = function(cotacao){
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
				console.log(self.cotacoes);
				}, function(errResponse){
			});
		};
		
		
	
			
		//cria uma lista de Produtos na nota fiscal
		self.adicionarProdutos = function(descricao, quantidade){
			self.listaCotacao.push({
				descricao :  descricao,
				quantidade : quantidade
			});
			$scope.cotacao = null;
			$scope.visialuzarTable = true;
			$scope.cotacaoCtrl.descricao = null;
			$scope.cotacaoCtrl.quantidade = null;
		}
			
	
		self.SomaTotal = function(listaCotacao){
			var totalSoma = 0;
			for(i =0; i < listaCotacao.length ; i ++){
				var total = listaCotacao[i];
				totalSoma += parseFloat(total.valorTotal);	
				$scope.valorTotalCotacao = totalSoma;
					}
			}
		
		$scope.somaUnitario = function(quantidade, valorUnitario){
			return $scope.valorTotal = quantidade * valorUnitario;
		}
		
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
		
		self.buscaPorCotacaoId = function(id){
			if(!id)return;
			cotacaoCompraService.buscaPorCotacaoId(id).
			then(function(p){
				self.cotacao = p;				
				self.listaCotacao = self.cotacao.itens;
			}, function(errResponse){
		});
		};

	
		if(idCotacaoAberta){
			self.buscaPorCotacaoId(idCotacaoAberta);
		}
		
		self.limpaCampos = function(){
			
			$scope.cotacaoCtrl = null;
			self.listaCotacao = self.listaCotacao;
			$scope.visialuzarTable = false;
		}
});