app.factory('fornecedorService', function($rootScope, toastr, $http,$q){
	
	
	return{
		
		altera: function(fornecedor){
			return $http.put('rest/almoxarifado/fornecedor/altera', fornecedor)
			.then(function(response){
				toastr.info("Alterado com sucesso!!!");	return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		salva: function(fornecedor){
			return $http.post('/rest/almoxarifado/fornecedor/salva', fornecedor)
			.then(function(response){
				toastr.info("Salvo com sucesso!!!");return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		lista: function(){
			return $http.get('rest/almoxarifado/fornecedor/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		buscaPorId: function(param){
			return $http.get('rest/almoxarifado/fornecedor/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
	}
});