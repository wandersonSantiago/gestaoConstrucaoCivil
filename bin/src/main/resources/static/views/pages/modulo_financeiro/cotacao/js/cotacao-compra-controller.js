app.controller("CotacaoEmpresaCadastarController", CotacaoEmpresaCadastarController);
app.controller("CotacaoEmpresaEditarController", CotacaoEmpresaEditarController);
app.controller("CotacaoEmpresaListarController", CotacaoEmpresaListarController);
app.controller("CotacaoEmpresaLancarController", CotacaoEmpresaLancarController);
app.controller("CotacaoEmpresaLancarEditarController", CotacaoEmpresaLancarEditarController);
app.controller("CotacaoEmpresaShowController", CotacaoEmpresaShowController);

function CotacaoEmpresaCadastarController($localStorage, blockUI, $state, $stateParams, CotacaoEmpresaService, ProdutoService, toastr, $scope){
	
	var self = this;	
	
	self.submit = submit;
	self.itens = [];
	self.cotacao = {itens : self.itens};
	self.adicionarProdutos = adicionarProdutos;
	self.ativarExcluirLote = ativarExcluirLote;
	self.apagarProdutos = apagarProdutos;
	$scope.backPage = $stateParams.backPage;
	self.proximaPagina = proximaPagina;
	self.clear = clear;
	
	unidadesMedida();
	
	if($localStorage.cotacaoEmpresa){
		self.cotacaoEmpresa = $localStorage.cotacaoEmpresa;
	}
	
	function proximaPagina(proxima){
		var backPage = 'cotacao.cadastrar';
		$state.go(proxima ,{backPage});
		$localStorage.cotacaoEmpresa = self.cotacaoEmpresa;
	}
	
	function submit(form){
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		if(self.itens.length < 1){
			sweetAlert({title: "É Obrigatório pelo menos 1 item", 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		 blockUI.start();
				self.cotacao.itens = self.itens;
				CotacaoEmpresaService.insert(self.cotacao).
				then(function(response){
					toastr.success("Cotacao, cadastrado")
					self.cotacaoEmpresa = null;
					if($scope.backPage){
						$state.go($scope.backPage);
					}
					 blockUI.stop();
					clear(form);
				}, function(errResponse){
					 blockUI.stop();
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
		
	function adicionarProdutos(descricao , quantidade, unidadeMedida){
		self.itens.push({
			descricao : descricao,
			quantidade : quantidade, 
			unidadeMedida: unidadeMedida
		})
	}
	
	
	function ativarExcluirLote(itens){
		self.itens.filter(function(f){
		if(f.selecionado){
			$scope.ativadoExcluirLote = true; }
		});
	}
		
	function apagarProdutos(itens){
			self.itens = self.itens.filter(function(f){
			if(!f.selecionado) return f;
			$scope.ativadoExcluirLote = false;
		});
	}
	
	 function unidadesMedida(){
		 ProdutoService.findByUnidadesMedida().then(function(u){
			 self.unidadesMedida = u;
		 },function(erro){

		 })
	 };
	 
	function clear(form){	    	 
    	 form.$setPristine();
    	 form.$setUntouched();   
    	 self.cotacao = null;
    	 $localStorage.$reset();
    	 self.itens = [];
    	 self.quantidade = null;
    	 self.descricao = null;
    	 self.unidadeMedida = null;
    	
     }
		
	 	 
			
}		

function CotacaoEmpresaEditarController($localStorage, blockUI, $state, $stateParams, CotacaoEmpresaService, ProdutoService, toastr, $scope){
	
	var self = this;
	
	var idCotacao = $stateParams.idCotacao;
	
	self.submit = submit;
	self.itens = [];
	self.cotacao = {itens : self.itens};
	self.adicionarProdutos = adicionarProdutos;
	self.ativarExcluirLote = ativarExcluirLote;
	self.apagarProdutos = apagarProdutos;
	$scope.backPage = $stateParams.backPage;
	self.clear = clear;
	unidadesMedida();
	
	self.proximaPagina = proximaPagina;
	findById(idCotacao);
	
	if($localStorage.cotacao){
		self.cotacao = $localStorage.cotacao;
	}
	
	function proximaPagina(proxima){
		var backPage = 'cotacao.editar';
		$state.go(proxima ,{backPage});
		$localStorage.cotacao = self.cotacao;
	}
	
	function submit(form){
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		if(self.itens.length < 1){
			sweetAlert({title: "É Obrigatório pelo menos 1 item", 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		 blockUI.start();
				self.cotacao.itens = self.itens;
				CotacaoEmpresaService.update(self.cotacao).
				then(function(response){
					 blockUI.stop();
					toastr.success("Cotacao, Alterada")
					self.cotacao = null;
					if($scope.backPage){
						$state.go($scope.backPage);
					}else{
						$state.go('cotacao.consultar');
					}
					clear(form);
				}, function(errResponse){
					 blockUI.stop();
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
		
	function adicionarProdutos(descricao , quantidade, unidadeMedida){
		self.itens.push({
			descricao : descricao,
			quantidade : quantidade, 
			unidadeMedida: unidadeMedida
		})
	}
		
	function ativarExcluirLote(itens){
		self.itens.filter(function(f){
		if(f.selecionado){
			$scope.ativadoExcluirLote = true; }
		});
	}
		
	function unidadesMedida(){
		 ProdutoService.findByUnidadesMedida().then(function(u){
			 self.unidadesMedida = u;
		 },function(erro){

		 })
	 };
	 
	function apagarProdutos(itens){
			self.itens = self.itens.filter(function(f){
			if(!f.selecionado) return f;
			$scope.ativadoExcluirLote = false;
		});
	}
		
	function clear(form){	    	 
    	 form.$setPristine();
    	 form.$setUntouched();   
    	 self.cotacao = null;
    	 $localStorage.$reset()
    	
     }
	
	function findById(id){
		if(!id)return;
		CotacaoEmpresaService.findById(id).
		then(function(p){
			self.cotacao = p;
			self.itens = p.itens;
			self.cotacao.dataLimite = new Date(p.dataLimite);
			}, function(errResponse){
		});
	};
		
}
function CotacaoEmpresaLancarController($localStorage, blockUI, $state, $stateParams, FornecedorService, CotacaoEmpresaService, SubCategoriaService, CategoriaService, FabricanteService, toastr, $scope){
	
	var self = this;
	
	var idCotacao = $stateParams.idCotacao;
	
	self.submit = submit;
	self.itens = [];
	self.cotacao = {id : idCotacao}
	self.cotacaoEmpresa = {itens : self.itens, cotacao : self.cotacao};
	self.adicionarProdutos = adicionarProdutos;
	self.ativarExcluirLote = ativarExcluirLote;
	self.apagarProdutos = apagarProdutos;
	self.fornecedores = fornecedores;
	$scope.backPage = $stateParams.backPage;

	
	self.proximaPagina = proximaPagina;
	findById(idCotacao);
	
	if($localStorage.cotacaoEmpresa){
		self.cotacaoEmpresa = $localStorage.cotacaoEmpresa;
	}
	
	function proximaPagina(proxima){
		var backPage = 'cotacao.lancar';
		$state.go(proxima ,{backPage});
		$localStorage.cotacaoEmpresa = self.cotacaoEmpresa;
	}
	
	function submit(form){
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		if(self.itens.length < 1){
			sweetAlert({title: "É Obrigatório pelo menos 1 item", 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		 blockUI.start();
				self.cotacaoEmpresa.itens = self.itens;
				CotacaoEmpresaService.launch(self.cotacaoEmpresa).
				then(function(response){
					 blockUI.stop();
					toastr.success("Cotacao, Cadastrada para o fornecedor : " + self.cotacaoEmpresa.fornecedor.dadoEmpresa.nomeFantasia)
					self.cotacaoEmpresa = null;
					if($scope.backPage){
						$state.go($scope.backPage);
					}else{
						$state.go('cotacao.consultar');
					}
					clear(form);
				}, function(errResponse){
					 blockUI.stop();
					sweetAlert({ timer : 3000,  text : errResponse,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
		
	function adicionarProdutos(descricao , quantidade){
		self.itens.push({
			descricao : descricao,
			quantidade : quantidade	
		})
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
	
	function ativarExcluirLote(itens){
		self.itens.filter(function(f){
		if(f.selecionado){
			$scope.ativadoExcluirLote = true; }
		});
	}
		
	function apagarProdutos(itens){
			self.itens = self.itens.filter(function(f){
			if(!f.selecionado) return f;
			$scope.valorTotalCotacao -= f.valorTotal;
			$scope.ativadoExcluirLote = false;
		});
	}
		
	function clear(form){	    	 
    	 form.$setPristine();
    	 form.$setUntouched();   
    	 self.cotacaoEmpresa = null;
    	 $localStorage.$reset()
    	
     }
	 function fornecedores(texto){
	     	return  FornecedorService.buscarPorTexto(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     } ;	 	
	function findById(id){
		if(!id)return;
		CotacaoEmpresaService.findById(id).
		then(function(p){
			self.cotacaoEmpresa.cotacao = p;
			self.itens = p.itens;
			self.cotacaoEmpresa.cotacao.dataLimite = new Date(p.dataLimite);
			}, function(errResponse){
		});
	};
		
}

function CotacaoEmpresaLancarEditarController($localStorage, blockUI, $state, $stateParams, FornecedorService, CotacaoEmpresaService, SubCategoriaService, CategoriaService, FabricanteService, toastr, $scope){
	
	var self = this;
	
	var idCotacaoEmpresa = $stateParams.idCotacaoEmpresa;
	
	self.submit = submit;
	self.ativarExcluirLote = ativarExcluirLote;
	self.apagarProdutos = apagarProdutos;
	$scope.backPage = $stateParams.backPage;

	
	self.proximaPagina = proximaPagina;
	findByCotacaoEmpresaId(idCotacaoEmpresa);
	
	if($localStorage.cotacaoEmpresa){
		self.cotacaoEmpresa = $localStorage.cotacaoEmpresa;
	}
	
	function proximaPagina(proxima){
		var backPage = 'cotacao.lancar';
		$state.go(proxima ,{backPage});
		$localStorage.cotacaoEmpresa = self.cotacaoEmpresa;
	}
	
	function submit(form){
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		if(self.itens.length < 1){
			sweetAlert({title: "É Obrigatório pelo menos 1 item", 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		 blockUI.start();
				self.cotacaoEmpresa.itens = self.itens;
				CotacaoEmpresaService.launch(self.cotacaoEmpresa).
				then(function(response){
					 blockUI.stop();
					toastr.success("Cotacao, Cadastrada para o fornecedor : " + self.cotacaoEmpresa.fornecedor.dadoEmpresa.nomeFantasia)
					self.cotacaoEmpresa = null;
					if($scope.backPage){
						$state.go($scope.backPage);
					}else{
						$state.go('cotacao.consultar');
					}
					clear(form);
				}, function(errResponse){
					 blockUI.stop();
					sweetAlert({ timer : 3000,  text : errResponse,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
		
		
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
	
	function ativarExcluirLote(itens){
		self.itens.filter(function(f){
		if(f.selecionado){
			$scope.ativadoExcluirLote = true; }
		});
	}
		
	function apagarProdutos(itens){
			self.itens = self.itens.filter(function(f){
			if(!f.selecionado) return f;
			$scope.valorTotalCotacao -= f.valorTotal;
			$scope.ativadoExcluirLote = false;
		});
	}
		
	function clear(form){	    	 
    	 form.$setPristine();
    	 form.$setUntouched();   
    	 self.cotacaoEmpresa = null;
    	 $localStorage.$reset()
    	
     }
	 	
	function findByCotacaoEmpresaId(id){
		if(!id)return;
		CotacaoEmpresaService.findByCotacaoEmpresaId(id).
		then(function(p){
			self.cotacaoEmpresa = p;
			self.itens = p.itens;
			self.cotacaoEmpresa.cotacao.dataLimite = new Date(p.cotacao.dataLimite);
			}, function(errResponse){
		});
	};
		
}
function CotacaoEmpresaListarController(blockUI, CotacaoEmpresaService, toastr, $scope){
	
	var self = this;
	
	self.sort = sort;	
	self.closeCotacao = closeCotacao;
	self.imprimirVencedores =imprimirVencedores;
	$scope.filter = filter;
	$scope.pesquisar = pesquisar;
	self.page = {page : 0 ,linesPerPage : 24 , orderBy : 'id' , direction : 'ASC'};
	$scope.cotacaoFilter = {page : self.page}
	$scope.imprimir = 'PAGINA';
	self.clear = clear;
	
	status();
	filter($scope.cotacaoFilter);
	
	
		
	function sort(orderBy){		
		$scope.cotacaoFilter.page.orderBy = orderBy;
		$scope.cotacaoFilter.page.direction == 'ASC' ? $scope.cotacaoFilter.page.direction = 'DESC' : $scope.cotacaoFilter.page.direction = 'ASC';
		filter($scope.cotacaoFilter);
	}
	
	function pesquisar(cotacaoFilter, imprimir){
		if(imprimir == 'PAGINA'){
			filter(cotacaoFilter);
		}else{
			pdf(cotacaoFilter);
		}
	}
	
	    	    
	    function filter(cotacaoFilter){
	    	$scope.mensagemErro = null;	    	 
	    	 blockUI.start();
	    	 cotacaoFilter.page.page == '0'? cotacaoFilter.page.page = 0 : cotacaoFilter.page.page = cotacaoFilter.page.page - 1;   
	    	 CotacaoEmpresaService.filter(cotacaoFilter).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		 self.cotacaoEmpresa = e.content;	
	    		 $scope.cotacaoFilter.page.totalElementos = e.totalElements;
	    		 $scope.cotacaoFilter.page.totalPaginas = e.totalPages;
	    		 $scope.cotacaoFilter.page.page = e.number + 1;
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
	    
	    function pdf(cotacaoFilter){	
	    	$scope.mensagemErro = null;	    	 
	    	 blockUI.start();
	    	 cotacaoFilter.page.page == '0'? cotacaoFilter.page.page = 0 : cotacaoFilter.page.page = cotacaoFilter.page.page - 1;  
	    	 CotacaoEmpresaService.pdf(cotacaoFilter)
	   	 .then(function(d){
	   		var file = new Blob([d],{type: 'application/pdf'});
	   		var fileURL = URL.createObjectURL(file);
	   		blockUI.stop();
	   	    window.open(fileURL);
	   	 	 }).catch(function error(msg) {			 	   	 		 
	    	});
     };	     
     
	    function status(){
			CotacaoEmpresaService.status().
			then(function(p){
				self.status = p;
				}, function(errResponse){
			});
		};

	   function closeCotacao(id) {
			swal({ 
				  title: 'Encerrar!!!',
				  text: "Tem certeza que deseja encerrar esta cotação ?",
				  type: 'info',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Encerrar!'
				}).then(function () {
					CotacaoEmpresaService.closeCotacao(id).
			then(function(response){				
				filter($scope.cotacaoFilter);
				toastr.success("Cotacao, Encerrada")
			});
		})
	}
	   
	   function imprimirVencedores(idCotacao){	
	    	 blockUI.start();
	    	 CotacaoEmpresaService.imprimirVencedores(idCotacao)
	   	 .then(function(d){
	   		var file = new Blob([d],{type: 'application/pdf'});
	   		var fileURL = URL.createObjectURL(file);
	   		blockUI.stop();
	   	    window.open(fileURL);
	   	 	 }).catch(function error(msg) {			 	   	 		 
	    	});
    };	   
    
    function clear(){
		$scope.cotacaoFilter = {};
		self.cotacaoEmpresa = [];
	}  
	
}


function CotacaoEmpresaShowController( $state, $stateParams, CotacaoEmpresaService, $scope, blockUI){
	
	var self = this;
	
	var idCotacao = $stateParams.idCotacao;
	self.visualizarItem = visualizarItem;
	self.imprimirCotacaoEmpresa = imprimirCotacaoEmpresa;
	
	$scope.visualizarItem = false;
	findById(idCotacao);
	
	function findById(id){
		if(!id)return;
		CotacaoEmpresaService.findById(id).
		then(function(p){
			self.cotacao = p;
			if(p.statusCotacao == 'FECHADO'){
					findByVencedores(idCotacao);
					self.titulo = 'Vencedores';
				}else{
					findByConcorrentes(idCotacao);
					self.titulo = 'Concorrentes';
				}
			}, function(errResponse){
		});
	};
	
	function findByConcorrentes(idCotacao){
		if(!idCotacao)return;
		CotacaoEmpresaService.findByConcorrentes(idCotacao).
		then(function(p){
			self.empresas = p;
			}, function(errResponse){
		});
	};
	
	function findByVencedores(idCotacao){
		if(!idCotacao)return;
		CotacaoEmpresaService.findByVencedores(idCotacao).
		then(function(p){
			self.empresas = p;
			}, function(errResponse){
		});
	};
	
	function visualizarItem(item){
		$scope.item = item;
		$scope.visualizarItem == true ? $scope.visualizarItem = false : $scope.visualizarItem = true;
	}
	
	 function imprimirCotacaoEmpresa(idCotacaoEmpresa){	
    	 blockUI.start();
    	 CotacaoEmpresaService.imprimirCotacaoEmpresa(idCotacaoEmpresa)
   	 .then(function(d){
   		var file = new Blob([d],{type: 'application/pdf'});
   		var fileURL = URL.createObjectURL(file);
   		blockUI.stop();
   	    window.open(fileURL);
   	 	 }).catch(function error(msg) {			 	   	 		 
    	});
};	     
}

