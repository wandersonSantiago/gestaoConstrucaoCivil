app.factory('chamadoManutencaoService', function($rootScope, toastr, $http){
	
	
	return{
		salva: function(chamado){
			return $http.post('/rest/chamado/chamadoManutencao/salvar', chamado)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Enviado com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"Não foi possivel Enviar",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		salvaServicos: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/servicos', chamado)
			.then(function(response){
				toastr.info("Serviços Salvo");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"Erro",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		salvaMensagem: function(mensagem){
			return $http.put('/rest/chamado/chamadoManutencao/mensagem', mensagem)
			.then(function(response){
				toastr.info("Mensagem Enviada");
				//	sweetAlert({ timer : 3000, text :"Mensagem Enviada", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"Mensagem não Enviada",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		atenderChamado: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/atender', chamado)
			.then(function(response){
				toastr.info("Chamado Em andamento");
				//sweetAlert({ timer : 3000, text :"Chamado Atendido", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"Erro",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		fecharChamado: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/fechar', chamado)
			.then(function(response){
				toastr.info("Chamado Fechado");
				//sweetAlert({ timer : 3000, text :"Encerrado", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"não encerrado",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		silenciarChamadoFalse: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/silenciar/false', chamado)
			.then(function(response){
				toastr.info("Alerta Ativado");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"não silenciado",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		silenciarChamadoTrue: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/silenciar/true', chamado)
			.then(function(response){
				toastr.info("Alterta desativado");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"não silenciado",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		altera: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/alterar', chamado)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Alterado!!!", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"não Alterado",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		relatorioChamadoSuporte: function(pages, maxResults){
			var config = {params: {page: pages , maxResults : maxResults}};
			return $http.get('/rest/chamado/chamadoTi/suporte/relatorio/', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		buscarPorId: function(chamado){
			return $http.get('/rest/chamado/chamadoManutencao/buscaPorId/'+chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listaSuporte: function(){
			return $http.get('/rest/chamado/chamadoManutencao/suporte/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
		listaUsuario: function(){
			return $http.get('/rest/chamado/chamadoManutencao/usuario/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		prioridade: function(){
			return $http.get('/rest/chamado/chamadoManutencao/prioridade')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		status : function(){
			return $http.get('/rest/chamado/chamadoTi/status')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		titulo : function(){
			return $http.get('/rest/chamado/chamadoManutencao/titulo')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
	}
});