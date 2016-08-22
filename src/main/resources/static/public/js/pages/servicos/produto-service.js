app.factory('produtoService', function($rootScope, toastr, $http,$q){
	
	
	return{
		produtoCreate: function(produto){
			return $http.post('/rest/almoxarifado/cadastrarProduto', produto)
			
			.then(function(response){
				
				toastr.info('produto cadastrado');
				
				return response.data;
			
			},function(errResponse){
			
				toastr.error('produto não cadastrado');
				return $q.reject(errResponse);
				
			});
		},
		produtoFindAll: function(){
			return $http.get('rest/almoxarifado/listarProduto')
			.then(function(response){
				return response.data;
			},function(errResponse){
				toastr.error('erro ao buscar produtos');
			
				return $q.reject(errResponse);
			});
		},
		
	}
});