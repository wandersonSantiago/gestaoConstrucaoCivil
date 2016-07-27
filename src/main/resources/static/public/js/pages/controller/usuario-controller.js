app.controller('usuarioController', function($scope,usuarioService, $routeParams){
	
	var self = this;
		
	$scope.listaUsuario = [];
	
	
	 self.cadastrarUsuario = function(usuario){
		 console.log(self.usuario);
		 usuarioService.usuarioCreate(self.usuario);
	}
	 
	
	 usuarioService.usuarioFindAll().
	  then(function(usuario){
		  $scope.listaUsuario = usuario;
	  }, function(errResponse){
			console.error('Erro ao tentar buscar Usuario');
			return $q.reject(errResponse);
		});
	 
	
});