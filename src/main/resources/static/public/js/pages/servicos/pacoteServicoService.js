app.factory('pacoteServicoService', function($rootScope, $http,$q){
	
	
	return{
		pacoteServicoCreate: function(pacoteServico){
			return $http.post('/rest/pacoteServico/cadastrarPacoteServico', pacoteServico)
			
			.then(function(response){
			
				$rootScope.gravado = true;
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar o pacote de servico');
				$rootScope.naoGravado = true;
				return $q.reject(errResponse);
				
			});
		},
		pacoteServicoFindAll: function(){
			return $http.get('rest/pacoteServico/pacoteServico/listarPacoteServico')
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar buscar os pacotes de servico');
				return $q.reject(errResponse);
			});
		},
		
	}
});