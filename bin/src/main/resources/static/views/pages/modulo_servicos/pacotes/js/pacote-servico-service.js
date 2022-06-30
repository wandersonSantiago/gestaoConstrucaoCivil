app.factory("PacoteService", function($http,$rootScope, toastr, $q){
	var url = '/rest/servicos/pacotes';
	return{
			
		buscarPorTexto: function(texto, pagina){
			var config = {params: {page: pagina, descricao : texto}};
			return $http.get(url + '/buscar', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
				
		save: function(params){
			return $http.post(url, params)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		alterar: function(params){
			return $http.put(url , params)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		buscarPorId :function(id){
			return $http.get(url + '/' + id)
			.then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
		buscarPorCNPJ: function(cnpj){
			return $http.get(url + '/cnpj/' + cnpj)
			.then(function(response){
			   return response.data;
			}, function(errResponse){
				return $q.reject(errResponse);
			});
		},
		existeCnpj : function(cnpj){
			return $http.get(url + '/existeCnpj/' + cnpj)
			.then(function(response){
			   return response.data;
			}, function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		status: function(){
			return $http.get(url + '/status')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		count: function(){
			return $http.get(url + '/count')
			.then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
	}
	
});