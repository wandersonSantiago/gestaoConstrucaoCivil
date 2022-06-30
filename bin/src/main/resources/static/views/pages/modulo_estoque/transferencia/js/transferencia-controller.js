app.controller("TransferenciaCadastarController", TransferenciaCadastarController);
app.controller("TransferenciaEditarController", TransferenciaEditarController);
app.controller("TransferenciaListarController", TransferenciaListarController);
app.controller("TransferenciaShowController", TransferenciaShowController);

function TransferenciaCadastarController($localStorage, $state, $stateParams, TransferenciaService, EstoqueService, EmpreendimentoService, toastr, $scope){
	
	var self = this;	
	
	self.submit = submit;
	self.produtos = produtos;
	self.empreendimentos = empreendimentos;

	$scope.produtos = [];
	self.adicionaProduto = adicionaProduto;
	self.removerProduto = removerProduto;
	self.soma = soma;
	self.somaUnitario = somaUnitario;
	self.ativarExcluirLote = ativarExcluirLote;
	self.apagarProdutos = apagarProdutos;	
	self.verificaQuantidadeDoProduto = verificaQuantidadeDoProduto;
	self.itens = [];
	
	function submit(form){
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
			 self.transferencia.itens = $scope.produtos;
			 self.transferencia.notaFiscal.valorTotal  = $scope.valorTotalNota;
				TransferenciaService.insert(self.transferencia).
				then(function(response){
					toastr.success("Transferencia, enviada");					
					clear(form);
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
	function clear(form){	    	 
    	 form.$setPristine();
    	 form.$setUntouched();   
    	 self.transferencia = null;
    	 $localStorage.$reset()
    	 $scope.valorTotalNota = 0;
		 self.notaFiscalProduto = [];
		 $scope.produtos = [];	
     }				
	
	
	 function produtos(texto){
	     	return  EstoqueService.findByTextAndPagination(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     } ;	 	
	     
     function empreendimentos(texto){
	     	return  EmpreendimentoService.buscarPorTexto(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     } ;	 	
		 	 	
	 function adicionaProduto(produto){
		 self.produto = null;
		 for(var i =0; i < $scope.produtos.length ; i ++){
			 if($scope.produtos[i].produto.id == produto.produto.id){
				 sweetAlert({title: "Produto não pode ser Repetido", 	type : "info", timer : 1000000,   width: 500,  padding: 20});	
		 			return
			 }
		 }
	 		if(produto.produto.id){
	 			$scope.produtos.push({
	 				produto : produto.produto,
	 				quantidadeAtual : produto.quantidade,
	 				custoMedio : produto.custoMedio,
	 				valorUnitario : produto.custoMedio
	 				});
	 		}
	 		
	 	}
	 
	 function verificaQuantidadeDoProduto(produto){
		 soma($scope.produtos);
		 if(produto.quantidadeAtual < produto.quantidade){
			 sweetAlert({title: "Quantidade não permitida!", 	type : "info", timer : 1000000,   width: 500,  padding: 20});	
			 $scope.valorTotalNota -= produto.valorTotal;
			 produto.quantidade = null;
		 }
	 }
	 	
	 function removerProduto(produto){
	 		var indice = $scope.produtos.indexOf(produto);
	 		$scope.produtos.splice(indice,1)
	 	}
	 
	 function soma(produtos){
			var totalSoma = 0;
			for(i =0; i < produtos.length ; i ++){
				console.log(produtos[i]);
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

function TransferenciaEditarController($localStorage, $state, $stateParams, TransferenciaService, EstoqueService, EmpreendimentoService, FabricanteService, toastr, $scope){
	
	var self = this;
	
	var idTransferencia = $stateParams.idTransferencia;
	self.submit = submit;
	self.produtos = produtos;
	self.empreendimentos = empreendimentos;

	$scope.produtos = [];
	self.adicionaProduto = adicionaProduto;
	self.removerProduto = removerProduto;
	self.soma = soma;
	self.somaUnitario = somaUnitario;
	self.ativarExcluirLote = ativarExcluirLote;
	self.apagarProdutos = apagarProdutos;
	self.itens = [];
	
	
	function submit(form){
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
			 self.transferencia.itens = $scope.produtos;
			 self.transferencia.notaFiscal.valorTotal  = $scope.valorTotalNota;
				TransferenciaService.insert(self.transferencia).
				then(function(response){
					toastr.success("Transferencia, Editada")
					self.notaFiscalProduto = null;
					$scope.produtos = null;					
					clear(form);
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
	function clear(form){	    	 
    	 form.$setPristine();
    	 form.$setUntouched();   
    	 self.transferencia = null;
    	 $localStorage.$reset()
    	
     }				
	
	
	 function produtos(texto){
	     	return  EstoqueService.findByTextAndPagination(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     } ;	 	
	     
     function empreendimentos(texto){
	     	return  EmpreendimentoService.buscarPorTexto(texto).
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
	 			$scope.produtos.push({
	 				produto : produto.produto,
	 				quantidade : produto.quantidadeBaixa,
	 				quantidadeAtual : produto.quantidade,
	 				custoMedio : produto.custoMedio,
	 				valorUnitario : produto.custoMedio
	 				});
	 		}
	 		
	 	}
	 
	
	 	
	 function removerProduto(produto){
	 		var indice = $scope.produtos.indexOf(produto);
	 		$scope.produtos.splice(indice,1)
	 	}
	 
	 function soma(produtos){
			var totalSoma = 0;
			for(i =0; i < produtos.length ; i ++){
				console.log(produtos[i]);
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
	
	
	findById(idTransferencia);
		
    		
	 function findById(id){
		if(!id)return;
		TransferenciaService.findById(id).
		then(function(p){
			self.transferencia = p;
			for(var i = 0; i < p.itens.length ; i++){
				console.log(p.itens[i]);
				adicionaProdutoRecuperadoNaLista(p.itens[i]);
			}
			soma(p.itens);
			self.transferencia.notaFiscal.dataVencimento = new Date(p.notaFiscal.dataVencimento);
			}, function(errResponse){
		});
	};
	function adicionaProdutoRecuperadoNaLista(produto){
		$scope.produtos.push({
				produto : produto.produto,
				quantidade : produto.quantidade,
 				quantidadeAtual : produto.quantidade,
				custoMedio : produto.valorUnitario,
				valorUnitario : produto.valorUnitario
				});
	}
		
}

function TransferenciaListarController(blockUI, TransferenciaService, toastr, $scope, $stateParams){
	
	var self = this;
	var tipo = $stateParams.tipo;
	self.sort = sort;
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = null;
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	self.visualizar = visualizar;
	
	self.aceitarTransferencia = aceitarTransferencia;
	self.rejeitarTransferencia = rejeitarTransferencia;
	
	self.titulo = tipo;
	
	$scope.direction = 'ASC';
	
	buscarPorTexto('', null, null);
		 
	function sort(orderBy){		
		$scope.direction == 'ASC' ? $scope.direction = 'DESC' : $scope.direction = 'ASC';
		buscarPorTexto($scope.texto, orderBy,  $scope.direction);
	}    
	     
	    function buscarPorTexto(texto, orderBy,  direction){
	    	$scope.mensagemErro = null;	    	 
	    	 blockUI.start();
	    	 self.paginaCorrente == '0'? self.paginaCorrente = 0 : self.paginaCorrente = self.paginaCorrente - 1;   
	    	 TransferenciaService.findByTextAndPagination(tipo, texto, self.paginaCorrente, orderBy, direction).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		 self.transferencias = e.content;	
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
	    

	    function visualizar(transferencia) {
			self.transferencia = transferencia;
			$scope.produtos = transferencia.itens;
		}
	    
	    function aceitarTransferencia(numero){
			if(!numero)return;
			TransferenciaService.aceitarTransferencia(numero).
			then(function(p){
				toastr.info("Transferencia, Aceita")
				findById(idTransferencia);
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
		
		function rejeitarTransferencia(numero){
			if(!numero)return;
			TransferenciaService.rejeitarTransferencia(numero).
			then(function(p){
				toastr.info("Transferencia, Rejeitada")
				findById(idTransferencia);
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
	
}
function TransferenciaShowController($stateParams, TransferenciaService,  toastr, $scope){
	
	var self = this;	
	

	self.itens = [];
	
	var idTransferencia = $stateParams.idTransferencia;
	self.aceitarTransferencia = aceitarTransferencia;
	self.rejeitarTransferencia = rejeitarTransferencia;
	
	findById(idTransferencia);
	
	
	 function findById(id){
			if(!id)return;
			TransferenciaService.findById(id).
			then(function(p){
				self.transferencia = p;
				self.transferencia.notaFiscal.dataNota = new Date(p.notaFiscal.dataNota);
				self.transferencia.notaFiscal.dataVencimento = new Date(p.notaFiscal.dataVencimento);
				$scope.produtos = p.itens;
				}, function(errResponse){
			});
		};
		
		function aceitarTransferencia(numero){
			if(!numero)return;
			TransferenciaService.aceitarTransferencia(numero).
			then(function(p){
				toastr.info("Transferencia, Aceita")
				findById(idTransferencia);
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
		
		function rejeitarTransferencia(numero){
			if(!numero)return;
			TransferenciaService.rejeitarTransferencia(numero).
			then(function(p){
				toastr.info("Transferencia, Rejeitada")
				findById(idTransferencia);
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
}		

