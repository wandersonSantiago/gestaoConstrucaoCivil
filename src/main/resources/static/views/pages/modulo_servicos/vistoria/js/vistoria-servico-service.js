app.factory('VincularService', function($rootScope, toastr, $http,$q){
	
	var url = '/rest/servicos/vincular';
	return{
		insert: function(obj){
			return $http.post(url, obj)
			.then(function(response){
				return response.data;
			},function(errResponse){
					return $q.reject(errResponse);
			});
		},
		
		update: function(obj){
			return $http.put(url, obj)
			.then(function(response){
				return response.data;
			},function(errResponse){
					return $q.reject(errResponse);
			});
		},
		
		findByTextAndPagination: function(text , page ,  orderBy, direction , maxResults){
			var config = {params: {descricao : text, page: page , maxResults : maxResults, orderBy : orderBy, direction : direction}};
			return $http.get(url + '/buscar', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		findById: function(param){
			return $http.get(url + '/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
			
	}
});