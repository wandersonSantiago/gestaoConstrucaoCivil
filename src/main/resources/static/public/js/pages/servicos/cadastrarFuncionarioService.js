app.factory('cadastrarFuncionarioService', function($rootScope, $http){
	
	
	return{
		funcionarioCreate: function(funcionario){
			return $http.post('rest/adminSistema/cadastrarEmpresa', funcionario)
			
			.then(function(response){
				console.log("teste");
				$rootScope.gravado = true;
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar funcionario');
				$rootScope.naoGravado = true;
				return $q.reject(errResponse);
				
			});
		},
		
	}
});