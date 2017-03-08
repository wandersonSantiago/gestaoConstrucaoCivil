app.factory('notaFiscalService', function($rootScope, toastr, $http,$q){
	
	
	return{
		salva: function(notaFiscalProduto){			
		console.log(notaFiscalProduto);
			return $http.post('/rest/notaFiscalProduto/salva', notaFiscalProduto)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		altera: function(notaFiscalProduto){			
			return $http.put('/rest/notaFiscal/altera', notaFiscalProduto)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		lista: function(){
			return $http.get('rest/notaFiscal/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
	}
});