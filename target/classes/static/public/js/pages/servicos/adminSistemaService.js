app.factory('adminSistemaService', function($http){
	
	return{
		
		empresaFindAll: function(){
			return $http.get('rest/adminSistema/listarEmpresa')
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar buscar Empresa');
				return $q.reject(errResponse);
			});
		},
		empresaCreate: function(empresa){
			console.log(empresa.razaoSocial);
			console.log(empresa);
			return $http.post('rest/adminSistema/cadastrarEmpresa', empresa)
			
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar gravar empresa');
				return $q.reject(errResponse);
			});
		},
		
		empresaFindOne: function(id){
			return $http.get('rest/adminSistema/buscaEmpresa' +id)
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar Buscar empresa');
				return $q.reject(errResponse);
			});
		},
		empresaUpdate: function(item, id){
			return $http.put('/rest/adminSistema/updateEmpresa' +id, item)
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar Alterar empresa');
				return $q.reject(errResponse);
			});
		},
		empresaDelete: function(id){
			return $http.delete('/rest/adminSistema/deletarEmpresa' +id)
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar apagar empresa');
				return $q.reject(errResponse);
			});
		}
	}
});