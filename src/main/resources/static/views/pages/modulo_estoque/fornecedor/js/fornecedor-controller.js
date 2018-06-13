app.controller("FornecedorCadastarController", FornecedorCadastarController);
app.controller("FornecedorEditarController", FornecedorEditarController);
app.controller("FornecedorListarController", FornecedorListarController);
app.controller("FornecedorVisualizarController", FornecedorVisualizarController);

function FornecedorCadastarController ($stateParams, toastr, $scope, buscaCepService, $location, $state, FornecedorService, blockUI){
		 
	var self = this;
	$scope.submitted = false;
	self.findCep = findCep;
	self.submit = submit;
	self.botao = "Cadastrar";
	self.empresa = $stateParams.empresa;
	
	existeEmpresaCadatrada(self.empresa.cnpj);
	
	
	function findCep() {
			self.cep = self.fornecedor.empresa.endereco.cep;
				buscaCepService.get({'cep': self.cep}).$promise
				.then(function success(result){				
					self.cep = self.fornecedor.empresa.endereco = result;				
				}).catch(function error(msg) {
				});				
		    }
	
	
	 		
	function submit(){		
		if($scope.formFornecedor.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}			
			 blockUI.start();
	    	 FornecedorService.save(self.fornecedor).
				 then(function(response){					
					 blockUI.stop();
					 toastr.success('Fornecedor', 'Cadastrada!');
					 clear();
					 $state.go('fornecedor.consultar');
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
	    	 $scope.formFornecedor.$setPristine();
	    	 $scope.formFornecedor.$setUntouched();   
	    	 self.fornecedor = null;
	    	
	     }
	     function existeEmpresaCadatrada(cnpj){
			  if(!cnpj){
				  return;
			  }
			  FornecedorService.buscarPorCNPJ(cnpj)
		    	 .then(function(e){
		    		if(e != null ){
		    			idFornecedor = e.id;
		    			mensagem("Fornecedor ja esta cadastrada", "info");
		    			$state.go('fornecedor.visualizar', { idFornecedor});
		    			}else{
		    				self.fornecedor= {dadoEmpresa : self.empresa};
		    			}	    		 
		 			}, function(errResponse){
		 				var empresa = self.empresa;
		 				$state.go('fornecedor.cadastrar', {empresa});
		    		 ;
		 			});
		     };
	    
		     function mensagem(texto, tipo){
		    	 swal({ text : texto ,  type : tipo, width: 200, higth: 100, padding: 20}).catch(swal.noop)
		     }
    
  
	          	
}

function FornecedorEditarController($scope, $location, $stateParams, $state, FornecedorService, toastr, blockUI){
	var self = this;
	var idFornecedor = $stateParams.idFornecedor;	
	
	self.submit = submit;
	self.botao = "Editar";
	buscarPorId(idFornecedor);
	
	 function buscarPorId(id){
    	 FornecedorService.buscarPorId(id)
    	 .then(function(e){
    		 self.fornecedor = e;
       	 }, function(errResponse){
    	 	 });
    	 };	    	 
    	 
	
	 function submit(){
		 if($scope.formFornecedor.$invalid){
				sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
				return;
			}		
		 blockUI.start();
    	 FornecedorService.alterar(self.fornecedor).
			 then(function(response){
				 self.Fornecedor = null;
				 toastr.success('dados alterado', 'Sucesso!!!');
				 $state.go("fornecedor.consultar");
				 blockUI.stop();
			 },
			function(errResponse){
				 blockUI.stop();
				 sweetAlert({text:JSON.stringify(errResponse.data), 	type : "error", timer : 100000,   width: 500,  padding: 20});			
		 	});
	       }
	 
	
}


function FornecedorListarController(FornecedorService, blockUI, toastr, $scope){
	var self = this;
	self.Fornecedors = null;
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	
	buscarPorTexto('');
	     
	    function buscarPorTexto(texto){
	    	$scope.mensagemErro = null;
	    	 blockUI.start();
	    	 self.paginaCorrente == '0'? self.paginaCorrente = 0 : self.paginaCorrente = self.paginaCorrente - 1; 
	 		 FornecedorService.buscarPorTexto(texto, self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		self.fornecedores = e.content;	
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

function FornecedorVisualizarController($scope, $stateParams, $state, FornecedorService, toastr, blockUI){
	var self = this;
	var idFornecedor = $stateParams.idFornecedor;	
	
	
	buscarPorId(idFornecedor);	
		  
	  function buscarPorId(id){
	    	 FornecedorService.buscarPorId(id)
	    	 .then(function(e){
	    		 self.fornecedor = e;
	       	 }, function(errResponse){
	    	 	 });
	    	 };
	    	 
	  
    		
}
