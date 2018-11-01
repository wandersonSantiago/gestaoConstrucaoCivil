app.controller("EmpreendimentoCadastarController", EmpreendimentoCadastarController);
app.controller("EmpreendimentoEditarController", EmpreendimentoEditarController);
app.controller("EmpreendimentoListarController", EmpreendimentoListarController);
app.controller("EmpreendimentoShowController", EmpreendimentoShowController);
app.controller("EmpreendimentoConfigController", EmpreendimentoConfigController);

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

function EmpreendimentoShowController($scope, $stateParams, $state, EmpreendimentoService, toastr, blockUI){
	var self = this;
	var idEmpreendimento = $stateParams.idEmpreendimento;	
	
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	$scope.texto = "";
	
	self.Empreendimento = null;	
	
	buscarPorId(idEmpreendimento);	
		  
		  
	  function buscarPorId(id){
	    	 EmpreendimentoService.buscarPorId(id)
	    	 .then(function(e){
	    		 self.empreendimento = e;
	       	 }, function(errResponse){
	    	 	 });
	    	 };	
    	 
    	
    	 
    	
    		
}

function EmpreendimentoConfigController($scope, $stateParams, $state, EmpreendimentoService, toastr, blockUI){
	var self = this;
	var idEmpreendimento = $stateParams.idEmpreendimento;	
	$scope.submitted = false;
	self.submit = submit;
	buscarPorId(idEmpreendimento);	
	dataBase();	  
	  
    	 function buscarPorId(id){
	    	 EmpreendimentoService.findByConfigEmpreendimentoId(id)
	    	 .then(function(e){
	    		 self.configuracaoEmpreendimento = e;
	       	 }, function(errResponse){
	    	 	 });
	    	 };	
    		
    	 function dataBase(){
	    	 EmpreendimentoService.dataBase()
	    	 .then(function(e){
	    		 self.dataBase = e;
	       	 }, function(errResponse){
	    	 	 });
	    	 };	
	    	 
	    	 
	    	 function submit(form){		
	    			if(form.$invalid){
	    				sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
	    				return;
	    			}			
				 blockUI.start();
		    	 EmpreendimentoService.insertOrUpdateConfiguracaoFinanceiro(self.configuracaoEmpreendimento).
					 then(function(response){					
						 blockUI.stop();
						 toastr.success('Configuração', 'Alterada!');
					 },
					function(errResponse){					 
						 blockUI.stop();					
						 var erros = errResponse.data.message;
						 sweetAlert({text: erros, 	type : "error", timer : 100000,   width: 500,  padding: 20});
											 
				 	});
			 }
}
