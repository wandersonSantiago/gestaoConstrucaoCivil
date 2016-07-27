app.factory('unidadeMedidaService', function($rootScope, toastr, $http,$q){
	
	
	return{
		unidadeMedidaCreate: function(unidadeMedida){
			return $http.post('/rest/produto/cadastrarUnidadeMedida', unidadeMedida)
			
			.then(function(response){
				console.log("teste");
				toastr.info('unidade de medida cadastrado');
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar Unidade Medida');
				toastr.error('unidade de medida não cadastrado');
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