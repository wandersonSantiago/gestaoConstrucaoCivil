app.controller('categoriaController', function($scope, categoriaService, $routeParams){
	
	var self = this;
		
	
	var idCategoria = $routeParams.idCategoria;
	
	 self.createCategoria = function(categoria){
		 categoriaService.categoriaCreate(self.categoria);
		 self.categoria = categoria;
	}
	 
	 self.alterarCategoria = function(categoria){
		 categoriaService.updateCategoria(self.categoria);
		 self.categoria = categoria;
	 }
	 
	 
	 self.buscarCategoria = function(){
		 categoriaService.categoriaFindAll().
			then(function(t){
				self.categorias = t;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar Categoria');
			});
		};
		
		self.buscarCategoriaPorId = function(id){
			if(!id)return;
			categoriaService.categoriaFindOne(id).
			then(function(p){
				self.categoria = p;
				}, function(errResponse){
				toastr.error('Erro ao buscar pacote de serviço');
			});
		};
		
	
		if(idCategoria){
			
			self.buscarCategoriaPorId(idCategoria);
		}
		
});