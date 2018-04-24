app.controller("FornecedorCadastarController", FornecedorCadastarController);
app.controller("FornecedorEditarController", FornecedorEditarController);
app.controller("FornecedorListarController", FornecedorListarController);
app.controller("FornecedorVisualizarController", FornecedorVisualizarController);

function FornecedorCadastarController (toastr, $scope, buscaCepService, $location, $state, FornecedorService, blockUI){
		 
	var self = this;
	$scope.submitted = false;
	self.findCep = findCep;
	self.submit = submit;
	
	
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
	    	 $scope.formFornecedor.$setPristine();
	    	 $scope.formFornecedor.$setUntouched();   
	    	 self.fornecedor = null;
	    	
	     }
	     
	  function existeCnpj(entidade){
		  if(!entidade){
			  return;
		  }
	    	 FornecedorService.existeCnpj(entidade)
	    	 .then(function(e){	    		 
	 			}, function(errResponse){
	 				self.fornecedor.cnpj = null;
	    		 swal({ text : errResponse.data.message ,  type : "info", width: 200, higth: 100, padding: 20}).catch(swal.noop);
	 			});
	     };
    
  
	          	
}


function FornecedorEditarController($scope, buscaCepService, $location, $stateParams, $state, FornecedorService, DocumentosApresentadosService, toastr, ComumService, blockUI){
	var self = this;
	var idFornecedor = $stateParams.idFornecedor;	
	self.Fornecedor = null;
	$scope.submitted = false;
	self.listaStatus = null;
	self.listaDocumentosApresentados = [];
	self.submit = submit;
	self.listarCidadePorEstados = listarCidadePorEstados;
	self.buscarPorTexto = buscarPorTexto;
	self.selecionarMatriz = false;
	self.existeCnpj = existeCnpj;
	self.findCep = findCep;
	
	listarStatus();
	listarDocumentos();
	listarEstados();
	buscarPorId(idFornecedor);
	
	 function buscarPorId(id){
    	 FornecedorService.buscarPorId(id)
    	 .then(function(e){
    		 self.Fornecedor = e;		    		 
    		 self.selecionarMatriz = e.matriz != null;
    		 self.cnpjAtual = self.Fornecedor.cnpj;
    		// findCepComCidades(self.Fornecedor.endereco.cep);	    		
       	 }, function(errResponse){
    	 	 });
    	 };	    	 
    	 
	$scope.$watch('entidadeCtrl.selecionarMatriz',function () {
	   	 if(!self.selecionarMatriz){
	   		self.Fornecedor.matriz = null;
		 }
	 });
	
	 function submit(){
		 if($scope.formEntidade.$invalid){
				sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
				return;
			}
		 if(self.selecionarMatriz && (!self.Fornecedor.matriz || !self.Fornecedor.matriz.hasOwnProperty('id'))){
			 swal({ title: 'Por favor selecione a Matriz!',timer : 3000,  type : "error",padding: 20}).catch(swal.noop);
    		 return
		}
		 blockUI.start();
    	 FornecedorService.alterar(self.Fornecedor).
			 then(function(response){
				 self.Fornecedor = null;
				 toastr.success('dados alterado', 'Sucesso!!!');
				 $state.go("Fornecedor.listar");
				 blockUI.stop();
			 },
			function(errResponse){
				 blockUI.stop();
				 sweetAlert({text:JSON.stringify(errResponse.data), 	type : "error", timer : 100000,   width: 500,  padding: 20});
			
		 	});
	       }
	 
	 function findCep() {
			self.cep = self.Fornecedor.endereco.cep;
				buscaCepService.get({'cep': self.cep}).$promise
				.then(function success(result){				
					for(i = 0 ; i < self.estados.length ; i++){
						if(self.estados[i].sigla == result.uf){
							$scope.estado = self.estados[i];
							listarCidadePorEstados($scope.estado.id, result);
						}
					}						
				}).catch(function error(msg) {
				});				
		    }
	
	var specialChars = [
		
		{val:"A",let:"ÁÂÃ"},
		{val:"E",let:"ÉÊ"},
		{val:"I",let:"Í"},
		{val:"O",let:"ÔÓ"},
		{val:"U",let:"Ú"},
		{val:"C",let:"Ç"}
	];
	
	function replaceSpecialChars(str) {
		var $spaceSymbol = '-';
		var regex;
		var returnString = str;
		for (var i = 0; i < specialChars.length; i++) {	
			regex = new RegExp("["+specialChars[i].let+"]", "g");
			returnString = returnString.replace(regex, specialChars[i].val);
			regex = null;
		}
		return returnString.replace(/\s/g,$spaceSymbol);
	};
	
	 function listarCidadePorEstados(id, result){
	   	 ComumService.listarCidadesPorEstado(id).
	   	 then(function(d){
	   		self.cidades = d;
	   		city = replaceSpecialChars(result.localidade.toUpperCase());
	   		for(i = 0 ; i < self.cidades.length ; i++){
					if(self.cidades[i].nome == city){
						$scope.cidade = self.cidades[i];
					}
				}
	   		var endereco = {numero : self.Fornecedor.endereco.numero , estado : $scope.estado ,
	   				logradouro : result.logradouro, bairro : result.bairro, cidade : $scope.cidade,
	   				cep : self.Fornecedor.endereco.cep, complemento : self.Fornecedor.endereco.complemento };
	   		self.Fornecedor.endereco = endereco;
	   	 }, function(errResponse){
	   	 }); 
	   }
		
	 $scope.$watch('entidadeCtrl.Fornecedor.endereco.cep',function () {
		 findCep();
	 });
	
	 
	 function findCepComCidades(cep) {
			self.cep = cep;
				buscaCepService.get({'cep': self.cep}).$promise
				.then(function success(result){				
					for(i = 0 ; i < self.estados.length ; i++){
						if(self.estados[i].sigla == result.uf){
							$scope.estado = self.estados[i];
							listarCidadePorEstados($scope.estado.id, result);
						}
					}						
				}).catch(function error(msg) {
				});				
		    }
	 
	 
	 
	 

	  
	  function listarStatus(){
    	 FornecedorService.listarStatus().
    	 then(function(e){
    		 self.listaStatus = e;
    	 }, function(errResponse){
    	 });
     };
     
     function existeCnpj(entidade){
		  if(!entidade){
			  return;
		  }
	    	 FornecedorService.existeCnpj(entidade)
	    	 .then(function(e){	    		 
	 			}, function(errResponse){
	 				self.Fornecedor.cnpj = self.cnpjAtual;
	    		 swal({ text : errResponse.data.message ,  type : "info", width: 200, higth: 100, padding: 20}).catch(swal.noop);
	 			});
	     };
	     
     function listarDocumentos(){
    	 DocumentosApresentadosService.listarDocumentosEntidade().
    	 then(function(d){
    		self.listaDocumentosApresentados = d;
    	 }, function(errResponse){
    	 });
     };
     
     
     function listarEstados(){
    	 ComumService.estados().
    	 then(function(d){
    		self.estados = d;
    	 }, function(errResponse){
    	 }); 
    	 
     };
       
     
     function buscarPorTexto(texto){
     	return  FornecedorService.buscarPorTexto(texto).
     	 then(function(e){
     		return e.content;
     	 }, function(errResponse){
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
	
	// listar();
	

	  function listar(){
		  blockUI.start();
	    	 FornecedorService.listar('').
	    	 then(function(e){
	    		 blockUI.stop();
	    		self.Fornecedors = e;
	    	 }, function(errResponse){
	    		 blockUI.stop();
	    	 });
	     };
	     
	     
	    function buscarPorTexto(texto){
	    	$scope.mensagemErro = null;
	    	 if(!texto || texto.length < 3){
	    		 $scope.mensagemErro = "Digite pelo menos 3 caracters";
	    		 return;
	    	 }
	    	 blockUI.start();
	    	 FornecedorService.buscarPorTexto(texto, self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		self.Fornecedors = e.content;	
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

function FornecedorVisualizarController($scope, $stateParams, $state, FornecedorService, toastr, CredencialService, blockUI){
	var self = this;
	var idFornecedor = $stateParams.idFornecedor;	
	
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	$scope.texto = "";
	
	self.Fornecedor = null;	
	self.imprimir = imprimir;
	self.imprimirPorIds = imprimirPorIds;
	$scope.visualizarFoto = visualizarFoto;
	self.credenciaisSelecionadas = [];
	self.buscarMembroPorFornecedor = buscarMembroPorFornecedor;
	
	buscarPorId(idFornecedor);
	buscarMembroPorFornecedor(idFornecedor, $scope.texto, 'ATIVO');
	
	 $scope.checkAll = function(selecionado) {
		 if(selecionado === true){
			 self.credenciaisSelecionadas = self.membros.map(function(membro) { return membro.credencial.id; });
		 }else{
			 self.credenciaisSelecionadas = [];
		 }
		 
		  };
		  
		  
	  function buscarPorId(id){
	    	 FornecedorService.buscarPorId(id)
	    	 .then(function(e){
	    		 self.Fornecedor = e;
	       	 }, function(errResponse){
	    	 	 });
	    	 };
	    	 
	 function buscarMembroPorFornecedor(id , texto, status){
		 
		 $scope.status = status;
		 blockUI.start();
		 var pagina;
		 
		 self.paginaCorrente != 0 ? pagina = self.paginaCorrente - 1:  pagina = 0;
		
    	 FornecedorService.buscarMembroPorFornecedor(id, pagina, texto, status)
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
