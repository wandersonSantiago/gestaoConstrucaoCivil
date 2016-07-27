app.factory('prestadoraServicoService', function($rootScope, toastr, $http,$q){
	
	
	return{
		prestadoraServicoCreate: function(prestadoraServico){
			return $http.post('/rest/prestadoraServico/cadastrarPrestadoraServico',prestadoraServico)
			
			.then(function(response){
			
				toastr.info('prestadora de serviço cadastrado');
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar o prestadora de servico');
				toastr.error('errro ao cadastrar prestadora de serviço');
				return $q.reject(errResponse);
				
			});
		},
		prestadoraServicoFindAll: function(){
			return $http.get('rest/prestadoraServico/listaPrestadoraServico/listarPrestadoraServico')
			.then(function(response){
				return response.data;
			},function(errResponse){
				toastr.info('erro ao buscar prestadoras de serviços');
				console.error('Erro ao tentar buscar as prestadoras de servico');
				return $q.reject(errResponse);
			});
		},
		
	}
});