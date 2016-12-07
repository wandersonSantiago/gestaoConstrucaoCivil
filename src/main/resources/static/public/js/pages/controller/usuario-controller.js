app.controller('usuarioController', function($scope, toastr, role, $rootScope, usuarioService, $http, $routeParams){
	
	var self = this;
	var idUsuario = $routeParams.idUsuario;
	$scope.ativo = "ativo";
	$scope.inativo = "inativo";
	$rootScope.tipoEmpreendimento = false;
	$scope.listaUsuario = [];
	
	$scope.perfil = function(){
			usuarioService.perfil().
				then(function(u){
					$scope.perfils = u;
					}, function(errResponse){
				});
			};
	
		
	self.user = function(){
		usuarioService.user().
			then(function(u){
				$rootScope.user = u;
				role.permission(u);
				if($rootScope.user.usuario.empreendimento.tipoEmpreendimento ==  "CONDOMINIO_DE_EDIFICIO_RESIDENCIAL"){
					$rootScope.tipoEmpreendimento = true;
				}else{
					$rootScope.tipoEmpreendimento = false;
				}
				}, function(errResponse){
			});
		};

		if($rootScope.tipoEmpreendimento == false){
			self.user();
		}
	
	self.altera = function(usuario){
		if(self.senha == self.senhaRepitida){
			self.usuario.senha = self.senha;
			usuarioService.altera(self.usuario).
			then(function(response){
				self.usuario = null;
				}, function(errResponse){
			});
		}else{			
			sweetAlert({ timer : 3000, text: "senha não coencidem, digite novamente" , type : "error", width: 300, higth: 100, padding: 20});
		}
	};
	
	self.alteraSenha = function(usuario){		
		if(self.senha == self.senhaRepitida){
			usuario.usuario.senha = self.senha;
			usuarioService.altera(usuario.usuario).
			then(function(response){
				self.usuario = null;
				}, function(errResponse){
			});
		}else{			
			sweetAlert({ timer : 3000, text: "senha não coencidem, digite novamente" , type : "error", width: 300, higth: 100, padding: 20});
		}
	};
	
	self.alteraEmpreendimento = function(usuario){		
			usuarioService.altera(usuario).
			then(function(response){
				}, function(errResponse){
			});		
	};
	
	 self.salva = function(usuario){
		if(self.senha == self.senhaRepitida){
			self.usuario.senha = self.senha;
			usuarioService.salva(self.usuario).
			then(function(response){
				self.usuario = null;
				}, function(errResponse){
			});
		}else{			
			sweetAlert({ timer : 3000, text: "senha não coencidem, digite novamente" , type : "error", width: 300, higth: 100, padding: 20});
		}
	};	
	
	 self.lista = function(){
		 usuarioService.lista().
			then(function(u){
				self.usuarios = u;
				for(i = 0; i < u.length ; i++){
					if(u[i].ativo == true){
						self.usuarios[i].ativo = $scope.ativo;
					}else{
						self.usuarios[i].ativo = $scope.inativo;					
					}
				}				
				}, function(errResponse){
			});
		};
		
		self.existeLogin = function(login){			
			usuarioService.existeLogin(login).
			then(function(p){
				self.existe = p;
				console.log(self.existe );
				if(self.existe == true){
					sweetAlert({ timer : 3000,  text :"Usuario já cadastrado",  type : "info", width: 300, higth: 300, padding: 20});
					$scope.userCtrl.usuario.login = null;
				  }
				}, function(errResponse){
			});
		};
		
		
		self.buscaPorId = function(id){
			if(!id)return;
			usuarioService.buscaPorId(id).
			then(function(p){
				self.usuario = p;
				}, function(errResponse){
			});
		};
		if(idUsuario){
			self.buscaPorId(idUsuario);
	}
	
});