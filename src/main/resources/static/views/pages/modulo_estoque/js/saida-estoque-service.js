app.factory('saidaEstoqueService', function($rootScope, toastr, $http,$q){
	
	
	return{
		salvaEdificio: function(baixaEstoque){	
			return $http.post('/rest/almoxarifado/estoque/baixaEdificio/salva', baixaEstoque)
			.then(function(response){
				toastr.info("Salvo com sucesso!!!");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},	
		salvaCasa: function(baixaEstoqueCasa){	
			return $http.post('/rest/almoxarifado/estoque/baixaCasa/salva', baixaEstoqueCasa)
			.then(function(response){
				toastr.info("Salvo com sucesso!!!");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		salvaOutros: function(baixaEstoqueOutros){	
			return $http.post('/rest/almoxarifado/estoque/baixaOutros/salva', baixaEstoqueOutros)
			.then(function(response){
				toastr.info("Salvo com sucesso!!!");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		altera: function(baixaEstoque){			
			return $http.put('/rest/almoxarifado/estoque/baixa/altera', baixaEstoque)
			.then(function(response){
				toastr.info("Alterado com sucesso!!!");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
						return $q.reject(errResponse);
			});
		},
		lista: function(){
			return $http.get('/rest/almoxarifado/estoque/baixa/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		listaProdutosComEstoque : function(){
			return $http.get('/rest/produtoEstoque/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		buscaPorCodigoBarras : function(codigoBarras){			
			return $http.get('/rest/produtoEstoque/buscaPorCodigo/'+codigoBarras)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		buscaPorId: function(param){
			return $http.get('/rest/almoxarifado/estoque/baixa/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
	}
});