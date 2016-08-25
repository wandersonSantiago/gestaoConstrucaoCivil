app.controller('produtoController', function($scope,produtoService, $routeParams){
	
	var self = this;
		self.listaFornecedores = [];
	
	 self.cadastrarProduto = function(produto){
		 self.produto.fornecedor = self.listaFornecedores;
			 produtoService.produtoCreate(self.produto);
			 self.produto = produto;
	}
	 
	 self.buscarProduto = function(){
		 produtoService.produtoFindAll().
			then(function(t){
				self.produto = t;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar produto');
			});
		};
	
		//cria uma lista de fornecedores
		self.adicionarFornecedores = function(){
			self.listaFornecedores.push({
				fornecedor : self.fornecedor.dadoEmpresa
			});
			console.log(self.listaFornecedores);
			self.fornecedor = null;
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