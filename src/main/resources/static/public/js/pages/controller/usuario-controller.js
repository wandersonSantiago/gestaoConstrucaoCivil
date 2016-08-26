app.controller('usuarioController', function($scope, toastr, usuarioService, $http, $routeParams){
	
	var self = this;
		
	var idUsuario = $routeParams.idUsuario;
	
	$scope.listaUsuario = [];
		
	$http.get('/rest/usuario/usuario/').then(function(response) {
		self.user = response.data.name;
	});
	
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
	 
	
	 self.lista = function(){
		 usuarioService.lista().
			then(function(u){
				if(u.ativo == true){
					u.ativo = "ativo";
					self.usuarios = u;
				}else{
					u.ativo = "inativo";
					self.usuarios = u;
				}
				console.log(self.usuarios.ativo);
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