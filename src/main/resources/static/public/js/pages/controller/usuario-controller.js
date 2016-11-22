app.controller('usuarioController', function($scope, toastr, $rootScope, usuarioService, $http, $routeParams){
	
	var self = this;
		
	var idUsuario = $routeParams.idUsuario;
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
				if($rootScope.user.usuario.empreendimento.tipoEmpreendimento ==  "CONDOMINIO_DE_EDIFICIO_RESIDENCIAL"){
					$rootScope.tipoEmpreendimento = true;
					console.log($rootScope.tipoEmpreendimento);
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
	}
	
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
	}
	 
	$scope.ativo = "ativo";
	$scope.inativo = "inativo";
	
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
		
		//busca a usuario atraves do id
		self.buscaPorId = function(id){
			if(!id)return;
			usuarioService.buscaPorId(id).
			then(function(p){
				self.usuario = p;
				}, function(errResponse){
			});
		};
	//verifica se o params esta com o ide executa o metodo de busca 	
		if(idUsuario){
			self.buscaPorId(idUsuario);
			
		}
	
});