app.controller('cotacaoCompraController', function($scope,cotacaoCompraService, $routeParams){
	
		var self = this;
		self.listaCotacao = [];
		self.existe = true;	
	
	 self.salva = function(cotacao){
		 self.cotacao.itens = self.listaCotacao;
		 console.log(self.cotacao);
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
				}, function(errResponse){
			});
		};
		
		
	
			
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
			
		
		}
});