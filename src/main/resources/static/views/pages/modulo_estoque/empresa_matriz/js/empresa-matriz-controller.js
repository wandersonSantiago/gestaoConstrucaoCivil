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
					 toastr.success('EmpresaMatriz', 'Cadastrada!');
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
	    	 $scope.formMatriz.$setPristine();
	    	 $scope.formMatriz.$setUntouched();   
	    	 self.dadoEmpresa = null;
	    	
	     }
	     
		  function existeCnpj(cnpj){
			  if(!cnpj){
				  return;
			  }
		    	 EmpresaMatrizService.existeCnpj(cnpj)
		    	 .then(function(e){	
		    		 e == true ? $state.go('fabricante.cadastrar',{ cnpj}) : $state.go('matriz.cadastrar',{ cnpj});	    		 
		 			}, function(errResponse){
		 				self.dadoEmpresa.cnpj = null;
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


function EmpresaMatrizEditarController($scope, buscaCepService, $location, $stateParams, $state, EmpresaMatrizService, DocumentosApresentadosService, toastr, ComumService, blockUI){
	var self = this;
	var idEmpresaMatriz = $stateParams.idEmpresaMatriz;	
	self.EmpresaMatriz = null;
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
	buscarPorId(idEmpresaMatriz);
	
	 function buscarPorId(id){
    	 EmpresaMatrizService.buscarPorId(id)
    	 .then(function(e){
    		 self.EmpresaMatriz = e;		    		 
    		 self.selecionarMatriz = e.matriz != null;
    		 self.cnpjAtual = self.EmpresaMatriz.cnpj;
    		// findCepComCidades(self.EmpresaMatriz.endereco.cep);	    		
       	 }, function(errResponse){
    	 	 });
    	 };	    	 
    	 
	$scope.$watch('entidadeCtrl.selecionarMatriz',function () {
	   	 if(!self.selecionarMatriz){
	   		self.EmpresaMatriz.matriz = null;
		 }
	 });
	
	 function submit(){
		 if($scope.formEntidade.$invalid){
				sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
				return;
			}
		 if(self.selecionarMatriz && (!self.EmpresaMatriz.matriz || !self.EmpresaMatriz.matriz.hasOwnProperty('id'))){
			 swal({ title: 'Por favor selecione a Matriz!',timer : 3000,  type : "error",padding: 20}).catch(swal.noop);
    		 return
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
	 
	 function findCep() {
			self.cep = self.EmpresaMatriz.endereco.cep;
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
	   		var endereco = {numero : self.EmpresaMatriz.endereco.numero , estado : $scope.estado ,
	   				logradouro : result.logradouro, bairro : result.bairro, cidade : $scope.cidade,
	   				cep : self.EmpresaMatriz.endereco.cep, complemento : self.EmpresaMatriz.endereco.complemento };
	   		self.EmpresaMatriz.endereco = endereco;
	   	 }, function(errResponse){
	   	 }); 
	   }
		
	 $scope.$watch('entidadeCtrl.EmpresaMatriz.endereco.cep',function () {
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
    	 EmpresaMatrizService.listarStatus().
    	 then(function(e){
    		 self.listaStatus = e;
    	 }, function(errResponse){
    	 });
     };
     
     function existeCnpj(entidade){
		  if(!entidade){
			  return;
		  }
	    	 EmpresaMatrizService.existeCnpj(entidade)
	    	 .then(function(e){	    		 
	 			}, function(errResponse){
	 				self.EmpresaMatriz.cnpj = self.cnpjAtual;
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
     	return  EmpresaMatrizService.buscarPorTexto(texto).
     	 then(function(e){
     		return e.content;
     	 }, function(errResponse){
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
	
	// listar();
	

	  function listar(){
		  blockUI.start();
	    	 EmpresaMatrizService.listar('').
	    	 then(function(e){
	    		 blockUI.stop();
	    		self.EmpresaMatrizs = e;
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

function EmpresaMatrizVisualizarController($scope, $stateParams, $state, EmpresaMatrizService, toastr, CredencialService, blockUI){
	var self = this;
	var idEmpresaMatriz = $stateParams.idEmpresaMatriz;	
	
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	$scope.texto = "";
	
	self.EmpresaMatriz = null;	
	self.imprimir = imprimir;
	self.imprimirPorIds = imprimirPorIds;
	$scope.visualizarFoto = visualizarFoto;
	self.credenciaisSelecionadas = [];
	self.buscarMembroPorEmpresaMatriz = buscarMembroPorEmpresaMatriz;
	
	buscarPorId(idEmpresaMatriz);
	buscarMembroPorEmpresaMatriz(idEmpresaMatriz, $scope.texto, 'ATIVO');
	
	 $scope.checkAll = function(selecionado) {
		 if(selecionado === true){
			 self.credenciaisSelecionadas = self.membros.map(function(membro) { return membro.credencial.id; });
		 }else{
			 self.credenciaisSelecionadas = [];
		 }
		 
		  };
		  
		  
	  function buscarPorId(id){
	    	 EmpresaMatrizService.buscarPorId(id)
	    	 .then(function(e){
	    		 self.EmpresaMatriz = e;
	       	 }, function(errResponse){
	    	 	 });
	    	 };
	    	 
	 function buscarMembroPorEmpresaMatriz(id , texto, status){
		 
		 $scope.status = status;
		 blockUI.start();
		 var pagina;
		 
		 self.paginaCorrente != 0 ? pagina = self.paginaCorrente - 1:  pagina = 0;
		
    	 EmpresaMatrizService.buscarMembroPorEmpresaMatriz(id, pagina, texto, status)
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
