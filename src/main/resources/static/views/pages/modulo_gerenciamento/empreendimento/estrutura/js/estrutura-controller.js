app.controller("EstruturaCadastarController", EstruturaCadastarController);
app.controller("EstruturaVisualizarController", EstruturaVisualizarController);

function EstruturaCadastarController ( $uibModal,toastr, $scope, $location, $state, EstruturaService, blockUI){
		 
	var self = this;
	$scope.submitted = false;
	self.submit = submit;
	self.editar = editar;
	self.listaRaiz = listaRaiz;
	self.buscarFilhas = buscarFilhas;
	self.removerFilhas = removerFilhas;
	self.titulo = "Inicial";
	$scope.estruturaGeral = [];
	buscarListaFilhas(0);	 	
	
	function buscarFilhas(estrutura){
		$scope.estruturaGeral.push(estrutura);
		$scope.raiz = estrutura;
		self.titulo = estrutura.descricao;
		buscarListaFilhas(estrutura.id);
	}
	
	function removerFilhas(estrutura){		
		$scope.estruturaGeral.splice($scope.estruturaGeral.indexOf(estrutura),$scope.estruturaGeral.length - $scope.estruturaGeral.indexOf(estrutura));
		buscarFilhas(estrutura);		
	}

	function listaRaiz(zero){
		self.titulo ="Inicial";
		$scope.estruturaGeral = [];
		buscarListaFilhas(zero);
	}
	
	function editar(estrutura){
		$('.modalEstrutura').modal('show');
		self.estrutura = estrutura;
		$scope.raiz = null;
		$scope.estruturas.splice($scope.estruturas.indexOf(estrutura),1);
		
	}
	
	function submit(){		
		if($scope.formEstrutura.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}	
		if($scope.raiz){
			self.estrutura.raiz = $scope.raiz;
		}
			 blockUI.start();
	    	 EstruturaService.save(self.estrutura).
				 then(function(response){					
					 blockUI.stop();
					 toastr.success('Estrutura', 'Cadastrada!');
					 clear();
					 $('.modalEstrutura').modal('hide');
					 $scope.estruturas.push(response);
				 },
				function(errResponse){					 
					 blockUI.stop();
					 if(errResponse.data.errors != null){
						 for (var i = 0; i < errResponse.data.errors.length; i++) {
								var erros = errResponse.data.errors[i].defaultMessage
							}
					 }else{
						 var erros = errResponse.data.message;
					 }					
					 sweetAlert({text: erros, 	type : "error", timer : 100000,   width: 500,  padding: 20});
										 
			 	});
		 }

	     function clear(){	    	 
	    	 $scope.formEstrutura.$setPristine();
	    	 $scope.formEstrutura.$setUntouched();   
	    	 self.estrutura = null;
	    	
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



function EstruturaVisualizarController($scope, $stateParams, $state, EstruturaService, toastr, CredencialService, blockUI){
	var self = this;
	var idEstrutura = $stateParams.idEstrutura;	
	
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	$scope.texto = "";
	
	self.Estrutura = null;	
	self.imprimir = imprimir;
	self.imprimirPorIds = imprimirPorIds;
	$scope.visualizarFoto = visualizarFoto;
	self.credenciaisSelecionadas = [];
	self.buscarMembroPorEstrutura = buscarMembroPorEstrutura;
	
	buscarPorId(idEstrutura);
	buscarMembroPorEstrutura(idEstrutura, $scope.texto, 'ATIVO');
	
	 $scope.checkAll = function(selecionado) {
		 if(selecionado === true){
			 self.credenciaisSelecionadas = self.membros.map(function(membro) { return membro.credencial.id; });
		 }else{
			 self.credenciaisSelecionadas = [];
		 }
		 
		  };
		  
		  
	  function buscarPorId(id){
	    	 EstruturaService.buscarPorId(id)
	    	 .then(function(e){
	    		 self.Estrutura = e;
	       	 }, function(errResponse){
	    	 	 });
	    	 };
	    	 
	 function buscarMembroPorEstrutura(id , texto, status){
		 
		 $scope.status = status;
		 blockUI.start();
		 var pagina;
		 
		 self.paginaCorrente != 0 ? pagina = self.paginaCorrente - 1:  pagina = 0;
		
    	 EstruturaService.buscarMembroPorEstrutura(id, pagina, texto, status)
    	 .then(function(e){    		
    		 self.membros = e.content;	
    		 self.totalElementos = e.totalElements;
    		 self.totalPaginas = e.totalPages;
    		 self.size =  e.size;    		 
    		
    		 visualizarFoto(self.membros[0]);
    		
    		 blockUI.stop();
       	 }, function(errResponse){
    	 	 });
    	 };  	 
    	 
    	 function visualizarFoto(membro){
    		 $scope.membro = membro;
    	 }
    	 
    	 function imprimirPorIds(){
        	 if(!self.credenciaisSelecionadas.length) return;
        	 
        	 blockUI.start();
      		CredencialService.imprimirPorIds(self.credenciaisSelecionadas)
         	 .then(function(d){
         		var file = new Blob([d],{type: 'application/pdf'});
         		var fileURL = URL.createObjectURL(file);
         		blockUI.stop();
         	    window.open(fileURL);
         	 	 },function(errResponse){	
         	 		blockUI.stop();
      				 swal({ timer : 3000, text : errResponse.data.message ,  type : "error", width: 200, higth: 100, padding: 20}).catch(swal.noop);
      		 	});
         }
    	 
    	 function imprimir(id){
    		 blockUI.start();
    			CredencialService.imprimirPorEntidade(id)
    	   	 .then(function(d){
    	   		 blockUI.stop();
    	   		var file = new Blob([d],{type: 'application/pdf'});
    	   		var fileURL = URL.createObjectURL(file);
    	   	    window.open(fileURL);
    	   	 	 },function(errResponse){	
    	   	 	 blockUI.stop();
    					 swal({ timer : 3000, text : errResponse.data.message ,  type : "error", width: 200, higth: 100, padding: 20}).catch(swal.noop);
    			 	});
    		}
    		
}
