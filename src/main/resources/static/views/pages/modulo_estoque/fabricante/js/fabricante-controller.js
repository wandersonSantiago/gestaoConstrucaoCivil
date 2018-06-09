app.controller("FabricanteCadastarController", FabricanteCadastarController);
app.controller("FabricanteEditarController", FabricanteEditarController);
app.controller("FabricanteListarController", FabricanteListarController);
app.controller("FabricanteVisualizarController", FabricanteVisualizarController);

function FabricanteCadastarController (toastr, $scope, $location, $state, $stateParams,  FabricanteService, blockUI){
		 
	var self = this;
	$scope.submitted = false;
	self.botao = "Cadastrar";
	self.submit = submit;
	self.empresa = $stateParams.empresa;
	
	existeEmpresaCadatrada(self.empresa.cnpj);
		 		
	function submit(){		
		if($scope.formFabricante.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}			
			 blockUI.start();
			 self.fabricante = {dadoEmpresa : self.empresa};
	    	 FabricanteService.save(self.fabricante).
				 then(function(response){					
					 blockUI.stop();
					 toastr.success('Fabricante', 'Cadastrada!');
					 clear();
					 $state.go('fabricante.consultar');
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
	    	 $scope.formFabricante.$setPristine();
	    	 $scope.formFabricante.$setUntouched();   
	    	 self.fabricante = null;	    	
	     }
	     
	  function existeEmpresaCadatrada(cnpj){
		  if(!cnpj){
			  return;
		  }
	    	 FabricanteService.buscarPorCNPJ(cnpj)
	    	 .then(function(e){
	    		if(e != null ){
	    			idFabricante = e.id;
	    			mensagem("Fabricante ja esta cadastrada", "info");
	    			$state.go('fabricante.visualizar', { idFabricante});
	    			}	    		 
	 			}, function(errResponse){
	    		 ;
	 			});
	     };
    
	     function mensagem(texto, tipo){
	    	 swal({ text : texto ,  type : tipo, width: 200, higth: 100, padding: 20}).catch(swal.noop)
	     }
  
	          	
}


function FabricanteEditarController($scope, $location, $stateParams, $state, FabricanteService, toastr, blockUI){
	var self = this;
	var idFabricante = $stateParams.idFabricante;	
	
	self.submit = submit;
	self.botao = "Editar";
	buscarPorId(idFabricante);
	
	 function buscarPorId(id){
    	 FabricanteService.buscarPorId(id)
    	 .then(function(e){
    		 self.empresa = e.dadoEmpresa;		    		    		
       	 }, function(errResponse){
    	 	 });
    	 };	    	 
    	 
	
	 function submit(){
		 if($scope.formFabricante.$invalid){
				sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
				return;
			}
		
		 blockUI.start();
		 self.fabricante = {dadoEmpresa : self.empresa};
    	 FabricanteService.alterar(self.fabricante).
			 then(function(response){
				 self.Fabricante = null;
				 toastr.success('dados alterado', 'Sucesso!!!');
				 $state.go("fabricante.listar");
				 blockUI.stop();
			 },
			function(errResponse){
				 blockUI.stop();
				 sweetAlert({text:JSON.stringify(errResponse.data), 	type : "error", timer : 100000,   width: 500,  padding: 20});
			
		 	});
	       }
	 
	
}


function FabricanteListarController(FabricanteService, blockUI, toastr, $scope){
	var self = this;
	self.Fabricantes = null;
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	
	buscarPorTexto('');
	     
	    function buscarPorTexto(texto){
	    	$scope.mensagemErro = null;
	    	 blockUI.start();
	    	 self.paginaCorrente == '0'? self.paginaCorrente = 0 : self.paginaCorrente = self.paginaCorrente - 1; 
	 		 FabricanteService.buscarPorTexto(texto, self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		self.fabricantes = e.content;	
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

function FabricanteVisualizarController($scope, $stateParams, $state, FabricanteService, toastr, blockUI){
	var self = this;
	var idFabricante = $stateParams.idFabricante;	
	
	
	buscarPorId(idFabricante);	
		  
	  function buscarPorId(id){
	    	 FabricanteService.buscarPorId(id)
	    	 .then(function(e){
	    		 self.fabricante = e;
	       	 }, function(errResponse){
	    	 	 });
	    	 };
	    	 
	  
    		
}
