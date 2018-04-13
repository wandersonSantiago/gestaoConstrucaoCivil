app.factory('PermissaoService', function($rootScope, toastr, $http,$q){
	
	var url = "/rest/usuario/permissao"
	return{
		
		salvar: function(permissao){
			return $http.post(url , permissao)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		listar: function(){
			return $http.get(url)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		buscarPorId: function(param){
			return $http.get(url + "/"+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		alterar: function(permissao){
			return $http.put(url, permissao)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		modulos: function(){
			return $http.get(url + '/modulo')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
	}
});