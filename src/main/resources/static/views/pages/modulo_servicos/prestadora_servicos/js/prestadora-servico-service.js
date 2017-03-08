app.factory('prestadoraServicoService', function($rootScope, toastr, $http,$q){
	
	
	return{
		
		
	
		salva: function(prestadoraServico){
			return $http.post('/rest/servicos/prestadoraServico/salva', prestadoraServico)
				.then(function(response){
					toastr.info("Salvo com sucesso!!!");
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
						return $q.reject(errResponse);
				});
		},
		
		
		lista: function(){
			return $http.get('rest/servicos/prestadoraServico/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		buscaTodosComPaginacao: function(page , maxResults){			
			var config = {params: {page: page , maxResults : maxResults}};
			return $http.get('rest/servicos/prestadoraServico/', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		buscaPorId: function(param){
			return $http.get('rest/servicos/prestadoraServico/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		altera: function(prestadoraServico){
			return $http.put('/rest/servicos/prestadoraServico/altera', prestadoraServico)
			.then(function(response){
				toastr.info("Alterado com sucesso!!!");	return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
	}
});