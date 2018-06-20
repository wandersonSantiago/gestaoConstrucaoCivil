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
		
		buscaPorId: function(param){
			return $http.get('/rest/estoque/buscaPorCodigo/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
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