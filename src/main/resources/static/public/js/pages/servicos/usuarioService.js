app.factory('usuarioService', function($rootScope, $http,$q){
	
	
	return{
		usuarioCreate: function(usuario){
			return $http.post('/rest/usuario/cadastrarUsuario', usuario)
			
			.then(function(response){
			
				$rootScope.gravado = true;
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar o usuario');
				$rootScope.naoGravado = true;
				return $q.reject(errResponse);
				
			});
		},
		usuarioFindAll: function(){
			return $http.get('rest/usuario/cadastrarUsuario/listarUsuario')
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar buscar os usuario');
				return $q.reject(errResponse);
			});
		},
		
	}
});