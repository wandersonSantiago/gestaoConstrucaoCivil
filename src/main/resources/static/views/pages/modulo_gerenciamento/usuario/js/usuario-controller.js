app.controller("UsuarioBuscarController", UsuarioBuscarController);
app.controller("UsuarioCadastarController", UsuarioCadastarController);
app.controller("UsuarioEditarController", UsuarioEditarController);
app.controller("UsuarioListarController", UsuarioListarController);
app.controller("UsuarioPerfilController", UsuarioPerfilController);
app.controller("UsuarioPermissaoController", UsuarioPermissaoController);

function UsuarioBuscarController($state, $stateParams,UsuarioService, toastr, $rootScope, $scope, $log) {
	
	var self = this;
	
	self.buscarPorTexto = buscarPorTexto;


	function buscarPorTexto(texto){
		UsuarioService.buscarPorTexto(texto).
			then(function(f){
				self.usuarios = f;
				}, function(errResponse){
					sweetAlert({text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
		};
			
}

function UsuarioCadastarController(Auth, EmpreendimentoService,  UsuarioService, toastr, $rootScope, $scope){
	
	var self = this;	
	
	self.submit = submit;
	self.existeLogin = existeLogin;
	self.buscarPorTexto = buscarPorTexto;
	
	 function submit(usuario){
			if(self.senha == self.senhaRepitida){
				self.usuario.senha = self.senha;
				self.usuario.perfilUsuario = self.perfil;
				if(!self.usuario.empreendimento.id){
					self.usuario.empreendimento = null;
				}
				UsuarioService.salvar(self.usuario).
				then(function(response){
					toastr.success("Usuario, cadastrado")
					self.usuario = null;
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
			}else{			
				sweetAlert({ timer : 3000, text: "senha não coencidem, digite novamente" , type : "error", width: 300, higth: 100, padding: 20});
			
			}
		};
	

	function existeLogin(login){			
		UsuarioService.existeLogin(login).
			then(function(p){
				self.existe = p;
				if(self.existe == true){
					$scope.userCtrl.usuario.login = null;
					sweetAlert({ timer : 3000,  text :"Usuario já cadastrado",  type : "info", width: 300, higth: 300, padding: 20});
					}				
				}, function(errResponse){
			});
		};
		
		 function buscarPorTexto(texto){
		     	return  EmpreendimentoService.buscarPorTexto(texto).
		     	 then(function(e){
		     		return e.content;
		     	 }, function(errResponse){
		     	 });
		     }
		
			
}		

function UsuarioEditarController( EmpreendimentoService, $timeout, Auth, $stateParams, $state , UsuarioService, toastr, $rootScope, $scope){
	
	var self = this;
	
	var idUsuario = $stateParams.idUsuario;
	self.submit = submit;
	self.buscarPorId = buscarPorId;
	self.buscarPorTexto = buscarPorTexto;
	self.alterarEmpreendimento = alterarEmpreendimento;
	
	function submit(usuario){		
		if(self.senha == self.senhaRepitida){
			self.usuario.senha = self.senha;
			UsuarioService.alterar(self.usuario).
			then(function(response){
				toastr.info("Usuario Salvo!!!")
				self.usuario = null;
				}, function(errResponse){
			sweetAlert({ timer : 3000, text: errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					
			});
		}else{			
			sweetAlert({ timer : 3000, text: "senha não coencidem, digite novamente" , type : "error", width: 300, higth: 100, padding: 20});
		}
	};	
	
	
	function alterarEmpreendimento(idEmpreendimento){
		if(!idEmpreendimento)return;
		UsuarioService.alterarEmpreendimento(idEmpreendimento).
		then(function(response){
			toastr.success("Empreendimento Alterado!!!")
			self.myVar = setInterval(function(){ logout() }, 500);
			}, function(errResponse){
		sweetAlert({ timer : 3000, text: errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
		});
	}
	
	function logout(){
		Auth.logout();
		clearInterval(self.myVar);
	}

	function buscarPorId(id){
		if(!id)return;
		UsuarioService.buscarPorId(id).
		then(function(p){
			self.usuario = p;
			}, function(errResponse){
		});
	};

	if(idUsuario){
		self.buscarPorId(idUsuario);		
	}
	
	 function buscarPorTexto(texto){
	     	return  EmpreendimentoService.buscarPorTexto(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     }
}

function UsuarioListarController(blockUI, $stateParams, $state , UsuarioService, toastr, $rootScope, $scope){
	
	var self = this;
	
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	
	listar();
	

	  function listar(){
		  blockUI.start();
		  UsuarioService.buscarPorTexto("", self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
		    		self.usuarios = e.content;	
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
	    	 UsuarioService.buscarPorTexto(texto, self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		self.usuarios = e.content;	
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

function UsuarioPermissaoController($stateParams, $state , UsuarioService, PermissaoService,  toastr, $rootScope, $scope){
	
	var self = this;
	var idUsuario = $stateParams.idUsuario;	
	self.submit = submit;	
	listar();
	
	function submit(usuario){		
			UsuarioService.alterar(self.usuario).
			then(function(response){
				toastr.info("Permissoes Atribuidas!!")
				}, function(errResponse){
			sweetAlert({ timer : 3000, text: errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
		});
	};	
	
	 function listar(){
		 PermissaoService.listar().
			then(function(u){				
					self.permissoes = u;			
				}, function(errResponse){
			});
		};		
		
		function buscarPorId(id){
			if(!id)return;
			UsuarioService.buscarPorId(id).
			then(function(p){
				self.usuario = p;
				}, function(errResponse){
			});
		};

		if(idUsuario){
			buscarPorId(idUsuario);		
		}
		
		
	
}

function UsuarioPerfilController($rootScope, $scope, $state, toastr, UsuarioService, $stateParams){
	
	var self = this;
	self.submit = submit;
	self.alterarSenha = alterarSenha;
	$scope.obj = {};
	status();
	self.alterarStatus = alterarStatus;
	
	

	  function submit(){
		 	var file = $scope.obj.flow.files[0]
	    	var form = new FormData();
	    	form.append('file', file.file);	    	
	    	form.append('usuario',new Blob([JSON.stringify($scope.user)], {
	            type: "application/json"
	        }) )
			UsuarioService.salvarFoto(form)
		   	 .then(function(response){
		   		 toastr.success('Foto salva!', 'Sucesso!!!');		
		   	 	},
			function(errResponse){		
				 swal({ timer : 30000, text: errResponse.data.message ,  type : "error", width: 500, higth: 100, padding: 20}).catch(swal.noop);
				 });
		  };
		
	
	  function alterarSenha(users , senhaVerificacao , senha , senhaRepetida){
		  if(senha == senhaRepetida){
				UsuarioService.alterarSenha(users.id, senhaVerificacao, senha).
				then(function(response){
					toastr.success('Alterado', 'Sucesso!!!');
					}, function(errResponse){	
						sweetAlert({text: errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
						});
			}else{			
				sweetAlert({ timer : 3000, text: "senha não coencidem, digite novamente" , type : "info", width: 300, higth: 100, padding: 20});
		}		  
		  
	  }

	  function alterarStatus(usuario){
		  usuario.senha = null;
			UsuarioService.alterar(usuario).
			then(function(response){
				$rootScope.user = response;
				toastr.info("Status alterado!!")
				}, function(errResponse){
			sweetAlert({ timer : 3000, text: errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
		});
	};	
	  function status(){
			 UsuarioService.status().
				then(function(u){				
						self.status = u;			
					}, function(errResponse){
				});
			};		
}