app.controller('produtoController', function($scope,produtoService, $routeParams){
	
	var self = this;
	var idProduto = $routeParams.idProduto;
		self.listaFornecedores = [];
		self.listaProduto =[];
	
		self.salva = function(produto){
		 self.produto.fornecedores = self.listaFornecedores;
		 console.log(self.produto);
			 produtoService.salva(self.produto).
				then(function(response){
					self.produto = null;
					$scope.protudoCtr.fornecedor = null;
					$scope.ativaTabela = false;
					self.listaFornecedores = listaFornecedores = [];
					
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
				self.produtos = t;
				
				}, function(errResponse){
			});
		};
	
		
		
		//cria uma lista de fornecedores
		self.adicionarFornecedores = function(fornecedor){
			self.listaFornecedores.push(
				 fornecedor
			);
			$scope.protudoCtr.fornecedor = null;
			$scope.ativaTabela = true;
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
		
		
		
		/*
		
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
			}*/
		
		
		
		self.buscaPorId = function(id){
			if(!id)return;
			produtoService.buscaPorId(id).
			then(function(p){
				self.produto = p;
				console.log(self.produto);
				$scope.protudoCtr.listaFornecedores = self.produto.fornecedores;
				}, function(errResponse){
			});
		};
		
		if(idProduto){
			self.buscaPorId(idProduto);
		}
});