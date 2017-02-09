app.factory('estoqueService', function($rootScope, toastr, $http,$q){
	
	
	return{
		salvaEdificio: function(baixaEstoque){	
			return $http.post('/rest/almoxarifado/estoque/baixaEdificio/salva', baixaEstoque)
			.then(function(response){
				sweetAlert({ timer : 10000, text :"Saida realizada com sucesso!!!", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"Erro, não foi possivel dar a saida, tente novamente!!!",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},	
		salvaCasa: function(baixaEstoqueCasa){	
			return $http.post('/rest/almoxarifado/estoque/baixaCasa/salva', baixaEstoqueCasa)
			.then(function(response){
				sweetAlert({ timer : 10000, text :"Saida realizada com sucesso!!!", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"Erro, não foi possivel dar a saida, tente novamente!!!",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		salvaOutros: function(baixaEstoqueOutros){	
			console.log(baixaEstoqueOutros);
			return $http.post('/rest/almoxarifado/estoque/baixaOutros/salva', baixaEstoqueOutros)
			.then(function(response){
				sweetAlert({ timer : 10000, text :"Saida realizada com sucesso!!!", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"Erro, não foi possivel dar a saida, tente novamente!!!",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		altera: function(baixaEstoque){			
			return $http.put('/rest/almoxarifado/estoque/baixa/altera', baixaEstoque)
			.then(function(response){
				sweetAlert({ timer : 10000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		editarProdutoEstoque : function(produtoEstoque){			
			return $http.put('/rest/produtoEstoque/alteraProduto', produtoEstoque)
			.then(function(response){
				sweetAlert({ timer : 10000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		lista: function(){
			return $http.get('/rest/almoxarifado/estoque/baixa/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		listaProdutosComEstoqueComPaginacao: function(page , maxResults){
			var config = {params: {page: page , maxResults : maxResults}};
			return $http.get('/rest/produtoEstoque/lista/paginacao/' , config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		listaProdutosComEstoque : function(){
			console.log("teste");
			return $http.get('/rest/produtoEstoque')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		buscaPorCodigoBarras : function(codigoBarras){
			return $http.get('/rest/almoxarifado/estoque/baixa/buscaPorCodigoBarras/'+codigoBarras)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		buscaPorId: function(param){
			return $http.get('/rest/produtoEstoque/buscaPorCodigo/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 10000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
	}
});