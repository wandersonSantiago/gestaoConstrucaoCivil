app.factory('unidadeMedidaService', function($rootScope, $http,$q){
	
	
	return{
		unidadeMedidaCreate: function(unidadeMedida){
			return $http.post('/rest/produto/cadastrarUnidadeMedida', unidadeMedida)
			
			.then(function(response){
				console.log("teste");
				$rootScope.gravado = true;
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar Unidade Medida');
				$rootScope.naoGravado = true;
				return $q.reject(errResponse);
				
			});
		},
		unidadeMedidaFindAll: function(){
			return $http.get('rest/produto/cadastrarUnidadeMedida/listarUnidadeMedida')
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar buscar cargo');
				return $q.reject(errResponse);
			});
		},
		
	}
});