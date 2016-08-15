app.controller('categoriaController', function($scope, categoriaService, $routeParams){
	
	var self = this;
		
	
	var idCategoria = $routeParams.idCategoria;
	
	 self.createCategoria = function(categoria){
		 console.log(self.categoria);
		 categoriaService.categoriaCreate(self.categoria);
	}
	 
	 
	 self.buscarCategoria = function(){
		 categoriaService.categoriaFindAll().
			then(function(t){
				$scope.listaCategoria = t;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar Categoria');
			});
		};
});