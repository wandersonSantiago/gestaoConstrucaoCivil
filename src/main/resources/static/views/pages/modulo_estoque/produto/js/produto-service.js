app.factory('ProdutoService', function($rootScope, toastr, $http,$q){
	
	var url = "/rest/estoque/produto";
	
	return{
		insert: function(produto){	
			return $http.post(url, produto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		update: function(produto){			
			return $http.put(url, produto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		findByTextAndPagination: function(text , page , maxResults){
			var config = {params: {descricao : text, page: page , maxResults : maxResults}};
			return $http.get(url + '/descricao', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		findById: function(param){
			return $http.get(url +'/'+ param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		findByUnidadesMedida: function(){
			return $http.get(url + "/unidades-medida")
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},	
		findByDescricao: function(texto){
			var config = {params: {descricao:texto}};
			return $http.get(url + '/descricao', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
	}
});