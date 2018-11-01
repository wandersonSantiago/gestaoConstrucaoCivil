app.factory("EmpreendimentoService", function($http,$rootScope, toastr, $q){
	var url = '/rest/empreendimento';
	return{
			
		buscarPorTexto: function(texto, pagina){
			var config = {params: {page: pagina, descricao:texto}};
			return $http.get(url + '/descricao', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
				
		save: function(params){
			return $http.post(url, params)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		alterar: function(params){
			return $http.put(url , params)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		buscarPorId :function(id){
			return $http.get(url + '/' + id)
			.then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
		
		findByConfigEmpreendimentoId :function(id){
			return $http.get('/rest/configuracao/empreendimento/' + id)
			.then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
		
		dataBase: function(){
			return $http.get('/rest/configuracao/empreendimento/dataBase')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		insertOrUpdateConfiguracaoFinanceiro:function(param){
			return $http.post('/rest/configuracao/empreendimento' , param)
			.then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
		
		getStatus: function(){
			return $http.get(url + '/status')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
	}
	
});