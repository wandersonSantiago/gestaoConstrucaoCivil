app.factory('cargoService', function($http,$q)
{

	 return {
		 cargoCreate: function(cargo){
			 return $http.post('rest/cargo/cadastrarCargo', cargo)
				.then(function(response){
					return response.data;
				},function(errResponse){
					console.error('Erro ao tentar gravar cargo');
					return $q.reject(errResponse);
				});
			},
			cargoFindAll: function(){
				return $http.get('rest/cargo/cadastrarCargo/listarCargo')
				.then(function(response){
					return response.data;
				},function(errResponse){
					console.error('Erro ao tentar buscar cargo');
					return $q.reject(errResponse);
				});
			},
	 }
});