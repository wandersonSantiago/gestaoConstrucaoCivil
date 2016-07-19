app.factory('prestadoraServicoService', function($rootScope, $http,$q){
	
	
	return{
		prestadoraServicoCreate: function(prestadoraServico){
			return $http.post('/rest/prestadoraServico/cadastrarPrestadoraServico',prestadoraServico)
			
			.then(function(response){
			
				$rootScope.gravado = true;
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar o prestadora de servico');
				$rootScope.naoGravado = true;
				return $q.reject(errResponse);
				
			});
		},
		prestadoraServicoFindAll: function(){
			return $http.get('rest/prestadoraServico/listaPrestadoraServico/listarPrestadoraServico')
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar buscar as prestadoras de servico');
				return $q.reject(errResponse);
			});
		},
		
	}
});