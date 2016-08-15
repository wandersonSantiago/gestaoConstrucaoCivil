app.factory('prestadoraServicoService', function($rootScope, toastr, $http,$q){
	
	
	return{
		
		
	
		prestadoraServicoCreate: function(prestadoraServico){
			return $http.post('/rest/prestadoraServico/cadastrarPrestadoraServico', prestadoraServico)
				.then(function(response){
						toastr.info('prestadora de serviço cadastrado');
						return response.data;
				},function(errResponse){
					toastr.error('errro ao cadastrar prestadora de serviço');
					return $q.reject(errResponse);
				});
		},
		
		
		prestadoraServicoFindAll: function(){
			return $http.get('rest/prestadoraServico/listaPrestadoraServico')
			.then(function(response){
				return response.data;
			},function(errResponse){
				toastr.info('erro ao buscar prestadoras de serviços');

				return $q.reject(errResponse);
			});
		},
		
		prestadoraServicoFindOne: function(param){
			return $http.get('rest/prestadoraServico/buscaPrestadoraServicoPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				toastr.error('erro ao buscar prestadora Sevico');
				return $q.reject(errResponse);
			});
		},
		
		
		
		prestadoraServicoUpdate: function(prestadoraServico){
			return $http.put('/rest/prestadoraServico/alterarPrestadoraServico', prestadoraServico)
			.then(function(response){
				toastr.info('Alterado prestadora serviço');
				return response.data;
			},function(errResponse){
				toastr.error('Erro ao tentar prestadora serviço');
				return $q.reject(errResponse);
			});
		},
		
	}
});