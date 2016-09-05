app.factory('tipoProdutoService', function($rootScope, toastr, $http,$q){
	
	
	return{
		
		altera: function(tipoProduto){
			return $http.put('rest/almoxarifado/produto/tipo/altera', tipoProduto)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		salva: function(tipoProduto){
			return $http.post('/rest/almoxarifado/produto/tipo/salva', tipoProduto)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso",  type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		lista: function(){
			return $http.get('rest/almoxarifado/produto/tipo/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		buscaPorId: function(param){
			console.log(param);
			return $http.get('rest/almoxarifado/produto/tipo/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
	}
});