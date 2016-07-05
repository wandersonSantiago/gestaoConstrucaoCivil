app.factory('cargoService', function($http)
{

	return {
	
		cargoCreate: function(id){
		return $http.delete('rest/cargo/cadastrarCargo' +id)
		.then(function(response){
			return response.data;
		},function(errResponse){
			console.error('Erro ao tentar apagar empresa');
			return $q.reject(errResponse);
		});
	}
	}
});