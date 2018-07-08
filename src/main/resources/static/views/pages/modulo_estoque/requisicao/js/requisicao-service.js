app.factory('RequisicaoService', function($rootScope, toastr, $http,$q){
	
	var url = '/rest/almoxarifado/requisicao';
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
		aceitarRequisicao: function(idRequisicao){
			return $http.post(url + '/aceitar', idRequisicao)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		rejeitarRequisicao: function(idRequisicao){
			return $http.post(url + '/rejeitar', idRequisicao)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		
	}
});