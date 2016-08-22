app.controller('usuarioController', function($scope, toastr, usuarioService, $http, $routeParams){
	
	var self = this;
		
	var idUsuario = $routeParams.idUsuario;
	
	$scope.listaUsuario = [];
		
	$http.get('/rest/usuario/usuario/').then(function(response) {
		self.user = response.data.name;
	});
	
	self.alterarUsuario = function(usuario){
		
		usuarioService.usuarioUpdate(self.usuario);
	}
	
	 self.cadastrarUsuario = function(usuario){
		if(self.senha == self.senhaRepitida){
			self.usuario.senha = self.senha;
			usuarioService.usuarioCreate(self.usuario);
			
			self.usuario = usuario = [];
		}else{			
			toastr.error('senha não coencidem, digite novamente');
			
		}
	}
	 
	
	 self.buscarUsuarios = function(){
		 usuarioService.usuarioFindAll().
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
				toastr.error('Erro ao tentar buscar Usuario');
			});
		};
		//busca a usuario atraves do id
		self.buscarUsuarioPorId = function(id){
			if(!id)return;
			usuarioService.usuarioFindOne(id).
			then(function(p){
				self.usuario = p;
				}, function(errResponse){
				toastr.error('Erro ao buscar empreendimento');
			});
		};
	//verifica se o params esta com o ide executa o metodo de busca 	
		if(idUsuario){
			self.buscarUsuarioPorId(idUsuario);
			
		}
	
});