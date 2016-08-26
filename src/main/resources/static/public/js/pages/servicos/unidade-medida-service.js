app.factory('unidadeMedidaService', function($rootScope,  $http,$q){
	
	
	return{
		
		lista: function(){
			return $http.get('rest/almoxarifado/unidadeMedida/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
	}
});