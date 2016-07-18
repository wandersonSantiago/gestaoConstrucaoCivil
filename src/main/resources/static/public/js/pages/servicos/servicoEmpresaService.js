app.factory('servicoEmpresaService', function($rootScope, $http,$q){
	
	
	return{
		servicoEmpresaCreate: function(servicoEmpresa){
			return $http.post('/rest/servicoEmpresa/cadastrarServicoEmpresa', servicoEmpresa)
			
			.then(function(response){
			
				$rootScope.gravado = true;
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar o servico da empresa');
				$rootScope.naoGravado = true;
				return $q.reject(errResponse);
				
			});
		},
		fornecedorFindAll: function(){
			return $http.get('rest/servicoEmpresa/cadastrarServicoEmpresa/listarServicoEmpresa')
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar buscar os serviços das empresa');
				return $q.reject(errResponse);
			});
		},
		
	}
});