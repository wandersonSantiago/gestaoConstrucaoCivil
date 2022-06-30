app.controller("PrestadoraCadastarController", PrestadoraCadastarController);
app.controller("PrestadoraEditarController", PrestadoraEditarController);
app.controller("PrestadoraListarController", PrestadoraListarController);
app.controller("PrestadoraVisualizarController", PrestadoraVisualizarController);

function PrestadoraCadastarController (toastr, $scope, $location, $state, $stateParams,  PrestadoraService, blockUI){
		 
	var self = this;
	$scope.submitted = false;
	self.botao = "Cadastrar";
	self.submit = submit;
	self.empresa = $stateParams.empresa;
	
	existeEmpresaCadatrada(self.empresa.cnpj);
		 		
	function submit(){		
		if($scope.formPrestadora.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}			
			 blockUI.start();
			 self.prestadora = {dadoEmpresa : self.empresa};
	    	 PrestadoraService.save(self.prestadora).
				 then(function(response){					
					 blockUI.stop();
					 toastr.success('Prestadora', 'Cadastrada!');
					 clear();
					 $state.go('prestadora.consultar');
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
					 mensagem(erros, "error");										 
			 	});
		 }

	     function clear(){	    	 
	    	 $scope.formPrestadora.$setPristine();
	    	 $scope.formPrestadora.$setUntouched();   
	    	 self.prestadora = null;	    	
	     }
	     
	  function existeEmpresaCadatrada(cnpj){
		  if(!cnpj){
			  return;
		  }
	    	 PrestadoraService.buscarPorCNPJ(cnpj)
	    	 .then(function(e){
	    		if(e != null ){
	    			idPrestadora = e.id;
	    			mensagem("Prestadora ja esta cadastrada", "info");
	    			$state.go('prestadora.visualizar', { idPrestadora});
	    			}	else{
	    				self.prestadora = {dadoEmpresa : self.empresa};
	    			}    		 
	 			}, function(errResponse){
	 				var empresa = self.empresa;
	 				$state.go('prestadora.cadastrar', {empresa});
	    		 ;
	 			});
	     };
    
	     function mensagem(texto, tipo){
	    	 swal({ text : texto ,  type : tipo, width: 200, higth: 100, padding: 20}).catch(swal.noop)
	     }
  
	          	
}


function PrestadoraEditarController($scope, $location, $stateParams, $state, PrestadoraService, toastr, blockUI){
	var self = this;
	var idPrestadora = $stateParams.idPrestadora;	
	
	self.submit = submit;
	self.botao = "Editar";
	buscarPorId(idPrestadora);
	
	 function buscarPorId(id){
    	 PrestadoraService.buscarPorId(id)
    	 .then(function(e){
    		 self.prestadora = e;		    		    		
       	 }, function(errResponse){
    	 	 });
    	 };	    	 
    	 
	
	 function submit(){
		 if($scope.formPrestadora.$invalid){
				sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
				return;
			}
		
		 blockUI.start();
    	 PrestadoraService.alterar(self.prestadora).
			 then(function(response){
				 self.Prestadora = null;
				 toastr.success('dados alterado', 'Sucesso!!!');
				 $state.go("prestadora.consultar");
				 blockUI.stop();
			 },
			function(errResponse){
				 blockUI.stop();
				 sweetAlert({text:JSON.stringify(errResponse.data), 	type : "error", timer : 100000,   width: 500,  padding: 20});
			
		 	});
	       }
	 
	
}


function PrestadoraListarController(PrestadoraService, blockUI, toastr, $scope){
	var self = this;
	self.Prestadoras = null;
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	
	buscarPorTexto('');
	     
	    function buscarPorTexto(texto){
	    	$scope.mensagemErro = null;
	    	 blockUI.start();
	    	 self.paginaCorrente == '0'? self.paginaCorrente = 0 : self.paginaCorrente = self.paginaCorrente - 1; 
	 		 PrestadoraService.buscarPorTexto(texto, self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		self.prestadoras = e.content;	
	    		 self.totalElementos = e.totalElements;
	    		 self.totalPaginas = e.totalPages;
	    		 self.paginaCorrente = e.number + 1;
	    		 blockUI.stop();
	    	 }, function(errResponse){
	    		 blockUI.stop();
	    		 if(errResponse.status == 404){
	    			 toastr.error('Nenhum registro encontrado', 'Not found!');
	    			 $scope.mensagemErro = errResponse.data.message;
	    		 }else{
	    		    swal({ text : errResponse.data.message ,  type : "info", width: 200, higth: 100, padding: 20}).catch(swal.noop);
	    		 }
			 });
	    }
}

function PrestadoraVisualizarController($scope, $stateParams, $state, PrestadoraService, toastr, blockUI){
	var self = this;
	var idPrestadora = $stateParams.idPrestadora;	
	
	
	buscarPorId(idPrestadora);	
		  
	  function buscarPorId(id){
	    	 PrestadoraService.buscarPorId(id)
	    	 .then(function(e){
	    		 self.prestadora = e;
	       	 }, function(errResponse){
	    	 	 });
	    	 };
	    	 
	  
    		
}
