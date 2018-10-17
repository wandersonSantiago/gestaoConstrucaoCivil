app.controller("VincularCadastarController", VincularCadastarController);
app.controller("VincularEditarController", VincularEditarController);
app.controller("VincularListarController", VincularListarController);
app.controller("VincularShowController", VincularShowController);

function VincularCadastarController(blockUI, $localStorage, $state, $stateParams, PrestadoraService, VincularService, EstruturaService, PacoteService, toastr, $scope){
	
	var self = this;	
	
	self.submit = submit;
	
	self.itens = [];
	self.removerFilhas = removerFilhas;
	self.vincular = {};
	self.pacotes = pacotes;
	self.prestadoraServico = prestadoraServico;
	self.listaRaiz = listaRaiz;
	self.buscarFilhas = buscarFilhas;
	//self.titulo = "Inicial";
	$scope.titulo = 'Vincular';
	$scope.estruturaGeral = [];
	buscarListaFilhas(0);
	
	
	function submit(form){
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		
		if($scope.raiz == null){
			sweetAlert({title: "Por favor Selecione pelo menos uma estrutura", 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
			 	self.vincular.itens = $scope.produtos;
			 	self.vincular.estrutura = $scope.raiz;
				VincularService.insert(self.vincular).
				then(function(response){
					toastr.success("Vincular, enviada");					
					clear(form);
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
	function clear(form){	    	 
    	 form.$setPristine();
    	 form.$setUntouched();   
    	 self.vincular = {};
    	 $localStorage.$reset()
    	 listaRaiz('0');
     }				
	
	
	 function pacotes(texto){
	     	return  PacoteService.buscarPorTexto(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     } ;	 	
	     
     function prestadoraServico(texto){
	     	return  PrestadoraService.buscarPorTexto(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     } ;	
	 	
	 
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

function VincularEditarController(EstruturaService, blockUI, $localStorage, $state, $stateParams, VincularService, EstoqueService,  toastr, $scope){
	
	var self = this;
	
	var idVincular = $stateParams.idVincular;

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
	self.vincular = {};
	
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
			 	self.vincular.itens = $scope.produtos;
			 	self.vincular.estrutura = $scope.raiz;
				VincularService.insert(self.vincular).
				then(function(response){
					toastr.success("Vincular, Editada");	
					var tipo = 'MINHA_REQUISICAO';
					$state.go('vincular.minhas', {tipo});
					clear(form);
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
	function clear(form){	    	 
    	 form.$setPristine();
    	 form.$setUntouched();   
    	 self.vincular = {};
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
	
	
	findById(idVincular);
		
    		
	 function findById(id){
		if(!id)return;
		VincularService.findById(id).
		then(function(p){
			self.vincular = p;
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

function VincularListarController(blockUI, VincularService, toastr, $scope, $stateParams){
	
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
	    	 VincularService.findByTextAndPagination(tipo, texto, self.paginaCorrente, orderBy, direction).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		 self.vinculars = e.content;	
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
	
	    
	    function visualizar(vincular) {
			self.vincular = vincular;
			$scope.produtos = vincular.itens;
		}
	    
	    self.aceitarVincular = aceitarVincular;
		self.rejeitarVincular = rejeitarVincular;
		
		function aceitarVincular(idVincular){
			if(!idVincular)return;
			VincularService.aceitarVincular(idVincular).
			then(function(p){
				toastr.info("Vincular, Aceita")
				buscarPorTexto('', null, null);
				$('.modalEstrutura').modal('hide');
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
		
		function rejeitarVincular(idVincular){
			if(!idVincular)return;
			VincularService.rejeitarVincular(idVincular).
			then(function(p){
				toastr.info("Vincular, Rejeitada")
				buscarPorTexto('', null, null);
				$('.modalEstrutura').modal('hide');
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
}
function VincularShowController($stateParams, VincularService,  toastr, $scope, $state){
	
	var self = this;	
	

	self.itens = [];
	
	var idVincular = $stateParams.idVincular;
	self.aceitarVincular = aceitarVincular;
	self.rejeitarVincular = rejeitarVincular;
	
	findById(idVincular);
	
	
	 function findById(id){
			if(!id)return;
			VincularService.findById(id).
			then(function(p){
				self.vincular = p;
				$scope.produtos = p.itens;
				}, function(errResponse){
			});
		};
		
		function aceitarVincular(idVincular){
			if(!idVincular)return;
			VincularService.aceitarVincular(idVincular).
			then(function(p){
				toastr.info("Vincular, Aceita")
				var tipo = 'LIBERAR';
				$state.go('vincular.full', {tipo});
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
		
		function rejeitarVincular(idVincular){
			if(!idVincular)return;
			VincularService.rejeitarVincular(idVincular).
			then(function(p){
				toastr.info("Vincular, Rejeitada")
				var tipo = 'LIBERAR';
				$state.go('vincular.full', {tipo});
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
}		

