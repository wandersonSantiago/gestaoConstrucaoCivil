app.controller("RequisicaoCadastarController", RequisicaoCadastarController);
app.controller("RequisicaoEditarController", RequisicaoEditarController);
app.controller("RequisicaoListarController", RequisicaoListarController);
app.controller("RequisicaoShowController", RequisicaoShowController);

function RequisicaoCadastarController(blockUI, $localStorage, $state, $stateParams, RequisicaoService, EstruturaService, EstoqueService, toastr, $scope){
	
	var self = this;	
	
	self.submit = submit;
	self.produtos = produtos;

	$scope.produtos = [];
	self.adicionaProduto = adicionaProduto;
	self.removerProduto = removerProduto;
	self.soma = soma;
	self.somaUnitario = somaUnitario;
	self.ativarExcluirLote = ativarExcluirLote;
	self.apagarProdutos = apagarProdutos;	
	self.verificaQuantidadeDoProduto = verificaQuantidadeDoProduto;
	self.itens = [];
	self.removerFilhas = removerFilhas;
	self.requisicao = {};
	
	self.listaRaiz = listaRaiz;
	self.buscarFilhas = buscarFilhas;
	self.titulo = "Inicial";
	$scope.titulo = 'Cadastrar';
	$scope.estruturaGeral = [];
	buscarListaFilhas(0);
	
	
	function submit(form){
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		if($scope.produtos == ''){
			sweetAlert({title: "Por favor Selecione pelo menos um produto", 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		if($scope.raiz == null){
			sweetAlert({title: "Por favor Selecione pelo menos uma estrutura", 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
			 	self.requisicao.itens = $scope.produtos;
			 	self.requisicao.estrutura = $scope.raiz;
				RequisicaoService.insert(self.requisicao).
				then(function(response){
					toastr.success("Requisicao, enviada");					
					clear(form);
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
	function clear(form){	    	 
    	 form.$setPristine();
    	 form.$setUntouched();   
    	 self.requisicao = {};
    	 $localStorage.$reset()
		 $scope.produtos = [];	
    	 listaRaiz('0');
     }				
	
	
	 function produtos(texto){
	     	return  EstoqueService.findByTextAndPagination(texto).
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
	 
	 function removerFilhas(estrutura){		
			$scope.estruturaGeral.splice($scope.estruturaGeral.indexOf(estrutura),$scope.estruturaGeral.length - $scope.estruturaGeral.indexOf(estrutura));
			buscarFilhas(estrutura);		
		}
	 
	 function buscarFilhas(estrutura){
			$scope.estruturaGeral.push(estrutura);
			$scope.raiz = estrutura;
			self.titulo = estrutura.descricao;
			buscarListaFilhas(estrutura.id);
		}
	 
	 function listaRaiz(zero){
			self.titulo ="Inicial";
			$scope.estruturaGeral = [];
			buscarListaFilhas(zero);
		}
	 
	 function buscarListaFilhas(idEstruturaRaiz){
		  blockUI.start();
	    	 EstruturaService.buscarListaFolhas(idEstruturaRaiz).
	    	 then(function(e){
	    		 	$scope.mensagemErro = null;
		    		$scope.estruturas = e;	
		    		 blockUI.stop();
	    	 }, function(errResponse){
	    		 blockUI.stop();
	    		 $scope.mensagemErro = "Não existem estruturas Filhas";
		    	 $scope.estruturas = [];
	    	 });
	     };
}		

function RequisicaoEditarController(EstruturaService, blockUI, $localStorage, $state, $stateParams, RequisicaoService, EstoqueService,  toastr, $scope){
	
	var self = this;
	
	var idRequisicao = $stateParams.idRequisicao;

	self.submit = submit;
	self.produtos = produtos;

	$scope.produtos = [];
	self.adicionaProduto = adicionaProduto;
	self.removerProduto = removerProduto;
	self.ativarExcluirLote = ativarExcluirLote;
	self.apagarProdutos = apagarProdutos;	
	self.verificaQuantidadeDoProduto = verificaQuantidadeDoProduto;
	self.itens = [];
	self.removerFilhas = removerFilhas;
	self.requisicao = {};
	
	self.listaRaiz = listaRaiz;
	self.buscarFilhas = buscarFilhas;
	self.titulo = "Inicial";
	$scope.titulo = 'Editar';
	$scope.estruturaGeral = [];
	
	
	
	function submit(form){
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		if($scope.produtos == ''){
			sweetAlert({title: "Por favor Selecione pelo menos um produto", 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		if($scope.raiz == null){
			sweetAlert({title: "Por favor Selecione pelo menos uma estrutura", 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
			 	self.requisicao.itens = $scope.produtos;
			 	self.requisicao.estrutura = $scope.raiz;
				RequisicaoService.insert(self.requisicao).
				then(function(response){
					toastr.success("Requisicao, Editada");	
					var tipo = 'MINHA_REQUISICAO';
					$state.go('requisicao.minhas', {tipo});
					clear(form);
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
	function clear(form){	    	 
    	 form.$setPristine();
    	 form.$setUntouched();   
    	 self.requisicao = {};
    	 $localStorage.$reset()
		 $scope.produtos = [];	
    	 listaRaiz('0');
     }				
	
	
	 function produtos(texto){
	     	return  EstoqueService.findByTextAndPagination(texto).
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
	 				quantidadeAtual : produto.quantidade
	 				});
	 		}
	 		
	 	}
	 
	 function verificaQuantidadeDoProduto(produto){
		 soma($scope.produtos);
		 if(produto.quantidadeAtual < produto.quantidade){
			 sweetAlert({title: "Quantidade não permitida!", 	type : "info", timer : 1000000,   width: 500,  padding: 20});	
			 produto.quantidade = null;
		 }
	 }
	 	
	 function removerProduto(produto){
	 		var indice = $scope.produtos.indexOf(produto);
	 		$scope.produtos.splice(indice,1)
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
			$scope.ativadoExcluirLote = false;
		});
	}
	 
	 function removerFilhas(estrutura){		
			$scope.estruturaGeral.splice($scope.estruturaGeral.indexOf(estrutura),$scope.estruturaGeral.length - $scope.estruturaGeral.indexOf(estrutura));
			buscarFilhas(estrutura);		
		}
	 
	 function buscarFilhas(estrutura){
			$scope.estruturaGeral.push(estrutura);
			$scope.raiz = estrutura;
			self.titulo = estrutura.descricao;
			buscarListaFilhas(estrutura.id);
		}
	 
	 function listaRaiz(zero){
			self.titulo ="Inicial";
			$scope.estruturaGeral = [];
			buscarListaFilhas(zero);
		}
	 
	 function buscarListaFilhas(idEstruturaRaiz){
		  blockUI.start();
	    	 EstruturaService.buscarListaFolhas(idEstruturaRaiz).
	    	 then(function(e){
	    		 	$scope.mensagemErro = null;
		    		$scope.estruturas = e;	
		    		 blockUI.stop();
	    	 }, function(errResponse){
	    		 blockUI.stop();
	    		 $scope.mensagemErro = "Não existem estruturas Filhas";
		    	 $scope.estruturas = [];
	    	 });
	     };
	
	
	findById(idRequisicao);
		
    		
	 function findById(id){
		if(!id)return;
		RequisicaoService.findById(id).
		then(function(p){
			self.requisicao = p;
			buscarFilhas(p.estrutura);
			for(var i = 0; i < p.itens.length ; i++){
				console.log(p.itens[i]);
				adicionaProdutoRecuperadoNaLista(p.itens[i]);
			}
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

function RequisicaoListarController(blockUI, RequisicaoService, toastr, $scope, $stateParams){
	
	var self = this;
	var tipo = $stateParams.tipo;
	self.sort = sort;
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = null;
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	
	self.visualizar = visualizar;
	
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
	    	 RequisicaoService.findByTextAndPagination(tipo, texto, self.paginaCorrente, orderBy, direction).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		 self.requisicaos = e.content;	
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
	
	    
	    function visualizar(requisicao) {
			self.requisicao = requisicao;
			$scope.produtos = requisicao.itens;
		}
	    
	    self.aceitarRequisicao = aceitarRequisicao;
		self.rejeitarRequisicao = rejeitarRequisicao;
		
		function aceitarRequisicao(idRequisicao){
			if(!idRequisicao)return;
			RequisicaoService.aceitarRequisicao(idRequisicao).
			then(function(p){
				toastr.info("Requisicao, Aceita")
				buscarPorTexto('', null, null);
				$('.modalEstrutura').modal('hide');
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
		
		function rejeitarRequisicao(idRequisicao){
			if(!idRequisicao)return;
			RequisicaoService.rejeitarRequisicao(idRequisicao).
			then(function(p){
				toastr.info("Requisicao, Rejeitada")
				buscarPorTexto('', null, null);
				$('.modalEstrutura').modal('hide');
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
}
function RequisicaoShowController($stateParams, RequisicaoService,  toastr, $scope, $state){
	
	var self = this;	
	

	self.itens = [];
	
	var idRequisicao = $stateParams.idRequisicao;
	self.aceitarRequisicao = aceitarRequisicao;
	self.rejeitarRequisicao = rejeitarRequisicao;
	
	findById(idRequisicao);
	
	
	 function findById(id){
			if(!id)return;
			RequisicaoService.findById(id).
			then(function(p){
				self.requisicao = p;
				$scope.produtos = p.itens;
				}, function(errResponse){
			});
		};
		
		function aceitarRequisicao(idRequisicao){
			if(!idRequisicao)return;
			RequisicaoService.aceitarRequisicao(idRequisicao).
			then(function(p){
				toastr.info("Requisicao, Aceita")
				var tipo = 'LIBERAR';
				$state.go('requisicao.full', {tipo});
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
		
		function rejeitarRequisicao(idRequisicao){
			if(!idRequisicao)return;
			RequisicaoService.rejeitarRequisicao(idRequisicao).
			then(function(p){
				toastr.info("Requisicao, Rejeitada")
				var tipo = 'LIBERAR';
				$state.go('requisicao.full', {tipo});
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
}		

