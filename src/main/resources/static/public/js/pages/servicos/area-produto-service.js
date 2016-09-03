app.factory('areaProdutoService', function($http,$q , $rootScope, toastr)
{

	 return {
		 salva: function(areaProduto){
			 return $http.post('rest/almoxarifado/areaProduto/salva', areaProduto)
				.then(function(response){
					sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
					return $q.reject(errResponse);
				});
			},
			
			
			lista: function(){
				return $http.get('rest/almoxarifado/areaProduto/lista')
				.then(function(response){
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 3000, text :"falha na conexão", type : "error", width: 300, higth: 100, padding: 20});	return $q.reject(errResponse);
				});
			},
			
			buscaPorId: function(param){
				return $http.get('rest/almoxarifado/areaProduto/buscaPorId/'+param)
				.then(function(response){
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
					return $q.reject(errResponse);
				});
			},
			
			altera: function(areaProduto){
				return $http.put('rest/almoxarifado/areaProduto/altera', areaProduto)
				.then(function(response){
					sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
					return $q.reject(errResponse);
				});
			},
	 }
	 
});