app.factory('transferenciaEstoqueService', function($rootScope, toastr, $http,$q){
	
	
	return{
		salva: function(transferencia){
			console.log(transferencia);
			return $http.post('/rest/almoxarifado/transferencia/salva', transferencia)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},		
		aceitaTransferencia: function(numeroNota){
			return $http.post('/rest/almoxarifado/transferencia/aceitar', numeroNota)
			.then(function(response){
				sweetAlert({ timer : 6000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},		
		rejeitaTransferencia: function(numeroNota){
			return $http.post('/rest/almoxarifado/transferencia/rejeitar', numeroNota)
			.then(function(response){
				sweetAlert({ timer : 6000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},		
		altera: function(baixaEstoque){			
			return $http.put('/rest/almoxarifado/transferencia/altera', baixaEstoque)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		listaEnviadas: function(){
			return $http.get('/rest/almoxarifado/transferencia/enviada')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"Não existe transferencia",  type : "info", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		listaEnviadasComPaginacao: function(page , maxResults){
			var config = {params: {page: page , maxResults : maxResults}};
			return $http.get('/rest/almoxarifado/transferencia/enviada/paginacao/' , config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"Não existe transferencia",  type : "info", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		listaRecebidas: function(){
			return $http.get('/rest/almoxarifado/transferencia/recebida')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"Não existe transferencia",  type : "info", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		listaRecebidasComPaginacao: function(page , maxResults){
			var config = {params: {page: page , maxResults : maxResults}};
			return $http.get('/rest/almoxarifado/transferencia/recebida/paginacao/' , config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"Não existe transferencia",  type : "info", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		lista: function(){
			return $http.get('/rest/almoxarifado/transferencia/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"Não existe transferencia",  type : "info", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		listaProdutosComEstoque : function(){
			return $http.get('/rest/produtoEstoque')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		buscaPorId: function(param){
			return $http.get('/rest/almoxarifado/transferencia/buscaPorId/'+param)
			.then(function(response){
				return response.data;				
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		buscaEnviados: function(param){
			return $http.get('/rest/almoxarifado/transferencia/buscaPorId/'+param)
			.then(function(response){
				return response.data;				
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		buscaRecebidos: function(param){
			return $http.get('/rest/almoxarifado/transferencia/buscaPorId/'+param)
			.then(function(response){
				return response.data;				
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
	}
});