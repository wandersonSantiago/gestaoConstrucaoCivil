app.factory('EstoqueService', function($rootScope, toastr, $http,$q){
	var url = '/rest/estoque'
	
	return{
		insert: function(notaFiscalProduto){	
			return $http.post('/rest/nota-fiscal-produto', notaFiscalProduto)
			.then(function(response){
			},function(errResponse){
					return $q.reject(errResponse);
			});
		},	
		updateConfiguration: function(estoque){	
			return $http.put(url, estoque)
			.then(function(response){
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
		
		findByTextAndPagination: function(text , page , maxResults){
			var config = {params: {descricao : text, page: page , maxResults : maxResults}};
			return $http.get(url + '/buscar', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
	}
});