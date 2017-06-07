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
	buscarComPaginacao: function(page , maxResults, tipo){
			var config = {params: {page: page , maxResults : maxResults, tipo : tipo}};
			return $http.get('/rest/almoxarifado/auditoria/estoque/', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		buscar: function(){
			return $http.get('/rest/almoxarifado/auditoria')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
	

	}
});