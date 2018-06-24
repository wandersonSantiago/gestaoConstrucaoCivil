app.controller("EstoqueCadastarController", EstoqueCadastarController);
app.controller("EstoqueEditarController", EstoqueEditarController);
app.controller("EstoqueListarController", EstoqueListarController);
app.controller("EstoqueConfiguracaoController", EstoqueConfiguracaoController);

function EstoqueCadastarController($localStorage, $state, $stateParams, EstoqueService, ProdutoService, FornecedorService, toastr, $scope){
	
	var self = this;	
	
	self.submit = submit;
	self.produtos = produtos;
	self.fornecedores = fornecedores;

	$scope.produtos = [];
	self.adicionaProduto = adicionaProduto;
	self.removerProduto = removerProduto;
	self.soma = soma;
	self.somaUnitario = somaUnitario;
	self.ativarExcluirLote = ativarExcluirLote;
	self.apagarProdutos = apagarProdutos;
	self.itens = [];
	
	$scope.backPage = $stateParams.backPage;
	self.proximaPagina = proximaPagina;
	
	if($localStorage.estoque){
		self.estoque = $localStorage.estoque;
	}
	
	function proximaPagina(proxima){
		var backPage = 'estoque.cadastrar';
		$state.go(proxima ,{backPage});
		$localStorage.estoque = self.estoque;
	}
	
	function submit(form){
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
			 self.notaFiscalProduto.itens = $scope.produtos;
			 self.notaFiscalProduto.notaFiscal.valorTotal  = $scope.valorTotalNota;
			 console.log(self.notaFiscalProduto);
				EstoqueService.insert(self.notaFiscalProduto).
				then(function(response){
					toastr.success("Estoque, cadastrado")
					self.notaFiscalProduto = null;
					$scope.produtos = null;
					if($scope.backPage){
						$state.go($scope.backPage);
					}
					clear(form);
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
	function clear(form){	    	 
    	 form.$setPristine();
    	 form.$setUntouched();   
    	 self.estoque = null;
    	 $localStorage.$reset()
    	
     }				
	
	
	 function produtos(texto){
	     	return  ProdutoService.findByDescricao(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     } ;	 	
     function fornecedores(texto){
	     	return  FornecedorService.buscarPorTexto(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     } ;	 	
		 	 	
	 function adicionaProduto(produto){
		 self.produto = null;
		 var indice = $scope.produtos.indexOf(produto);
	 		if(indice > 0){
	 			sweetAlert({title: "Produto Repetido", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
	 			return
	 		}
	 		if(produto){
	 			$scope.produtos.push({produto});
	 		}
	 		
	 	}
	 
	
	 	
	 function removerProduto(produto){
	 		var indice = $scope.produtos.indexOf(produto);
	 		$scope.produtos.splice(indice,1)
	 	}
	 
	 function soma(produtos){
			var totalSoma = 0;
			for(i =0; i < produtos.length ; i ++){
				var total = produtos[i];
				totalSoma += parseFloat(total.valorTotal);	
				$scope.valorTotalNota = totalSoma;
					}
			}
		
	 function somaUnitario(quantidade, valorUnitario){
			return $scope.valorTotal = quantidade * valorUnitario;
		}
		
		
		
	 function ativarExcluirLote(produtos){
			$scope.produtos.filter(function(f){
			if(f.selecionado){
				$scope.ativadoExcluirLote = true; }
			});
		}
	 
	 function apagarProdutos(produtos){
			$scope.produtos = $scope.produtos.filter(function(f){
			if(!f.selecionado) return f;
			$scope.valorTotalNota -= f.valorTotal;
			$scope.ativadoExcluirLote = false;
		});
	}
}		

function EstoqueEditarController($localStorage, $state, $stateParams, EstoqueService, SubCategoriaService, CategoriaService, FabricanteService, toastr, $scope){
	
	var self = this;
	
	var idEstoque = $stateParams.idEstoque;
	
	self.submit = submit;
	
	self.proximaPagina = proximaPagina;
	
	
	findById(idEstoque);
	
	if($localStorage.estoque){
		self.estoque = $localStorage.estoque;
	}
	
	function proximaPagina(proxima){
		var backPage = 'estoque.editar';
		$state.go(proxima ,{backPage});
		$localStorage.estoque = self.estoque;
	}
	
	function submit(form){
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
				EstoqueService.update(self.estoque).
				then(function(response){
					toastr.success("Estoque, Editado")
					self.estoque = null;
					$state.go('estoque.consultar');
					clear(form);
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
	function clear(form){	
    	 $localStorage.$reset()
    	
     }
		
	 function findById(id){
		if(!id)return;
		EstoqueService.findById(id).
		then(function(p){
			self.estoque = p;
			$scope.categoria = p.categoria.categoria;
			subCategoria(p.categoria.categoria.id)
			}, function(errResponse){
		});
	};
		
}

function EstoqueListarController(blockUI, EstoqueService, toastr, $scope){
	
	var self = this;
	
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = null;
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	
	buscarPorTexto('');
		 
	     
	     
	    function buscarPorTexto(texto){
	    	$scope.mensagemErro = null;	    	 
	    	 blockUI.start();
	    	 self.paginaCorrente == '0'? self.paginaCorrente = 0 : self.paginaCorrente = self.paginaCorrente - 1;   
	    	 EstoqueService.findByTextAndPagination(texto, self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		 self.estoques = e.content;	
	    		 self.totalElementos = e.totalElements;
	    		 self.totalPaginas = e.totalPages;
	    		 self.paginaCorrente = e.number + 1;
	    		 blockUI.stop();
	    	 }, function(errResponse){
	    		 blockUI.stop();
	    		 if(errResponse.status == 404){
	    			 $scope.mensagemErro = errResponse.data.message;
	    		 }else{
	    			 $scope.mensagemErro =errResponse.data.message;
	    		 }
			 });
	    }
	
}

function EstoqueConfiguracaoController($state, $stateParams, EstoqueService, toastr, $scope){
	
	var self = this;	
	
	self.submit = submit;
	var idEstoque = $stateParams.idEstoque;

	findById(idEstoque);
	
	function submit(form){
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		if(self.estoque.quantidadeMinima > self.estoque.quantidadeMaxima){
			sweetAlert({title: "Estoque mínimo não pode ser maior que o máximo, favor  refazer a operação", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
				EstoqueService.updateConfiguration(self.estoque).
				then(function(response){
					toastr.success("Configurações, editada");
					$state.go('estoque.consultar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		

	function findById(id){
		if(!id)return;
		EstoqueService.findById(id).
		then(function(p){
			self.estoque = p;
			}, function(errResponse){
		});
	};
	
}		
