app.factory('empreendimentoService', function($rootScope, $http){
	
	
	return{
		
		empreendimentoFindAll: function(){
			return $http.get('rest/adminSistema/listarEmpresa')
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar buscar Empresa');
				return $q.reject(errResponse);
			});
		},
		empreendimentoCreate: function(empresa){
			return $http.post('rest/adminSistema/cadastrarEmpresa', empresa)
			
			.then(function(response){
				console.log("teste");
				$rootScope.gravado = true;
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar empresa');
				$rootScope.naoGravado = true;
				return $q.reject(errResponse);
				
			});
		},
		
		empreendimentoFindOne: function(id){
			return $http.get('rest/adminSistema/buscaEmpresa' +id)
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar Buscar empresa');
				return $q.reject(errResponse);
			});
		},
		empreendimentoUpdate: function(item, id){
			return $http.put('/rest/adminSistema/updateEmpresa' +id, item)
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar Alterar empresa');
				return $q.reject(errResponse);
			});
		},
		empreendimentoDelete: function(id){
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