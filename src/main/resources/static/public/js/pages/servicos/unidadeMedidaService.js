app.factory('unidadeService', function($http,$q)
{

	 return {
		 unidadeMedidaCreate: function(unidadeMedida){
			 return $http.post('rest/produto/cadastrarUnidadeMedida', unidadeMedida)
				.then(function(response){
					return response.data;
				},function(errResponse){
					console.error('Erro ao tentar gravar Unidade Medida');
					return $q.reject(errResponse);
				});
			},
			unidadeMedidaFindAll: function(){
				return $http.get('rest/produto/cadastrarUnidadeMedida/listarUnidadeMedida')
				.then(function(response){
					return response.data;
				},function(errResponse){
					console.error('Erro ao tentar buscar Unidade Medida');
					return $q.reject(errResponse);
				});
			},
	 }
});