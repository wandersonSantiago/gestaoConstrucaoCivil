app.factory('pacoteServicoService', function($rootScope, toastr, $http,$q){
	
	
	return{
		pacoteServicoCreate: function(pacoteServico){
			return $http.post('/rest/pacoteServico/cadastrarPacoteServico', pacoteServico)
			.then(function(response){
			toastr.info('pacotes de serviço cadastrado');
			return response.data;
			},function(errResponse){
			
				toastr.error('pacotes de serviço não cadastrado');
				return $q.reject(errResponse);
			});
		},
		pacoteServicoFindAll: function(){
			return $http.get('rest/pacoteServico/listarPacoteServico')
			.then(function(response){
				return response.data;
			},function(errResponse){
				toastr.error('erro ao buscar pacotes de serviços');
		
				return $q.reject(errResponse);
			});
		},
		
		pacoteServicoFindOne: function(param){
			return $http.get('rest/pacoteServico/listarPacoteServicoId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar Buscar pacote de Serviço');
				return $q.reject(errResponse);
			});
		},
		
		
	}
});