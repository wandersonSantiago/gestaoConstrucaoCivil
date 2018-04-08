app.controller('categoriaController', function($scope, categoriaService, $routeParams){
	
	var self = this;
		
	
	var idCategoria = $routeParams.idCategoria;
	
	 self.salva = function(categoria){
		 categoriaService.salva(self.categoria).
			then(function(t){
				self.categoria = null;
				}, function(errResponse){
			});
	}
	 
	 self.altera = function(categoria){
		 categoriaService.altera(self.categoria).
			then(function(t){
				self.categoria = null;
				}, function(errResponse){
			});
	 }
	 
	 
	 self.lista = function(){
		 categoriaService.lista().
			then(function(t){
				self.categorias = t;
				}, function(errResponse){
			});
		};
		
		self.listaTipoCategoria = function(){
			 categoriaService.listaTipoCategoria().
				then(function(t){
					self.tipoCategoria = t;
					}, function(errResponse){
				});
			};
			
		self.buscaPorId = function(id){
			if(!id)return;
			categoriaService.buscaPorId(id).
			then(function(p){
				self.categoria = p;
				}, function(errResponse){
		});
		};
		
	
		if(idCategoria){
			self.buscaPorId(idCategoria);
		}
		
});