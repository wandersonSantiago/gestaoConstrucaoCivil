app.factory('CategoriaFinanceiroService', function($rootScope, toastr, $http,$q){
	
	var url = 'rest/financeiro/categoria/';
	return{
		
		insert: function(categoria){
			return $http.post(url,categoria)
			.then(function(response){
			},function(errResponse){
				return $q.reject(errResponse);
				});
		},
		
		findByCategoriaFinanceiroByDescricao: function(texto, pagina){
			var config = {params: {page: pagina, descricao:texto}};
			return $http.get(url + 'descricao', config)
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
		
		findAllIdTipo: function(param){
			return $http.get(url + "tipo/" + param)
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
		tipos: function(){
			return $http.get(url +"tipo/")
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
	}
});