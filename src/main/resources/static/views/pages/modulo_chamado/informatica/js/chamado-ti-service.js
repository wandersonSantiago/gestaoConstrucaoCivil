app.factory('chamadoTiService', function($rootScope, toastr, $http){
	
	
	return{
		salva: function(chamado){
			return $http.post('/rest/chamado/chamadoTi/salvar', chamado)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		salvaMensagem: function(mensagem){
			return $http.put('/rest/chamado/chamadoTi/mensagem', mensagem)
			.then(function(response){
				toastr.info("Mensagem enviada");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"Mensagem não Enviada",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		salvaServicos: function(chamado){
			return $http.put('/rest/chamado/chamadoTi/servicos', chamado)
			.then(function(response){
				toastr.info("Serviços Salvo");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"Erro",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		atenderChamado: function(chamado){
			return $http.put('/rest/chamado/chamadoTi/atender', chamado)
			.then(function(response){
				toastr.info("Chamado Em andamento");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"Erro",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		silenciarChamadoFalse: function(chamado){
			return $http.put('/rest/chamado/chamadoTi/silenciar/false', chamado)
			.then(function(response){
				toastr.info("Alerta Ativado");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"não silenciado",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		silenciarChamadoTrue: function(chamado){
			return $http.put('/rest/chamado/chamadoTi/silenciar/true', chamado)
			.then(function(response){
				toastr.info("Alerta desativado");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"não silenciado",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		fecharChamado: function(chamado){
			return $http.put('/rest/chamado/chamadoTi/fechar', chamado)
			.then(function(response){
				toastr.info("Chamado Fechado");
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"não encerrado",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		altera: function(chamado){
			return $http.put('/rest/chamado/chamadoTi/alterar', chamado)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(chamado){
			return $http.get('/rest/chamado/chamadoTi/buscaPorId/'+chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listaSuporte: function(){
			return $http.get('/rest/chamado/chamadoTi/suporte/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
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
		listaUsuario: function(){
			return $http.get('/rest/chamado/chamadoTi/usuario/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		prioridade: function(){
			return $http.get('/rest/chamado/chamadoTi/prioridade')
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
		tipoEquipamento : function(){
			return $http.get('/rest/chamado/chamadoTi/equipamento/tipo')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		titulo : function(){
			return $http.get('/rest/chamado/chamadoTi/titulo/tI')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		tituloImpressora : function(){
			return $http.get('/rest/chamado/chamadoTi/titulo/impressora')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
	}
});