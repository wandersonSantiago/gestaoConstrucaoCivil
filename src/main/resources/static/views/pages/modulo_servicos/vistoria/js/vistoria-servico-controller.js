app.controller("VistoriaCadastarController", VistoriaCadastarController);
app.controller("VistoriaEditarController", VistoriaEditarController);
app.controller("VistoriaListarController", VistoriaListarController);
app.controller("VistoriaShowController", VistoriaShowController);

function VistoriaCadastarController(blockUI, $localStorage, $state, $stateParams, PrestadoraService, VistoriaService, EstruturaService, PacoteService, toastr, $scope){
	
	var self = this;	
	
	self.submit = submit;
	
	self.itens = [];
	self.removerFilhas = removerFilhas;
	self.vistoria = {};
	self.pacotes = pacotes;
	self.prestadoraServico = prestadoraServico;
	self.listaRaiz = listaRaiz;
	self.buscarFilhas = buscarFilhas;
	$scope.titulo = 'Vistoria';
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
			 	self.servicoEmpresa.estrutura = $scope.raiz;
				VistoriaService.insert(self.servicoEmpresa).
				then(function(response){
					toastr.success("Vistoria, enviada");					
					clear(form);
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
	function clear(form){	    	 
    	 form.$setPristine();
    	 form.$setUntouched();   
    	 self.servicoEmpresa = {};
    	 $localStorage.$reset()
    	// listaRaiz('0');
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

function VistoriaEditarController(EstruturaService, blockUI, $localStorage, $state, $stateParams, VistoriaService, EstoqueService,  toastr, $scope){
	
	var self = this;
	
	var idVistoria = $stateParams.idVistoria;
	
	self.submit = submit;
	
	self.itens = [];
	self.removerFilhas = removerFilhas;
	self.vistoria = {};
	self.pacotes = pacotes;
	self.prestadoraServico = prestadoraServico;
	self.listaRaiz = listaRaiz;
	self.buscarFilhas = buscarFilhas;
	$scope.titulo = 'Vistoria';
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
			 	self.servicoEmpresa.estrutura = $scope.raiz;
				VistoriaService.update(self.servicoEmpresa).
				then(function(response){
					toastr.success("Serviço, editada");	
					$state.go('vistoria.consultar');
					clear(form);
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
	function clear(form){	    	 
    	 form.$setPristine();
    	 form.$setUntouched();   
    	 self.servicoEmpresa = {};
    	 $localStorage.$reset()
    	// listaRaiz('0');
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
	
	
	findById(idVistoria);
		
    		
	 function findById(id){
		if(!id)return;
		VistoriaService.findById(id).
		then(function(p){
			self.servicoEmpresa = p;
			buscarFilhas(p.estrutura);			
			}, function(errResponse){
		});
	};	
		
}

function VistoriaListarController(blockUI, VistoriaService, toastr, $scope, $stateParams){
	
	var self = this;
	self.sort = sort;
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = null;
	self.totalPaginas = null;
	self.paginaCorrente = 0;
		
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
	    	 VistoriaService.findByTextAndPagination(texto, self.paginaCorrente, orderBy, direction).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		 self.vistorias = e.content;	
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
	    
	    $scope.dynamicPopover = {
    		    content: '',
    		    templateUrl: 'myPopoverTemplate.html',
    		    title: 'Title'
    		  };
	    $scope.dynamicPopoverValor = {
    		    content: '',
    		    templateUrl: 'myPopoverTemplateValor.html',
    		    title: 'Title'
    		  };
	    $scope.dynamicPopoverEmpresa = {
    		    content: '',
    		    templateUrl: 'myPopoverTemplateEmpresa.html',
    		    title: 'Title'
    		  };
	    $scope.dynamicPopoverPacote = {
    		    content: '',
    		    templateUrl: 'myPopoverTemplatePacote.html',
    		    title: 'Title'
    		  };
}
function VistoriaShowController($stateParams, VistoriaService,  toastr, $scope, $state){
	
	var self = this;	
	

	self.itens = [];
	
	var idVistoria = $stateParams.idVistoria;
	self.aceitarVistoria = aceitarVistoria;
	self.rejeitarVistoria = rejeitarVistoria;
	
	findById(idVistoria);
	
	
	 function findById(id){
			if(!id)return;
			VistoriaService.findById(id).
			then(function(p){
				self.vistoria = p;
				$scope.produtos = p.itens;
				}, function(errResponse){
			});
		};
		
		function aceitarVistoria(idVistoria){
			if(!idVistoria)return;
			VistoriaService.aceitarVistoria(idVistoria).
			then(function(p){
				toastr.info("Vistoria, Aceita")
				var tipo = 'LIBERAR';
				$state.go('vistoria.full', {tipo});
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
		
		function rejeitarVistoria(idVistoria){
			if(!idVistoria)return;
			VistoriaService.rejeitarVistoria(idVistoria).
			then(function(p){
				toastr.info("Vistoria, Rejeitada")
				var tipo = 'LIBERAR';
				$state.go('vistoria.full', {tipo});
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
}		

