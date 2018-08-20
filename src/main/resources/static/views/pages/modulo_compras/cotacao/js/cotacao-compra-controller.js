app.controller("CotacaoEmpresaCadastarController", CotacaoEmpresaCadastarController);
app.controller("CotacaoEmpresaEditarController", CotacaoEmpresaEditarController);
app.controller("CotacaoEmpresaListarController", CotacaoEmpresaListarController);
app.controller("CotacaoEmpresaLancarController", CotacaoEmpresaLancarController);
app.controller("CotacaoEmpresaLancarEditarController", CotacaoEmpresaLancarEditarController);
app.controller("CotacaoEmpresaShowController", CotacaoEmpresaShowController);

function CotacaoEmpresaCadastarController($localStorage, $state, $stateParams, CotacaoEmpresaService, SubCategoriaService, CategoriaService, FabricanteService, toastr, $scope){
	
	var self = this;	
	
	self.submit = submit;
	self.itens = [];
	self.cotacao = {itens : self.itens};
	self.adicionarProdutos = adicionarProdutos;
	self.ativarExcluirLote = ativarExcluirLote;
	self.apagarProdutos = apagarProdutos;
	$scope.backPage = $stateParams.backPage;
	self.proximaPagina = proximaPagina;
	
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
				self.cotacao.itens = self.itens;
				CotacaoEmpresaService.insert(self.cotacao).
				then(function(response){
					toastr.success("Cotacao, cadastrado")
					self.cotacaoEmpresa = null;
					if($scope.backPage){
						$state.go($scope.backPage);
					}
					clear(form);
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
		
	function adicionarProdutos(descricao , quantidade){
		self.itens.push({
			descricao : descricao,
			quantidade : quantidade	
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
	function clear(form){	    	 
    	 form.$setPristine();
    	 form.$setUntouched();   
    	 self.cotacao = null;
    	 $localStorage.$reset();
    	 self.itens = [];
    	
     }
		
	 	 
			
}		

function CotacaoEmpresaEditarController($localStorage, $state, $stateParams, CotacaoEmpresaService, SubCategoriaService, CategoriaService, FabricanteService, toastr, $scope){
	
	var self = this;
	
	var idCotacao = $stateParams.idCotacao;
	
	self.submit = submit;
	self.itens = [];
	self.cotacao = {itens : self.itens};
	self.adicionarProdutos = adicionarProdutos;
	self.ativarExcluirLote = ativarExcluirLote;
	self.apagarProdutos = apagarProdutos;
	$scope.backPage = $stateParams.backPage;

	
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
				self.cotacao.itens = self.itens;
				CotacaoEmpresaService.update(self.cotacao).
				then(function(response){
					toastr.success("Cotacao, Alterada")
					self.cotacao = null;
					if($scope.backPage){
						$state.go($scope.backPage);
					}else{
						$state.go('cotacao.consultar');
					}
					clear(form);
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
		
	function adicionarProdutos(descricao , quantidade){
		self.itens.push({
			descricao : descricao,
			quantidade : quantidade	
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
function CotacaoEmpresaLancarController($localStorage, $state, $stateParams, FornecedorService, CotacaoEmpresaService, SubCategoriaService, CategoriaService, FabricanteService, toastr, $scope){
	
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
				self.cotacaoEmpresa.itens = self.itens;
				CotacaoEmpresaService.launch(self.cotacaoEmpresa).
				then(function(response){
					toastr.success("Cotacao, Cadastrada para o fornecedor : " + self.cotacaoEmpresa.fornecedor.dadoEmpresa.nomeFantasia)
					self.cotacaoEmpresa = null;
					if($scope.backPage){
						$state.go($scope.backPage);
					}else{
						$state.go('cotacao.consultar');
					}
					clear(form);
				}, function(errResponse){
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

function CotacaoEmpresaLancarEditarController($localStorage, $state, $stateParams, FornecedorService, CotacaoEmpresaService, SubCategoriaService, CategoriaService, FabricanteService, toastr, $scope){
	
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
				self.cotacaoEmpresa.itens = self.itens;
				CotacaoEmpresaService.launch(self.cotacaoEmpresa).
				then(function(response){
					toastr.success("Cotacao, Cadastrada para o fornecedor : " + self.cotacaoEmpresa.fornecedor.dadoEmpresa.nomeFantasia)
					self.cotacaoEmpresa = null;
					if($scope.backPage){
						$state.go($scope.backPage);
					}else{
						$state.go('cotacao.consultar');
					}
					clear(form);
				}, function(errResponse){
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
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = null;
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	
	self.closeCotacao = closeCotacao;
	
	buscarPorTexto('');
	status();	 
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
	    	 CotacaoEmpresaService.findByTextAndPagination(texto, self.paginaCorrente, orderBy, direction).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		 self.cotacaoEmpresa = e.content;	
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
				buscarPorTexto('');
				toastr.success("Cotacao, Encerrada")
			});
		})
	}
	
}


function CotacaoEmpresaShowController( $state, $stateParams, CotacaoEmpresaService, $scope){
	
	var self = this;
	
	var idCotacao = $stateParams.idCotacao;
	
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
}

