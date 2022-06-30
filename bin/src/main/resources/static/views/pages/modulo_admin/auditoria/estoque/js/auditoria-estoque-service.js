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
		
		buscarEntrada: function(){
			return $http.get('/rest/almoxarifado/auditoria/entrada')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		buscarSaida: function(){
			return $http.get('/rest/almoxarifado/auditoria/saida')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		buscarTransferenciaEntrada: function(){
			return $http.get('/rest/almoxarifado/auditoria/transferencia/entrada')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		buscarTransferenciaSaida: function(){
			return $http.get('/rest/almoxarifado/auditoria/transferencia/saida')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		buscarRequisicao: function(){
			return $http.get('/rest/almoxarifado/auditoria/requisicao')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
	

	}
});