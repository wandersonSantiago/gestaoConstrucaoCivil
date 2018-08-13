app.factory('VincularService', function($rootScope, toastr, $http,$q){
	
	var url = '/rest/almoxarifado/Vincular';
	return{
		insert: function(obj){
			return $http.post(url, obj)
			.then(function(response){
				return response.data;
			},function(errResponse){
					return $q.reject(errResponse);
			});
		},
		
		findByTextAndPagination: function(tipo , text , page ,  orderBy, direction , maxResults){
			var config = {params: {descricao : text, page: page , maxResults : maxResults, orderBy : orderBy, direction : direction, tipo : tipo}};
			return $http.get(url + '/buscar', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		findById: function(param){
			return $http.get(url + '/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		aceitarVincular: function(idVincular){
			return $http.post(url + '/aceitar', idVincular)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		rejeitarVincular: function(idVincular){
			return $http.post(url + '/rejeitar', idVincular)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		
	}
});