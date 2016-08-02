app.factory('tipoEmpreendimentoService', function($rootScope, toastr, $http){
	
	
	return{
		
		tipoEmpreendimentoFindAll: function(){
			return $http.get('rest/empreendimento/tiposEmpreendimentos')
			.then(function(response){
				return response.data;
			},function(errResponse){
				toastr.error('erro ao buscar tipo de empreendimento');
				console.error('Erro ao tentar buscar tipo');
				return $q.reject(errResponse);
			});
		},
		tipoEmpreendimentoCreate: function(tipoEmpreendimento){
			console.log(tipoEmpreendimento);
			return $http.post('rest/tipoEmpreendimento/cadastrarTipoEmpreendimento', tipoEmpreendimento)
			
			.then(function(response){
				console.log("gravado");
				$rootScope.gravado = true;
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar empresa');
				$rootScope.naoGravado = true;
				return $q.reject(errResponse);
				
			});
		},
		
		tipoEmpreendimentoFindOne: function(id){
			return $http.get('rest/adminSistema/buscaEmpresa' +id)
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar Buscar empresa');
				return $q.reject(errResponse);
			});
		},
		tipoEmpreendimentoUpdate: function(item, id){
			return $http.put('/rest/adminSistema/updateEmpresa' +id, item)
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar Alterar empresa');
				return $q.reject(errResponse);
			});
		},
		tipoEmpreendimentoDelete: function(id){
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