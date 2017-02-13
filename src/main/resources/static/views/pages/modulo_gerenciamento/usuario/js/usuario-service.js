app.factory('usuarioService', function($rootScope, toastr, $http,$q){
	
	
	return{
		
		salva: function(usuario){
			return $http.post('/rest/usuario/salva', usuario)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso",  type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		lista: function(){
			return $http.get('rest/usuario/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		perfil: function(){
			return $http.get('rest/usuario/perfil')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		buscaPorId: function(param){
			return $http.get('rest/usuario/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		buscaPermissaoPorIdUsuario: function(param){
			return $http.get('/rest/permissao/permissaoUsuario/buscaPorUsuario/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		existeLogin: function(login){
			return $http.get('rest/usuario/existeLogin/'+login)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		altera: function(usuario){
			return $http.put('rest/usuario/altera', usuario)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		user: function(){
			return $http.get('/rest/usuario/usuarios')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
	}
});