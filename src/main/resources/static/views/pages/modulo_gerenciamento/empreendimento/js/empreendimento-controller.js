app.controller("EmpreendimentoCadastarController", EmpreendimentoCadastarController);
app.controller("EmpreendimentoEditarController", EmpreendimentoEditarController);
app.controller("EmpreendimentoListarController", EmpreendimentoListarController);
app.controller("EmpreendimentoVisualizarController", EmpreendimentoVisualizarController);

function EmpreendimentoCadastarController (toastr, $scope, buscaCepService, $location, $state, EmpreendimentoService, blockUI){
		 
	var self = this;
	$scope.submitted = false;
	self.findCep = findCep;
	self.submit = submit;
	self.titulo = "Cadastro";
	
	
	getStatus();
	
	function findCep() {
			self.cep = self.empreendimento.endereco.cep;
				buscaCepService.get({'cep': self.cep}).$promise
				.then(function success(result){				
					self.cep = self.empreendimento.endereco = result;				
				}).catch(function error(msg) {
				});				
		    }
	
	
	 		
	function submit(){		
		if($scope.formEmpreendimento.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}			
			 blockUI.start();
	    	 EmpreendimentoService.save(self.empreendimento).
				 then(function(response){					
					 blockUI.stop();
					 toastr.success('Empreendimento', 'Cadastrada!');
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
	    	 $scope.formEmpreendimento.$setPristine();
	    	 $scope.formEmpreendimento.$setUntouched();   
	    	 self.empreendimento = null;
	    	
	     }
	     
	     function getStatus(){
	    	 EmpreendimentoService.getStatus().
	    	 then(function(e){
	    		 self.status = e;
	    	 }, function(errResponse){
	    	 });
	     };
	
  
	          	
}


function EmpreendimentoEditarController(empreendimentoPrepService, $scope, buscaCepService, $location, $stateParams, $state, EmpreendimentoService, toastr,  blockUI){
	var self = this;
	$scope.submitted = false;
	self.findCep = findCep;
	self.submit = submit;
	self.empreendimento = empreendimentoPrepService;
	self.empreendimento.dataAbertura = new Date(empreendimentoPrepService.dataAbertura);
	self.empreendimento.datafechamento = new Date(empreendimentoPrepService.datafechamento);
	
	
	self.titulo = "Edição";
	
	getStatus();
	
	function findCep(cep) {
			var numero = self.empreendimento.endereco.numero;
				buscaCepService.get({'cep': cep}).$promise
				.then(function success(result){				
					self.cep = self.empreendimento.endereco = result;	
					self.empreendimento.endereco.numero = numero;
				}).catch(function error(msg) {
				});				
		    }
	
	
	 		
	function submit(){		
		if($scope.formEmpreendimento.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}			
			 blockUI.start();
	    	 EmpreendimentoService.save(self.empreendimento).
				 then(function(response){					
					 blockUI.stop();
					 toastr.success('Empreendimento', 'Alterado!');
					 $state.go('empreendimento.consultar');
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

	   function getStatus(){
	    	 EmpreendimentoService.getStatus().
	    	 then(function(e){
	    		 self.status = e;
	    	 }, function(errResponse){
	    	 });
	     };
		

}


function EmpreendimentoListarController(EmpreendimentoService, blockUI, toastr, $scope){
	var self = this;
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	
	listar();
	

	  function listar(){
		  blockUI.start();
	    	 EmpreendimentoService.buscarPorTexto("", self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
		    		$scope.empreendimentos = e.content;	
		    		 self.totalElementos = e.totalElements;
		    		 self.totalPaginas = e.totalPages;
		    		 blockUI.stop();
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
	    	 EmpreendimentoService.buscarPorTexto(texto, self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		$scope.empreendimentos = e.content;	
	    		 self.totalElementos = e.totalElements;
	    		 self.totalPaginas = e.totalPages;
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
}

function EmpreendimentoVisualizarController($scope, $stateParams, $state, EmpreendimentoService, toastr, CredencialService, blockUI){
	var self = this;
	var idEmpreendimento = $stateParams.idEmpreendimento;	
	
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	$scope.texto = "";
	
	self.Empreendimento = null;	
	self.imprimir = imprimir;
	self.imprimirPorIds = imprimirPorIds;
	$scope.visualizarFoto = visualizarFoto;
	self.credenciaisSelecionadas = [];
	self.buscarMembroPorEmpreendimento = buscarMembroPorEmpreendimento;
	
	buscarPorId(idEmpreendimento);
	buscarMembroPorEmpreendimento(idEmpreendimento, $scope.texto, 'ATIVO');
	
	 $scope.checkAll = function(selecionado) {
		 if(selecionado === true){
			 self.credenciaisSelecionadas = self.membros.map(function(membro) { return membro.credencial.id; });
		 }else{
			 self.credenciaisSelecionadas = [];
		 }
		 
		  };
		  
		  
	  function buscarPorId(id){
	    	 EmpreendimentoService.buscarPorId(id)
	    	 .then(function(e){
	    		 self.Empreendimento = e;
	       	 }, function(errResponse){
	    	 	 });
	    	 };
	    	 
	 function buscarMembroPorEmpreendimento(id , texto, status){
		 
		 $scope.status = status;
		 blockUI.start();
		 var pagina;
		 
		 self.paginaCorrente != 0 ? pagina = self.paginaCorrente - 1:  pagina = 0;
		
    	 EmpreendimentoService.buscarMembroPorEmpreendimento(id, pagina, texto, status)
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
