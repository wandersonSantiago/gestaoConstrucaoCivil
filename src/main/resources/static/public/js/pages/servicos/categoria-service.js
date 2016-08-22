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
		categoriaFindAll: function(){
			return $http.get('rest/almoxarifado/listarCategoria')
			.then(function(response){
				return response.data;
			},function(errResponse){
				toastr.error('erro ao buscar categorias');
				console.error('Erro ao tentar buscar os categorias');
				return $q.reject(errResponse);
			});
		},
		
		categoriaFindOne: function(param){
			return $http.get('rest/almoxarifado/listarCategoriaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				console.error('Erro ao tentar Buscar categoria');
				return $q.reject(errResponse);
			});
		},
		
		updateCategoria: function(categoria){
			return $http.put('rest/almoxarifado/alterarCategoria', categoria)
			.then(function(response){
				toastr.info('catgeoria Alterado empresa');
				return response.data;
			},function(errResponse){
				toastr.error('Erro ao tentar Alterar categoria');
				return $q.reject(errResponse);
			});
		},
		
	}
});