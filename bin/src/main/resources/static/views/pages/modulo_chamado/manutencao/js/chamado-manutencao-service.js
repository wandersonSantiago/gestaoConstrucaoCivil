app.factory('chamadoManutencaoService', function($rootScope, toastr, $http){
	
	
	return{
		salva: function(chamado){
			return $http.post('/rest/chamado/chamadoManutencao/salvar', chamado)
			.then(function(response){
				toastr.info("Salvo com sucesso!!!");	return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		salvaServicos: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/servicos', chamado)
			.then(function(response){
				toastr.info("Serviços Salvo");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		salvaMensagem: function(mensagem){
			return $http.put('/rest/chamado/chamadoManutencao/mensagem', mensagem)
			.then(function(response){
				toastr.info("Mensagem Enviada");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		atenderChamado: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/atender', chamado)
			.then(function(response){
				toastr.info("Chamado Em andamento");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		fecharChamado: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/fechar', chamado)
			.then(function(response){
				toastr.info("Chamado Fechado");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		silenciarChamadoFalse: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/silenciar/false', chamado)
			.then(function(response){
				toastr.info("Alerta Ativado");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		silenciarChamadoTrue: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/silenciar/true', chamado)
			.then(function(response){
				toastr.info("Alterta desativado");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		altera: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/alterar', chamado)
			.then(function(response){
				toastr.info("Alterado com sucesso!!!");return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		relatorioChamadoSuporte: function(pages, maxResults){
			var config = {params: {page: pages , maxResults : maxResults}};
			return $http.get('/rest/chamado/chamadoManutencao/suporte/relatorio/', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		relatorioPorData: function(dataInicial, dataFinal){
			return $http.get('/rest/chamado/chamadoManutencao/suporte/relatorio/dataInicial/' +dataInicial +'/dataFinal/' +dataFinal)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		relatorioPorDataPorTitulo: function(dataInicial, dataFinal ,titulo){
			return $http.get('/rest/chamado/chamadoManutencao/suporte/relatorio/dataInicial/' +dataInicial +'/dataFinal/' +dataFinal + '/titulo/' +titulo)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		buscarPorId: function(chamado){
			return $http.get('/rest/chamado/chamadoManutencao/buscaPorId/'+chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},		
		
		listaSuporte: function(){
			return $http.get('/rest/chamado/chamadoManutencao/suporte/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		listaUsuario: function(){
			return $http.get('/rest/chamado/chamadoManutencao/usuario/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		prioridade: function(){
			return $http.get('/rest/chamado/chamadoManutencao/prioridade')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		status : function(){
			return $http.get('/rest/chamado/chamadoTi/status')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		titulo : function(){
			return $http.get('/rest/chamado/chamadoManutencao/titulo')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
	}
});