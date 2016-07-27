app.factory('servicoEmpresaService', function($rootScope, toastr, $http,$q){
	
	
	return{
		servicoEmpresaCreate: function(servicoEmpresa){
			return $http.post('/rest/servicoEmpresa/cadastrarServicoEmpresa', servicoEmpresa)
			
			.then(function(response){
			
				toastr.info('serviço cadastrado');
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar o servico da empresa');
				toastr.error('serviço não cadastrado');
				return $q.reject(errResponse);
				
			});
		},
		fornecedorFindAll: function(){
			return $http.get('rest/servicoEmpresa/cadastrarServicoEmpresa/listarServicoEmpresa')
			.then(function(response){
				return response.data;
			},function(errResponse){
				toastr.error('Erro ao tentar buscar os serviços das empresa');
				console.error('Erro ao tentar buscar os serviços das empresa');
				return $q.reject(errResponse);
			});
		},
		
	}
});