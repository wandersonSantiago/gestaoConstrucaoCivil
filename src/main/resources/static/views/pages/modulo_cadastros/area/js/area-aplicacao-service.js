app.factory('areaProdutoService', function($http,$q , $rootScope, toastr)
{

	 return {
		 salva: function(areaProduto){
			 return $http.post('rest/almoxarifado/areaProduto/salva', areaProduto)
				.then(function(response){
					toastr.info("Salvo com sucesso!!!");return response.data;
				},function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
				});
			},
			
			
			lista: function(){
				return $http.get('rest/almoxarifado/areaProduto/lista')
				.then(function(response){
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					});
			},
			
			buscaPorId: function(param){
				console.log(param);
				return $http.get('rest/almoxarifado/areaProduto/buscaPorId/'+param)
				.then(function(response){
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
				});
			},
			
			altera: function(areaProduto){
				return $http.put('rest/almoxarifado/areaProduto/altera', areaProduto)
				.then(function(response){
					toastr.info("Alterado com sucesso!!!");	return response.data;
				},function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
						return $q.reject(errResponse);
				});
			},
	 }
	 
});