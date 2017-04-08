app.factory('auditoriaEstoqueService', function($rootScope, toastr, $http, $q){
	
	
	return{
		
		buscaPorId: function(param){
			return $http.get('rest/empresaContratada/buscaPorId/'+param)
			.then(function(response){
			return response.data;
			},function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
				});
		},	
	entradaComPaginacao: function(page , maxResults){
			var config = {params: {page: page , maxResults : maxResults}};
			return $http.get('rest/produtoEstoque/auditoria/entrada', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
	

	}
});