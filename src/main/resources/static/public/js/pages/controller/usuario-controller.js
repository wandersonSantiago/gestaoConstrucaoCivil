app.controller('usuarioController', function($scope,usuarioService, $routeParams){
	
	var self = this;
		
	$scope.listaUsuario = [];
	
	
	 self.cadastrarUsuario = function(usuario){
		
		 usuarioService.usuarioCreate(self.usuario);
		 
		 self.usuario = usuario = [];
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