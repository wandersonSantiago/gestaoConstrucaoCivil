app.factory('requisicaoEstoqueService', function($rootScope, toastr, $http,$q){
	
	
	return{
		salvaEdificio: function(requisicaoEstoque){	
			return $http.post('/rest/almoxarifado/estoque/requisicaoEdificio/salva', requisicaoEstoque)
			.then(function(response){
				sweetAlert({ timer : 10000, text :"Saida realizada com sucesso!!!", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"Erro, não foi possivel dar a saida, tente novamente!!!",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},	
		salvaCasa: function(requisicaoEstoqueCasa){	
			return $http.post('/rest/almoxarifado/estoque/requisicaoCasa/salva', requisicaoEstoqueCasa)
			.then(function(response){
				sweetAlert({ timer : 10000, text :"Saida realizada com sucesso!!!", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"Erro, não foi possivel dar a saida, tente novamente!!!",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		salvaOutros: function(requisicaoEstoqueOutros){	
			return $http.post('/rest/almoxarifado/estoque/requisicaoOutros/salva', requisicaoEstoqueOutros)
			.then(function(response){
				sweetAlert({ timer : 10000, text :"Saida realizada com sucesso!!!", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"Erro, não foi possivel dar a saida, tente novamente!!!",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		altera: function(requisicaoEstoque){			
			return $http.put('/rest/almoxarifado/estoque/requisicao/altera', requisicaoEstoque)
			.then(function(response){
				sweetAlert({ timer : 10000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		listaRequisicaoEdificio: function(){
			return $http.get('/rest/almoxarifado/estoque/requisicaoEdificio/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		listaRequisicaoCasa: function(){
			return $http.get('/rest/almoxarifado/estoque/requisicaoCasa/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		listaRequisicaoOutros: function(){
			return $http.get('/rest/almoxarifado/estoque/requisicaoOutros/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		listaProdutosComEstoque : function(){
			return $http.get('/rest/produtoEstoque/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		buscaPorCodigoBarras : function(codigoBarras){
			return $http.get('/rest/produtoEstoque/buscaPorCodigo/'+codigoBarras)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		buscaPorIdCasa: function(param){
			return $http.get('/rest/almoxarifado/estoque/requisicaoCasa/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		buscaPorIdEdificio: function(param){
			return $http.get('/rest/almoxarifado/estoque/requisicaoEdificio/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		buscaPorIdOutros: function(param){
			return $http.get('/rest/almoxarifado/estoque/requisicaoOutros/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		aceitarRequisicaoCasa: function(numeroNota){
			return $http.post('/rest/almoxarifado/estoque/requisicaoCasa/aceitar', numeroNota)
			.then(function(response){
				sweetAlert({ timer : 6000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},		
		rejeitaRequisicaoCasa: function(numeroNota){
			return $http.post('/rest/almoxarifado/estoque/requisicaoCasa/rejeitar', numeroNota)
			.then(function(response){
				sweetAlert({ timer : 6000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},		
		aceitarRequisicaoEdificio: function(numeroNota){
			return $http.post('/rest/almoxarifado/estoque/requisicaoEdificio/aceitar', numeroNota)
			.then(function(response){
				sweetAlert({ timer : 6000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},		
		rejeitaRequisicaoEdificio: function(numeroNota){
			return $http.post('/rest/almoxarifado/estoque/requisicaoEdificio/rejeitar', numeroNota)
			.then(function(response){
				sweetAlert({ timer : 6000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},		
		aceitarRequisicaoOutros: function(numeroNota){
			return $http.post('/rest/almoxarifado/estoque/requisicaoOutros/aceitar', numeroNota)
			.then(function(response){
				sweetAlert({ timer : 6000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},		
		rejeitaRequisicaoOutros: function(numeroNota){
			return $http.post('/rest/almoxarifado/estoque/requisicaoOutros/rejeitar', numeroNota)
			.then(function(response){
				sweetAlert({ timer : 6000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},		
	}
});