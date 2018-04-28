app.factory('UsuarioService', function($rootScope, toastr, $http,$q){
	
	var url = '/rest/usuario';
	
	return{
		
		salvar: function(usuario){
			return $http.post(url , usuario)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		listar: function(){
			return $http.get(url)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		status: function(){
			return $http.get(url + '/status')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		buscarPorId: function(param){
			return $http.get(url + '/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		alterar: function(parametroUsuario){
			return $http.put(url , parametroUsuario)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		existeLogin: function(login){
			return $http.get(url + '/existeLogin/'+login)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
	
		salvarFoto: function(form){
			return $http.post(url + '/foto', form, {
	            transformRequest: angular.identity,
	            headers: {'Content-Type': undefined}
	        }).then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
		alterarSenha: function(idUsuario, senha, novaSenha){
			var config = {params: {idUsuario , senha , novaSenha }};
			return $http.put(url + '/'+idUsuario+'/senha/'+senha+'/nova-senha/'+novaSenha)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);

			});
		},
		alterarEmpreendimento: function(idEmpreendimento){
			return $http.put(url + '/' + idEmpreendimento)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);

			});
		},
		
		buscarPorTexto: function(texto, pagina){
			var config = {params: {page: pagina, descricao:texto}};
			return $http.get(url + '/descricao', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
	}
});