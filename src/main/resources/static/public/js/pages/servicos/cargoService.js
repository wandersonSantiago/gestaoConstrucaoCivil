app.factory('cargoService', function($http,$q , toastr)
{

	 return {
		 cargoCreate: function(cargo){
			 return $http.post('rest/cargo/cadastrarCargo', cargo)
				.then(function(response){
					toastr.info('Cargo cadastrado');
					return response.data;
				},function(errResponse){
					toastr.error('Cargo cadastrado');
					return $q.reject(errResponse);
				});
			},
			cargoFindAll: function(){
				return $http.get('rest/cargo/cadastrarCargo/listarCargo')
				.then(function(response){
					return response.data;
				},function(errResponse){
					toastr.error('erro ao buscar cargos');
					console.error('Erro ao tentar buscar cargo');
					return $q.reject(errResponse);
				});
			},
	 }
	 
});