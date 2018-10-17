app.controller("PacoteCadastarController", PacoteCadastarController);
app.controller("PacoteEditarController", PacoteEditarController);
app.controller("PacoteListarController", PacoteListarController);
app.controller("PacoteVisualizarController", PacoteVisualizarController);

function PacoteCadastarController (toastr, $scope, $location, $localStorage, $state, $stateParams,  PacoteService, CategoriaService, SubCategoriaService, blockUI){
		 
	var self = this;
	self.botao = "Cadastrar";
	self.submit = submit;	
		 		
	self.subCategoria = subCategoria;
	self.categoria = categoria;
	
	$scope.backPage = $stateParams.backPage;
	self.proximaPagina = proximaPagina;
	
	if($localStorage.pacote){
		self.pacote = $localStorage.pacote;
	}
	
	function proximaPagina(proxima){
		var backPage = 'pacote.cadastrar';
		$state.go(proxima ,{backPage});
		$localStorage.pacote = self.pacote;
	}
	
	function submit(form){		
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}			
			 blockUI.start();
	    	 PacoteService.save(self.pacoteServico).
				 then(function(response){					
					 blockUI.stop();
					 toastr.success('Pacote', 'Cadastrada!');
					 
					 if($scope.backPage){
							$state.go($scope.backPage);
						}
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
					 mensagem(erros, "error");										 
			 	});
		 }

	     function clear(){	    	 
	    	 $scope.form.$setPristine();
	    	 $scope.form.$setUntouched();   
	    	 self.pacote = null;	    	
	     }
	     
	     function subCategoria(idCategoria){
	 		if(!idCategoria){
	 			return
	 		}
	 		self.subCategorias = null;
	 		SubCategoriaService.findByCategoriaId(idCategoria).then(function(c){
	 			self.subCategorias = c;
	 		},function(erro){
	 		})
	 	 };
	 			 
	 	 function categoria(texto){
	 	     	return  CategoriaService.findByDescricao(texto).
	 	     	 then(function(e){
	 	     		return e.content;
	 	     	 }, function(errResponse){
	 	     	 });
	 	     } ; 	
	 	 
	 	    function categoria(texto){
	 	     	return  CategoriaService.findByDescricao(texto).
	 	     	 then(function(e){
	 	     		return e.content;
	 	     	 }, function(errResponse){
	 	     	 });
	 	     } ; 	
    
	 	     
	     function mensagem(texto, tipo){
	    	 swal({ text : texto ,  type : tipo, width: 200, higth: 100, padding: 20}).catch(swal.noop)
	     }
  
	          	
}


function PacoteEditarController($scope, $location, $stateParams, $state, PacoteService, toastr, blockUI, CategoriaService, SubCategoriaService){
	var self = this;
	var idPacote = $stateParams.idPacote;	
	
	self.subCategoria = subCategoria;
	self.categoria = categoria;
	
	self.submit = submit;
	self.botao = "Editar";
	buscarPorId(idPacote);
	
	 function buscarPorId(id){
    	 PacoteService.buscarPorId(id)
    	 .then(function(e){
    		 self.pacoteServico = e;	
       	 }, function(errResponse){
    	 	 });
    	 };	    	 
    	 
	
	 function submit(){
		 if($scope.form.$invalid){
				sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
				return;
			}
		
		 blockUI.start();
    	 PacoteService.alterar(self.pacoteServico).
			 then(function(response){
				 self.Pacote = null;
				 toastr.success('dados alterado', 'Sucesso!!!');
				 $state.go("pacote.consultar");
				 blockUI.stop();
			 },
			function(errResponse){
				 blockUI.stop();
				 sweetAlert({text:JSON.stringify(errResponse.data), 	type : "error", timer : 100000,   width: 500,  padding: 20});
			
		 	});
	       }
	 function subCategoria(idCategoria){
	 		if(!idCategoria){
	 			return
	 		}
	 		self.subCategorias = null;
	 		SubCategoriaService.findByCategoriaId(idCategoria).then(function(c){
	 			self.subCategorias = c;
	 		},function(erro){
	 		})
	 	 };
	 			 
	 	 function categoria(texto){
	 	     	return  CategoriaService.findByDescricao(texto).
	 	     	 then(function(e){
	 	     		return e.content;
	 	     	 }, function(errResponse){
	 	     	 });
	 	     } ; 	
	 	 
	
}


function PacoteListarController(PacoteService, blockUI, toastr, $scope){
	var self = this;
	self.Pacotes = null;
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	
	buscarPorTexto('');
	     
	    function buscarPorTexto(texto){
	    	$scope.mensagemErro = null;
	    	 blockUI.start();
	    	 self.paginaCorrente == '0'? self.paginaCorrente = 0 : self.paginaCorrente = self.paginaCorrente - 1; 
	 		 PacoteService.buscarPorTexto(texto, self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		self.pacotes = e.content;	
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

function PacoteVisualizarController($scope, $stateParams, $state, PacoteService, toastr, blockUI){
	var self = this;
	var idPacote = $stateParams.idPacote;	
	
	
	buscarPorId(idPacote);	
		  
	  function buscarPorId(id){
	    	 PacoteService.buscarPorId(id)
	    	 .then(function(e){
	    		 self.pacote = e;
	       	 }, function(errResponse){
	    	 	 });
	    	 };
	    	 
	  
    		
}
