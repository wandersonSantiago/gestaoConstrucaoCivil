app.factory('cotacaoCompraService', function($rootScope, toastr, $http,$q){
	
	
	return{
		salvaCotacaoEmpresa : function(cotacaoEmpresa){
			return $http.post('/rest/almoxarifado/estoque/cotacao/salva', cotacaoEmpresa)
			.then(function(response){
				toastr.info("Salvo com sucesso!!!");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
	
		salva: function(cotacao){	
			return $http.post('/rest/almoxarifado/cotacao/salva', cotacao)
			.then(function(response){
				toastr.info("Salvo com sucesso!!!");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		altera: function(cotacao){			
			return $http.put('/rest/almoxarifado/cotacao/salva', cotacao)
			.then(function(response){
				toastr.info("Alterado com sucesso!!!");return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		lista: function(){
			return $http.get('/rest/almoxarifado/cotacao/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		listaConcorrentes: function(id){
			return $http.get('/rest/almoxarifado/estoque/cotacao/concorrentes/' +id)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		listaVencedores: function(id){
			return $http.get('/rest/almoxarifado/estoque/cotacao/buscaGanhdores/' +id)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		fecharCotacao: function(idCotacao){
			console.log(idCotacao);
			return $http.put('/rest/almoxarifado/cotacao/fecharCotacao/'+idCotacao)
			.then(function(response){
				toastr.info("Cotação Encerrada!!!");	return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		buscaPorCotacaoId: function(param){
			return $http.get('rest/almoxarifado/cotacao/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
	}
});