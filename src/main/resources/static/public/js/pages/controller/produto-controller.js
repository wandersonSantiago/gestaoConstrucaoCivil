app.controller('produtoController', function($scope,produtoService, $routeParams){
	
	var self = this;
		$scope.listaFornecedores = [];
	
	 self.cadastrarProduto = function(produto){
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
			$scope.listaFornecedores.push({
				razaoSocial : self.fornecedor.dadoEmpresa.razaoSocial ,
				nomeFantasia : self.fornecedor.dadoEmpresa.nomeFantasia ,
				cnpj : self.fornecedor.dadoEmpresa.cnpj
			});
			console.log($scope.listaFornecedores);
						fornecedor.value = "";
						$scope.fornecedor = null;
		}
		
		self.ativarExcluirLote = function(listaFornecedores){
			$scope.listaFornecedores.filter(function(fornecedor){
			if(listaFornecedores.selecionado){
				$scope.ativadoExcluirLote = true;
			}
			});
		}
			

		//apagar outros empreendimentos, somente da lista de front
		self.apagarFornecedores = function(fornecedor){
				$scope.fornecedor = fornecedor.filter(function(outro){
				if(!outro.fornecedor) return outro;
				$scope.ativadoExcluirLote = null;
			});
			
			
		}
	
});