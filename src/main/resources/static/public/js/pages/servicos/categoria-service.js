app.factory('categoriaService', function($rootScope, toastr, $http,$q){
	
	
	return{
		categoriaCreate: function(categoria){
			return $http.post('rest/almoxarifado/cadastrarCategoria',categoria)
			
			.then(function(response){
			
				toastr.info('Categoria cadastrado');
				
				return response.data;
			
			},function(errResponse){
				console.error('Erro ao tentar gravar a categoria');
				toastr.error('Categoria não cadastrado');
				return $q.reject(errResponse);
				
			});
		},
		fornecedorFindAll: function(){
			return $http.get('rest/almoxarifado/listarCategoria')
			.then(function(response){
				return response.data;
			},function(errResponse){
				toastr.error('erro ao buscar categorias');
				console.error('Erro ao tentar buscar os categorias');
				return $q.reject(errResponse);
			});
		},
		
	}
});