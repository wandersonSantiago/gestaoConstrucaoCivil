app.factory('adminSistemaService', function($rootScope, toastr, $http, $q){
	
	
	return{
		
		buscaPorId: function(param){
			console.log("service admin" );
			return $http.get('rest/empresaContratada/buscaPorId/'+param)
			.then(function(response){
				//sweetAlert({ timer : 3000, text :"falha de conexão", type : "error", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
					sweetAlert({ timer : 3000,  text :"falha de conexão", type : "error", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
				});
		},	
		lista: function(){
			return $http.get('rest/empresaContratada/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha de conexão", type : "error", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
				
		salva: function(empresa){
			return $http.post('rest/empresaContratada/salva', empresa)
			.then(function(response){
				sweetAlert({ timer : 3000,  text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão", type : "error", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		altera: function(empresa){
			return $http.put('/rest/empresaContratada/altera',empresa)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		exclui: function(id){
			return $http.delete('/rest/empresaContratada/exclui' +id)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão", type : "error", width: 300, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		}
	}
});