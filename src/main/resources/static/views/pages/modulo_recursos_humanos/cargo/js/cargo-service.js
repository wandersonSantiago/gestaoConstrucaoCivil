app.factory('cargoService', function($http,$q , $rootScope, toastr)
{

	 return {
		 salva: function(cargo){
			 return $http.post('rest/recursosHumanos/cargo/salva', cargo)
				.then(function(response){
					toastr.info("Salvo com sucesso!!!");return response.data;
				},function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
				});
			},
			
			
			lista: function(){
				return $http.get('rest/recursosHumanos/cargo/lista')
				.then(function(response){
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					});
			},
			listaComPaginacao: function(page , maxResults){
				var config = {params: {page: page , maxResults : maxResults}};
				return $http.get('rest/recursosHumanos/cargo/lista/paginacao/' , config)
				.then(function(response){
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					});
			},
			
			buscaPorId: function(param){
				return $http.get('rest/recursosHumanos/cargo/buscaPorId/'+param)
				.then(function(response){
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
						return $q.reject(errResponse);
				});
			},
			
			altera: function(cargo){
				return $http.put('rest/recursosHumanos/cargo/altera', cargo)
				.then(function(response){
					toastr.info("Alterado com sucesso!!!");return response.data;
				},function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
				});
			},
	 }
	 
});