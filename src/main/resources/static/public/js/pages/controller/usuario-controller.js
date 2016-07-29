app.controller('usuarioController', function($scope,usuarioService, $routeParams){
	
	var self = this;
		
	$scope.listaUsuario = [];
	
	
	 self.cadastrarUsuario = function(usuario){
		 console.log(self.usuario);
		 usuarioService.usuarioCreate(self.usuario);
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