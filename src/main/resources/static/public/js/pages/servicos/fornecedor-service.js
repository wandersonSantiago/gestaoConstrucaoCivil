app.factory('fornecedorService', function($rootScope, toastr, $http,$q){
	
	
	return{
		
		fornecedorUpdate: function(fornecedor){
			return $http.put('rest/almoxarifado/alterarFornecedor', fornecedor)
			.then(function(response){
				toastr.info('fornecedor Alterado ');
				return response.data;
			},function(errResponse){
				toastr.error('Erro ao tentar Alterar fornecedor');
				return $q.reject(errResponse);
			});
		},
		
		fornecedorCreate: function(fornecedor){
			return $http.post('/rest/almoxarifado/cadastrarFornecedor', fornecedor)
			.then(function(response){
				toastr.info('Fornecedor cadastrado');
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar gravar o fornecedor');
				toastr.error('fornecedor não cadastrado');
				return $q.reject(errResponse);
			});
		},
		fornecedorFindAll: function(){
			return $http.get('rest/almoxarifado/listarFornecedor')
			.then(function(response){
				return response.data;
			},function(errResponse){
				toastr.error('erro ao buscar fornecedores');
				console.error('Erro ao tentar buscar os fornecedores');
				return $q.reject(errResponse);
			});
		},
		
		fornecedorFindOne: function(param){
			return $http.get('rest/almoxarifado/listarFornecedorPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar Buscar Fornecedor');
				return $q.reject(errResponse);
			});
		},
		
	}
});