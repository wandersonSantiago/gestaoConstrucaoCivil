app.factory('permissaoService', function($rootScope, toastr, $http,$q){
	
	
	return{
		
		salva: function(permissao){
			return $http.post('/rest/usuario/permissao', permissao)
			.then(function(response){
				toastr.info("Salvo com sucesso!!!");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		salvaPermissaoUsuario: function(permissao){
			return $http.post('/rest/permissao/permissaoUsuario/salva', permissao)
			.then(function(response){
				toastr.info("Salvo com sucesso!!!");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		excluiPermissaoUsuario :function(permissao){
			return $http.delete('/rest/permissao/permissaoUsuario/removerPermissao/'+permissao)
			.then(function(response){
				toastr.danger("Excluido com sucesso!!!");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		lista: function(){
			return $http.get('rest/usuario/permissao/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		modulo: function(){
			return $http.get('rest/usuario/permissao/modulo')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		buscaPorId: function(param){
			return $http.get('rest/usuario/permissao/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		altera: function(permissao){
			return $http.put('rest/usuario/permissao', permissao)
			.then(function(response){
				toastr.info("Alterado com sucesso!!!");return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
	}
});