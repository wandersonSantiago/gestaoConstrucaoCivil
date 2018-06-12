app.controller("EmpresaMatrizCadastarController", EmpresaMatrizCadastarController);
app.controller("EmpresaMatrizEditarController", EmpresaMatrizEditarController);
app.controller("EmpresaMatrizListarController", EmpresaMatrizListarController);
app.controller("EmpresaMatrizVisualizarController", EmpresaMatrizVisualizarController);

function EmpresaMatrizCadastarController (toastr, $scope, buscaCepService, $location, $state, $stateParams, EmpresaMatrizService, blockUI){
		 
	var self = this;
	$scope.submitted = false;
	self.findCep = findCep;
	self.submit = submit;
	self.existeCnpj = existeCnpj;
	self.cnpj = $stateParams.cnpj;
	self.proximaPagina = $stateParams.proximaPagina;
	
	uf();
	status();
	
	
	function findCep() {
			self.cep = self.dadoEmpresa.endereco.cep;
				buscaCepService.get({'cep': self.cep}).$promise
				.then(function success(result){				
					self.cep = self.dadoEmpresa.endereco = result;				
				}).catch(function error(msg) {
				});				
		    }
	
	
	 		
	function submit(){		
		if($scope.formMatriz.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}			
			 blockUI.start();
			 self.dadoEmpresa.cnpj = self.cnpj;
	    	 EmpresaMatrizService.save(self.dadoEmpresa).
				 then(function(response){					
					 blockUI.stop();
					 toastr.success('Empresa', 'Cadastrada!');
					 clear();
					 $state.go(self.proximaPagina,{response});
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
	    	 $scope.formMatriz.$setPristine();
	    	 $scope.formMatriz.$setUntouched();   
	    	 self.dadoEmpresa = null;
	    	
	     }
	     
		  function existeCnpj(cnpj){
			  if(!cnpj){
				  return;
			  }
		    	 EmpresaMatrizService.buscarPorCnpj(cnpj)
		    	 .then(function(empresa){	
		    		 empresa != ""  ? $state.go(self.proximaPagina,{ empresa}) : $state.go('matriz.cadastrar',{ cnpj});	    		 
		 			}, function(errResponse){		 				
		    		 swal({ text : errResponse.data.message ,  type : "info", width: 200, higth: 100, padding: 20}).catch(swal.noop);
		 			});
		     };
	    
	     function status(){			 
		    	 EmpresaMatrizService.status()
		    	 .then(function(e){	
		    		 self.status = e;
		 			}, function(errResponse){
		    		 swal({ text : errResponse.data.message ,  type : "info", width: 200, higth: 100, padding: 20}).catch(swal.noop);
		 			});
		     };
		     
	     function uf(){			 
	    	 EmpresaMatrizService.uf()
	    	 .then(function(e){	
	    		 self.ufs = e;
	 			}, function(errResponse){
	    		 swal({ text : errResponse.data.message ,  type : "info", width: 200, higth: 100, padding: 20}).catch(swal.noop);
	 			});
	     };    
  
	          	
}


function EmpresaMatrizEditarController($scope, buscaCepService, $location, $stateParams, $state, EmpresaMatrizService, toastr, blockUI){
	var self = this;
	var idEmpresaMatriz = $stateParams.idEmpresaMatriz;	
	
	buscarPorId(idEmpresaMatriz);
	
	 function buscarPorId(id){
    	 EmpresaMatrizService.buscarPorId(id)
    	 .then(function(e){
    		 self.EmpresaMatriz = e;		    		 	    		
       	 }, function(errResponse){
    	 	 });
    	 };	    	 
    	 
	
	 function submit(){
		 if($scope.formEntidade.$invalid){
				sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
				return;
			}		 
		 blockUI.start();
    	 EmpresaMatrizService.alterar(self.EmpresaMatriz).
			 then(function(response){
				 self.EmpresaMatriz = null;
				 toastr.success('dados alterado', 'Sucesso!!!');
				 $state.go("EmpresaMatriz.listar");
				 blockUI.stop();
			 },
			function(errResponse){
				 blockUI.stop();
				 sweetAlert({text:JSON.stringify(errResponse.data), 	type : "error", timer : 100000,   width: 500,  padding: 20});
			
		 	});
	       }
	 
	
}


function EmpresaMatrizListarController(EmpresaMatrizService, blockUI, toastr, $scope){
	var self = this;
	self.EmpresaMatrizs = null;
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	
	buscarPorTexto('');
	     
	    function buscarPorTexto(texto){
	    	$scope.mensagemErro = null;
	    	 
	    	 blockUI.start();
	    	 EmpresaMatrizService.buscarPorTexto(texto, self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		self.EmpresaMatrizs = e.content;	
	    		 self.totalElementos = e.totalElements;
	    		 self.totalPaginas = e.totalPages;
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

function EmpresaMatrizVisualizarController($scope, $stateParams, $state, EmpresaMatrizService, toastr, blockUI){
	
}
