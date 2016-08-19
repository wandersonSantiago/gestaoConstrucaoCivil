app.controller('usuarioController', function($scope, toastr, usuarioService, $routeParams){
	
	var self = this;
		
	$scope.listaUsuario = [];
	
	
	 self.cadastrarUsuario = function(usuario){
		if(self.senha == self.senhaRepitida){
			self.usuario.senha = self.senha;
			usuarioService.usuarioCreate(self.usuario);
			
			//self.usuario = usuario = [];
		}else{			
			toastr.error('senha não coencidem, digite novamente');
			
		}
	}
	 
	
	 self.buscarUsuarios = function(){
		 usuarioService.usuarioFindAll().
			then(function(u){
				self.listaUsuario = u;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar Usuario');
			});
		};
	 
	
});