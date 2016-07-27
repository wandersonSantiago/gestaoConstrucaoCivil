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
		
	}
});