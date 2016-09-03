app.controller('notaFiscalController', function($scope,notaFiscalService, $routeParams){
	
	var self = this;
		self.listaFornecedores = [];
	
	 self.salva = function(produto){
		 self.produto.fornecedores = self.listaFornecedores;
			 produtoService.salva(self.produto).
				then(function(response){
					self.produto = null;
					self.listaFornecedores = null;
					}, function(errResponse){
				});
	 }
	 self.altera = function(produto){
		 self.produto.fornecedores = self.listaFornecedores;
			 produtoService.altera(self.produto).
				then(function(response){
					self.produto = null;
					self.listaFornecedores = null;
					}, function(errResponse){
				});
	 }
	 
	 self.lista = function(){
		 produtoService.lista().
			then(function(t){
				self.produto = t;
				}, function(errResponse){
			});
		};
		
		
		//cria uma lista de fornecedores
		self.adicionarFornecedores = function(){
			self.listaFornecedores.push({
				fornecedor : self.fornecedor
			});
			console.log(self.listaFornecedores);
			self.fornecedores = null;
		}
		
		self.ativarExcluirLote = function(listaFornecedores){
			console.log("ativado");
			self.listaFornecedores.filter(function(fornecedores){
			if(fornecedores.selecionado){
				$scope.ativadoExcluirLote = true;
			}
			});
		}
			

		//apagar outros empreendimentos, somente da lista de front
		self.apagarFornecedores = function(listaFornecedores){
			console.log("apagar");
				self.listaFornecedores = listaFornecedores.filter(function(fornecedores){
				if(!fornecedores.selecionado) return fornecedores;
				$scope.ativadoExcluirLote = null;
			});
		}
});