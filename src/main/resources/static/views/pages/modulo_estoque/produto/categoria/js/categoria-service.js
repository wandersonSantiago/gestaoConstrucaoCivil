app.factory('CategoriaService', function($rootScope, toastr, $http,$q){
	
	var url = 'rest/almoxarifado/categoria/';
	return{
		
		insert: function(categoria){
			return $http.post(url,categoria)
			.then(function(response){
			},function(errResponse){
				return $q.reject(errResponse);
				});
		},
		
		findByDescricaoPagination: function(texto, pagina){
			var config = {params: {page: pagina, descricao:texto}};
			return $http.get(url + '/descricao', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		findById: function(param){
			return $http.get(url + param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		update: function(categoria){
			return $http.put(url , categoria)
			.then(function(response){
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
		
	}
});