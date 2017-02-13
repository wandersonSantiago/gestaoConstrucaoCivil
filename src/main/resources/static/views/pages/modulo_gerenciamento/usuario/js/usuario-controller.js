app.controller('usuarioController', function($scope, toastr, permissaoService,  $rootScope, usuarioService, $http, $routeParams){
	
	var self = this;
	var idUsuario = $routeParams.idUsuario;
	$scope.ativo = "ativo";
	$scope.inativo = "inativo";
	$scope.listaUsuario = [];
	self.listaPerfil = [];
	$scope.listaPerfil = [];
	$scope.moduloAdmin = [];
	$scope.moduloCadastros = [];
	$scope.moduloChamado =[];
	$scope.moduloCompras = [];
	$scope.moduloEstoque = []
	$scope.moduloGerenciamento = [];
	$scope.moduloRecursosHumanos = [];
	$scope.moduloServicos = [];
	$scope.listaPermisaoUsuario = [];
	self.usuario = {};
	self.permissaoUsuario = {};
	 
				
		      $scope.permissao = function (user) {
		        var idx = self.listaPerfil.indexOf(user);
		        if (idx > -1) {
		        	self.listaPerfil.splice(idx, 1);
		        }
		        else {
		        	self.listaPerfil.push(user);
		        }
		      };
		 
		      $scope.existeExclui = function (user) {
		          return self.listaPerfil.indexOf(user) > -1;
		        };
		     
		        
		        self.preencheLista  = function(u){		        	
		    		for(i = 0; i < u.perfilsUsuario.length ; i ++){
		    			for(c = 0; c < $scope.perfils.length ; c ++){
		    				if($scope.perfils[c] == u.perfilsUsuario[i]){
			    				$scope.ativado = true;
			    			}
		    			}		    			
		    		}
		    	}
		       
		        $scope.verificaPerfilparaSalvar = function(){
		        	$scope.listaPerfil = [];
		        	for(c = 0; c < $scope.perfils.length ; c ++){
	    				if($scope.ativado === true){
	    					$scope.listaPerfil.push({perfilsUsuario : $scope.perfils[c]});
		    			}
	    			}		  
		        }
		
	self.user = function(){
		usuarioService.user().
			then(function(u){
				$rootScope.user = u;				
				}, function(errResponse){
			});
		};

	
	
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
			self.usuario.permissoes = self.listaPerfil;
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
				self.buscaPermissaoPorIdUsuario(p.id);
				}, function(errResponse){
			});
		};
		
		self.buscaPermissaoPorIdUsuario = function(id){
			if(!id)return;
			usuarioService.buscaPermissaoPorIdUsuario(id).
			then(function(p){
				$scope.listaPermisaoUsuario = p;
				$scope.perfil();
				
				}, function(errResponse){
			});
		};
		
		if(idUsuario){
			self.buscaPorId(idUsuario);
	}
		
		$scope.perfil = function(){
			 permissaoService.lista().
				then(function(u){
					$scope.permissoes = u;
					verificaTipoPermissao($scope.permissoes);
					}, function(errResponse){
				});
			};
			
		verificaTipoPermissao = function(permissoes){
			
			for(i = 0 ; i < permissoes.length ; i++){
				
			if(permissoes[i].id == $scope.listaPermisaoUsuario[i].permissao.id ){
				$scope.ativo = true;
				console.log($scope.ativo);
			}
			if(permissoes[i].tipoModulo == "ADMIN"){	
				$scope.moduloAdmin.push(permissoes[i]);
				$scope.visualizaAdmin = true;
				
			}
			if(permissoes[i].tipoModulo == "CADASTROS"){
				$scope.moduloCadastros.push(permissoes[i]);
				$scope.visualizaCadastros = true;
			}
			if(permissoes[i].tipoModulo == "CHAMADO"){
				$scope.moduloChamado.push(permissoes[i]);
				$scope.visualizaChamado = true;
			}
			if(permissoes[i].tipoModulo == "COMPRAS"){
				$scope.moduloCompras.push(permissoes[i]);
				$scope.visualizaCompras = true;
			}
			if(permissoes[i].tipoModulo == "ESTOQUE"){
				$scope.moduloEstoque.push(permissoes[i]);
				$scope.visualizaEstoque = true;
			}
			if(permissoes[i].tipoModulo == "GERENCIAMENTO"){
				$scope.moduloGerenciamento.push(permissoes[i]);
				$scope.visualizaGerenciamento = true;
			}
			if(permissoes[i].tipoModulo == "RECURSOS_HUMANOS"){
				$scope.moduloRecursosHumanos.push(permissoes[i]);
				$scope.visualizaRecursosHumanos = true;
			}
			if(permissoes[i].tipoModulo == "SERVICOS"){
				$scope.moduloServicos.push(permissoes[i]);
				$scope.visualizaServicos = true;
			}
			}
		};
	
		//======permissao
		
		 self.salvaPermissaoUsuario = function(permissao){		
			 self.permissaoUsuario.usuario = self.usuario;
			 self.permissaoUsuario.permissao = permissao;
			 console.log(self.permissaoUsuario);
			 permissaoService.salvaPermissaoUsuario(self.permissaoUsuario).
				then(function(response){
					}, function(errResponse){
				});
		};	
	
});