app.factory('cotacaoCompraService', function($rootScope, toastr, $http,$q){
	
	
	return{
		salva: function(cotacao){	
			return $http.post('/rest/almoxarifado/cotacao/salva', cotacao)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		altera: function(cotacao){			
			return $http.put('/rest/almoxarifado/compra/cotacao/salva', cotacao)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		lista: function(){
			return $http.get('/rest/almoxarifado/compra/cotacao/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
	}
});