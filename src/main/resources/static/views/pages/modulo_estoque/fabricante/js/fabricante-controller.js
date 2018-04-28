app.controller("FabricanteCadastarController", FabricanteCadastarController);
app.controller("FabricanteEditarController", FabricanteEditarController);
app.controller("FabricanteListarController", FabricanteListarController);
app.controller("FabricanteVisualizarController", FabricanteVisualizarController);

function FabricanteCadastarController (toastr, $scope, buscaCepService, $location, $state, FabricanteService, blockUI){
		 
	var self = this;
	$scope.submitted = false;
	self.findCep = findCep;
	self.submit = submit;
	
	
	function findCep() {
			self.cep = self.fabricante.empresa.endereco.cep;
				buscaCepService.get({'cep': self.cep}).$promise
				.then(function success(result){				
					self.cep = self.fabricante.empresa.endereco = result;				
				}).catch(function error(msg) {
				});				
		    }
	
	
	 		
	function submit(){		
		if($scope.formFabricante.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}			
			 blockUI.start();
	    	 FabricanteService.save(self.fabricante).
				 then(function(response){					
					 blockUI.stop();
					 toastr.success('Fabricante', 'Cadastrada!');
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
	    	 $scope.formFabricante.$setPristine();
	    	 $scope.formFabricante.$setUntouched();   
	    	 self.fabricante = null;
	    	
	     }
	     
	  function existeCnpj(entidade){
		  if(!entidade){
			  return;
		  }
	    	 FabricanteService.existeCnpj(entidade)
	    	 .then(function(e){	    		 
	 			}, function(errResponse){
	 				self.fabricante.cnpj = null;
	    		 swal({ text : errResponse.data.message ,  type : "info", width: 200, higth: 100, padding: 20}).catch(swal.noop);
	 			});
	     };
    
  
	          	
}


function FabricanteEditarController($scope, buscaCepService, $location, $stateParams, $state, FabricanteService, DocumentosApresentadosService, toastr, ComumService, blockUI){
	var self = this;
	var idFabricante = $stateParams.idFabricante;	
	self.Fabricante = null;
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
	buscarPorId(idFabricante);
	
	 function buscarPorId(id){
    	 FabricanteService.buscarPorId(id)
    	 .then(function(e){
    		 self.Fabricante = e;		    		 
    		 self.selecionarMatriz = e.matriz != null;
    		 self.cnpjAtual = self.Fabricante.cnpj;
    		// findCepComCidades(self.Fabricante.endereco.cep);	    		
       	 }, function(errResponse){
    	 	 });
    	 };	    	 
    	 
	$scope.$watch('entidadeCtrl.selecionarMatriz',function () {
	   	 if(!self.selecionarMatriz){
	   		self.Fabricante.matriz = null;
		 }
	 });
	
	 function submit(){
		 if($scope.formEntidade.$invalid){
				sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
				return;
			}
		 if(self.selecionarMatriz && (!self.Fabricante.matriz || !self.Fabricante.matriz.hasOwnProperty('id'))){
			 swal({ title: 'Por favor selecione a Matriz!',timer : 3000,  type : "error",padding: 20}).catch(swal.noop);
    		 return
		}
		 blockUI.start();
    	 FabricanteService.alterar(self.Fabricante).
			 then(function(response){
				 self.Fabricante = null;
				 toastr.success('dados alterado', 'Sucesso!!!');
				 $state.go("Fabricante.listar");
				 blockUI.stop();
			 },
			function(errResponse){
				 blockUI.stop();
				 sweetAlert({text:JSON.stringify(errResponse.data), 	type : "error", timer : 100000,   width: 500,  padding: 20});
			
		 	});
	       }
	 
	 function findCep() {
			self.cep = self.Fabricante.endereco.cep;
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
	   		var endereco = {numero : self.Fabricante.endereco.numero , estado : $scope.estado ,
	   				logradouro : result.logradouro, bairro : result.bairro, cidade : $scope.cidade,
	   				cep : self.Fabricante.endereco.cep, complemento : self.Fabricante.endereco.complemento };
	   		self.Fabricante.endereco = endereco;
	   	 }, function(errResponse){
	   	 }); 
	   }
		
	 $scope.$watch('entidadeCtrl.Fabricante.endereco.cep',function () {
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
    	 FabricanteService.listarStatus().
    	 then(function(e){
    		 self.listaStatus = e;
    	 }, function(errResponse){
    	 });
     };
     
     function existeCnpj(entidade){
		  if(!entidade){
			  return;
		  }
	    	 FabricanteService.existeCnpj(entidade)
	    	 .then(function(e){	    		 
	 			}, function(errResponse){
	 				self.Fabricante.cnpj = self.cnpjAtual;
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
     	return  FabricanteService.buscarPorTexto(texto).
     	 then(function(e){
     		return e.content;
     	 }, function(errResponse){
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
	
	// listar();
	

	  function listar(){
		  blockUI.start();
	    	 FabricanteService.listar('').
	    	 then(function(e){
	    		 blockUI.stop();
	    		self.Fabricantes = e;
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
	    	 FabricanteService.buscarPorTexto(texto, self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		self.Fabricantes = e.content;	
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

function FabricanteVisualizarController($scope, $stateParams, $state, FabricanteService, toastr, CredencialService, blockUI){
	var self = this;
	var idFabricante = $stateParams.idFabricante;	
	
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	$scope.texto = "";
	
	self.Fabricante = null;	
	self.imprimir = imprimir;
	self.imprimirPorIds = imprimirPorIds;
	$scope.visualizarFoto = visualizarFoto;
	self.credenciaisSelecionadas = [];
	self.buscarMembroPorFabricante = buscarMembroPorFabricante;
	
	buscarPorId(idFabricante);
	buscarMembroPorFabricante(idFabricante, $scope.texto, 'ATIVO');
	
	 $scope.checkAll = function(selecionado) {
		 if(selecionado === true){
			 self.credenciaisSelecionadas = self.membros.map(function(membro) { return membro.credencial.id; });
		 }else{
			 self.credenciaisSelecionadas = [];
		 }
		 
		  };
		  
		  
	  function buscarPorId(id){
	    	 FabricanteService.buscarPorId(id)
	    	 .then(function(e){
	    		 self.Fabricante = e;
	       	 }, function(errResponse){
	    	 	 });
	    	 };
	    	 
	 function buscarMembroPorFabricante(id , texto, status){
		 
		 $scope.status = status;
		 blockUI.start();
		 var pagina;
		 
		 self.paginaCorrente != 0 ? pagina = self.paginaCorrente - 1:  pagina = 0;
		
    	 FabricanteService.buscarMembroPorFabricante(id, pagina, texto, status)
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
