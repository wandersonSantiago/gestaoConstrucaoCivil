app.factory('cadastrarFuncionarioService', function($rootScope, toastr, $http){
	
	
	return{
		funcionarioCreate: function(funcionario){
			return $http.post('/rest/recursosHumanos/cadastrarFuncionario', funcionario)
			
			.then(function(response){
				console.log("teste");
				toastr.info('Funcionario cadastrado');
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar funcionario');
				toastr.error('Funcionario não cadastrado');
				return $q.reject(errResponse);
				
			});
		},
		
		
		
		buscarFuncionario: function(param){
			return $http.get('/rest/recursosHumanos/listarFuncionarioPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				toastr.error('Erro ao Buscar Funcionario');
				return $q.reject(errResponse);
			});
		},
		
		funcionarioEngenheiroFindAll: function(){
			return $http.get('/rest/recursosHumanos/buscaFuncionarioEngenheiro')
			.then(function(response){
				return response.data;
			},function(errResponse){
				toastr.error('Erro ao Buscar Empresas');
				return $q.reject(errResponse);
			});
		},
			
		
		
		funcionarioFindAll: function(){
			return $http.get('/rest/recursosHumanos/listarFuncionarios')
			.then(function(response){
				return response.data;
			},function(errResponse){
				toastr.error('Erro ao Buscar Empresas');
				console.error('Erro ao tentar buscar Empresa');
				return $q.reject(errResponse);
			});
		},
		
	}
});