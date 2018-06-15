app.factory('EstoqueService', function($rootScope, toastr, $http,$q){
	
	
	return{
		insert: function(notaFiscalProduto){	
			return $http.post('/rest/nota-fiscal-produto', notaFiscalProduto)
			.then(function(response){
			},function(errResponse){
					return $q.reject(errResponse);
			});
		},	
		
		buscaPorId: function(param){
			return $http.get('/rest/produtoEstoque/buscaPorCodigo/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
	}
});