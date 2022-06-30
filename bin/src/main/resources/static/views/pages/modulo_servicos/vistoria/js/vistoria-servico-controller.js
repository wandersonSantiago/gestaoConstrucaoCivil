app.controller("VistoriaCadastarController", VistoriaCadastarController);
app.controller("VistoriaEditarController", VistoriaEditarController);
app.controller("VistoriaListarController", VistoriaListarController);
app.controller("VistoriaShowController", VistoriaShowController);

function VistoriaCadastarController(blockUI, $localStorage, $state, $stateParams, PrestadoraService, VistoriaService, EstruturaService, PacoteService, toastr, $scope){
	
	var self = this;	
	self.submit = submit;
	self.ocorrencia = ocorrencia;
	$scope.obj = {};
	self.vistoria = {};
	$scope.titulo = 'Cadastrar';
	
	
			function submit(form){
				if(form.$invalid){
					sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "info", timer : 100000,   width: 500,  padding: 20});	
					return;
				}	
				VistoriaService.insert(self.servicoEmpresa).
				then(function(response){
					toastr.success("Vistoria, enviada");	
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
			};
		
			
			function ocorrencia(form){
				  if(form.$invalid){
						sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
						return;
					}
				 	var file = $scope.obj.flow.files[0]
			    	var form = new FormData();
				 	if(file){
				 		form.append('file', file.file);	
				 	}		    	    	
			    	form.append('servicoEmpresa',new Blob([JSON.stringify(self.servicoEmpresa)], {
			            type: "application/json"
			        }) )		        
					LancamentosService.insert(form)
				   	 .then(function(response){
						toastr.success("Lancamento, cadastrado");			
				   	 	},
					function(errResponse){		
						 swal({ timer : 30000, text: errResponse.data.message ,  type : "error", width: 500, higth: 100, padding: 20}).catch(swal.noop);
						 });
				  };
			
	
}		

function VistoriaEditarController(EstruturaService, blockUI, $localStorage, $state, $stateParams, VistoriaService, EstoqueService,  toastr, $scope){
	
	var self = this;
	
	var idVistoria = $stateParams.idVistoria;
	
	self.submit = submit;
	$scope.ocorrencia = {}
	self.ocorrencias = [];
	self.ocorrencia = ocorrencia;
	$scope.obj = {};
	self.vistoria = {};
	$scope.titulo = 'Cadastrar';
	
	
			function submit(form){
				if(form.$invalid){
					sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "info", timer : 100000,   width: 500,  padding: 20});	
					return;
				}	
				VistoriaService.insert(self.servicoEmpresa).
				then(function(response){
					toastr.success("Vistoria, enviada");	
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
			};
		
			
			function ocorrencia(){
				 	var files = $scope.obj.flow.files;
			    	var form = new FormData();
			    	var fomrFiles = [];
				 	if(files){
				 		angular.forEach(files, function(file, key) {
				 			form.append('file', file.file);
				 			});				 		
				 	}						 	
			    	form.append('servicoEmpresa',new Blob([JSON.stringify(self.servicoEmpresa)], {
			            type: "application/json"
			        }) )
			        form.append('ocorrencia',new Blob([JSON.stringify($scope.ocorrencia)], {
			            type: "application/json"
			        }) )
					VistoriaService.ocorrencia(form)
				   	 .then(function(response){
						toastr.success("Ocorrencia, registrada");			
				   	 	},
					function(errResponse){		
						 swal({ timer : 30000, text: errResponse.data.message ,  type : "error", width: 500, higth: 100, padding: 20}).catch(swal.noop);
						 });
				  };
	
	findById(idVistoria);
		
    		
	 function findById(id){
		if(!id)return;
		VistoriaService.findById(id).
		then(function(p){
			self.servicoEmpresa = p;	
			}, function(errResponse){
		});
	};	
		
}

function VistoriaListarController(blockUI, VistoriaService, toastr, $scope, $stateParams){
	
	var self = this;
	self.sort = sort;
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = null;
	self.totalPaginas = null;
	self.paginaCorrente = 0;
		
	$scope.direction = 'ASC';
	
	buscarPorTexto('', null, null);
		 
	function sort(orderBy){		
		$scope.direction == 'ASC' ? $scope.direction = 'DESC' : $scope.direction = 'ASC';
		buscarPorTexto($scope.texto, orderBy,  $scope.direction);
	}    
	     
	    function buscarPorTexto(texto, orderBy,  direction){
	    	$scope.mensagemErro = null;	    	 
	    	 blockUI.start();
	    	 self.paginaCorrente == '0'? self.paginaCorrente = 0 : self.paginaCorrente = self.paginaCorrente - 1;   
	    	 VistoriaService.findByTextAndPagination(texto, self.paginaCorrente, orderBy, direction).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		 self.vistorias = e.content;	
	    		 self.totalElementos = e.totalElements;
	    		 self.totalPaginas = e.totalPages;
	    		 self.paginaCorrente = e.number + 1;
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
	    
	    $scope.dynamicPopover = {
    		    content: '',
    		    templateUrl: 'myPopoverTemplate.html',
    		    title: 'Title'
    		  };
	    $scope.dynamicPopoverEstruturas = {
    		    content: '',
    		    templateUrl: 'myPopoverTemplateEstruturas.html',
    		    title: 'Title'
    		  };
	    $scope.dynamicPopoverValor = {
    		    content: '',
    		    templateUrl: 'myPopoverTemplateValor.html',
    		    title: 'Title'
    		  };
	    $scope.dynamicPopoverEmpresa = {
    		    content: '',
    		    templateUrl: 'myPopoverTemplateEmpresa.html',
    		    title: 'Title'
    		  };
	    $scope.dynamicPopoverPacote = {
    		    content: '',
    		    templateUrl: 'myPopoverTemplatePacote.html',
    		    title: 'Title'
    		  };
}
function VistoriaShowController($stateParams, VistoriaService,  toastr, $scope, $state){
	
	var self = this;	
	

	self.itens = [];
	
	var idVistoria = $stateParams.idVistoria;
	self.aceitarVistoria = aceitarVistoria;
	self.rejeitarVistoria = rejeitarVistoria;
	
	findById(idVistoria);
	
	
	 function findById(id){
			if(!id)return;
			VistoriaService.findById(id).
			then(function(p){
				self.vistoria = p;
				$scope.produtos = p.itens;
				}, function(errResponse){
			});
		};
		
		function aceitarVistoria(idVistoria){
			if(!idVistoria)return;
			VistoriaService.aceitarVistoria(idVistoria).
			then(function(p){
				toastr.info("Vistoria, Aceita")
				var tipo = 'LIBERAR';
				$state.go('vistoria.full', {tipo});
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
		
		function rejeitarVistoria(idVistoria){
			if(!idVistoria)return;
			VistoriaService.rejeitarVistoria(idVistoria).
			then(function(p){
				toastr.info("Vistoria, Rejeitada")
				var tipo = 'LIBERAR';
				$state.go('vistoria.full', {tipo});
				}, function(errResponse){
					sweetAlert({title: errResponse.data.message, 	type : "info", timer : 100000,   width: 500,  padding: 20});	
			});
		};
}		

