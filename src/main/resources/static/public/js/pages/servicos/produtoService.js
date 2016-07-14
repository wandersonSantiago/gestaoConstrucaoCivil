app.factory('produtoService', function($rootScope, $http,$q){
	
	
	return{
		produtoCreate: function(produto){
			return $http.post('/rest/produto/cadastrarProduto', produto)
			
			.then(function(response){
				
				$rootScope.gravado = true;
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar o Produto');
				$rootScope.naoGravado = true;
				return $q.reject(errResponse);
				
			});
		},
		produtoFindAll: function(){
			return $http.get('rest/produto/cadastrarProduto/listarProduto')
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar buscar os produtos');
				return $q.reject(errResponse);
			});
		},
		
	}
});