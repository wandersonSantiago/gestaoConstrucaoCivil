app.factory('fornecedorService', function($rootScope, $http,$q){
	
	
	return{
		fornecedorCreate: function(fornecedor){
			return $http.post('/rest/fornecedor/cadastrarFornecedor', fornecedor)
			
			.then(function(response){
			
				$rootScope.gravado = true;
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar o fornecedor');
				$rootScope.naoGravado = true;
				return $q.reject(errResponse);
				
			});
		},
		fornecedorFindAll: function(){
			return $http.get('rest/fornecedor/cadastrarFornecedor/listarFornecedor')
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar buscar os fornecedores');
				return $q.reject(errResponse);
			});
		},
		
	}
});