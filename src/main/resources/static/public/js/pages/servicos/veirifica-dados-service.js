app.factory('verificaDadosService', function($rootScope, $http,$q){
	
	
	return{
		
		
		
		verificaCnpj: function(param){
			return $http.get('/rest/dadoEmpresa/existeCnpj/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		verificaIe: function(param){
			return $http.get('/rest/dadoEmpresa/existeIe/'+param)
			.then(function(response){
				return response.data;
				console.log(response);
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
	}
});