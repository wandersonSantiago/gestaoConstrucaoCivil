app.factory('servicoEmpresaService', function($rootScope, toastr, $http,$q){
	
	
	return{
		salvaEdificio: function(objeto){
			return $http.post('/rest/servico/edificio/vincular/salva', objeto)
			.then(function(response){
				toastr.info("Salvo com sucesso!!!");return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		alteraEdificio: function(objeto){
			return $http.put('/rest/servico/edificio/vincular', objeto)
			.then(function(response){
				toastr.info("Alterado com sucesso!!!");	return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		listaEdificio: function(){
			return $http.get('/rest/servico/edificio/vincular/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		buscaTodosComPaginacaoEdificio: function(page , maxResults){
			var config = {params: {page: page , maxResults : maxResults}};
			return $http.get('/rest/servico/edificio/vincular/', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		buscaServicoEdificioPorId: function(param){
			return $http.get('/rest/servico/edificio/vincular/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		salvaCasa: function(objeto){
			return $http.post('/rest/servico/casa/vincular/salva', objeto)
			.then(function(response){
				toastr.info("Salvo com sucesso!!!");return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		alteraCasa: function(objeto){
			return $http.put('/rest/servico/casa/vincular', objeto)
			.then(function(response){
				toastr.info("Alterado com sucesso!!!");	return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		listaCasa: function(){
			return $http.get('/rest/servico/casa/vincular/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		buscaTodosComPaginacaoCasa: function(page , maxResults){
			var config = {params: {page: page , maxResults : maxResults}};
			return $http.get('/rest/servico/casa/vincular/', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		buscaServicoCasaPorId: function(param){
			return $http.get('/rest/servico/casa/vincular/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		
		salvaEdificacoesComunitaria: function(objeto){
			return $http.post('/rest/servico/outros/vincular/salva', objeto)
			.then(function(response){
				toastr.info("Salvo com sucesso!!!");	return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		alteraEdificacoesComunitaria: function(objeto){
			return $http.put('/rest/servico/outros/vincular', objeto)
			.then(function(response){
				toastr.info("Alterado com sucesso!!!");return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		listaEdificacoesComunitaria: function(){
			return $http.get('/rest/servico/outros/vincular/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		buscaTodosComPaginacaoEdificacoesComunitaria: function(page , maxResults){
			var config = {params: {page: page , maxResults : maxResults}};
			return $http.get('/rest/servico/outros/vincular/', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		buscaServicoComunitarioPorId: function(param){
			return $http.get('/rest/servico/outros/vincular/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		consultarServicoEdificio: function(torre , andar , apartamento){
			return $http.get('/rest/servico/edificio/vincular/vistoria/torre/'+torre+'/andar/'+andar+'/apartamento/'+apartamento)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		consultarServicoCasa: function(casa , andar){
			return $http.get('/rest/servico/outros/vincular/vistoria/casa/'+casa+'/andar/'+andar)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		consultarServicoEdificacoesComunitaria: function(outros){
			return $http.get('/rest/servico/outros/vincular/vistoria/outros/'+outros)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		alteraVistoriaEdificio: function(objeto){
			return $http.put('/rest/servico/edificio/vincular/vistoria', objeto)
			.then(function(response){
				toastr.info("Alterado com sucesso!!!");	return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
	}
});