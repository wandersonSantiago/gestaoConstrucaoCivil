app.factory('fabricanteService', function($http,$q , $rootScope, toastr)
{

	 return {
		 salva: function(fabricante){
			 return $http.post('rest/almoxarifado/fabricante/salva', fabricante)
				.then(function(response){
					toastr.info("Salvo com sucesso!!!");return response.data;
				},function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
						return $q.reject(errResponse);
				});
			},
			
			
			lista: function(){
				return $http.get('rest/almoxarifado/fabricante/lista')
				.then(function(response){
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					});
			},
			
			buscaPorId: function(param){
				return $http.get('rest/almoxarifado/fabricante/buscaPorId/'+param)
				.then(function(response){
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
						return $q.reject(errResponse);
				});
			},
			
			altera: function(fabricante){
				return $http.put('rest/almoxarifado/fabricante/altera', fabricante)
				.then(function(response){
					toastr.info("Alterado com sucesso!!!");	return response.data;
				},function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
						return $q.reject(errResponse);
				});
			},
	 }
	 
});