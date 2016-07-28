app.factory('adminSistemaService', function($rootScope, toastr, $http, $q){
	
	
	return{
		
		buscarEmpresa: function(param){
			console.log("service admin" );
			return $http.get('rest/adminSistema/listarEmpresaId/'+param)
			.then(function(response){
				return response.data;
			});
			
		},	
		empresaFindAll: function(){
			return $http.get('rest/adminSistema/listarEmpresa')
			.then(function(response){
				return response.data;
			},function(errResponse){
				toastr.error('Erro ao Buscar Empresas');
				console.error('Erro ao tentar buscar Empresa');
				return $q.reject(errResponse);
			});
		},
				
		empresaCreate: function(empresa){
			return $http.post('rest/adminSistema/cadastrarEmpresa', empresa)
			.then(function(response){
				toastr.info('Empresa cadastrado');
				return response.data;
			},function(errResponse){
				toastr.error('Empresa não cadastrado');
				return $q.reject(errResponse);
			});
		},
		empresaUpdate: function(empresa){
			return $http.put('/rest/adminSistema/alterarEmpresa',empresa)
			.then(function(response){
				toastr.info('Empresa  Alterado');
				return response.data;
			},function(errResponse){
				toastr.error('Empresa não Alterado');
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