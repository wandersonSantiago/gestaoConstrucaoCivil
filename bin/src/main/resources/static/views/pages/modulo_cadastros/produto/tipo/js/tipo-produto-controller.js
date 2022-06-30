app.controller('tipoProdutoController', function($scope, $rootScope,  tipoProdutoService, buscaCepService, $routeParams){
	
	var self = this;
  
	self.TipoProduto = null;
	
	var idTipoProduto =  $routeParams.idTipoProduto;
	

	
	
	self.salva = function(tipoProduto, sucesso){
		
		tipoProdutoService.salva(self.tipoProduto).
		then(function(response){
			self.tipoProduto = tipoProduto;
		}, function(errResponse){
			
		});
		
		
	}
	
	self.altera = function(tipoProduto, sucesso){
		tipoProdutoService.altera(self.tipoProduto).
		then(function(e){
			self.tipoProduto = null;
		}, function(errResponse){
		});
}
	

	self.lista = function(){
		tipoProdutoService.lista().
		then(function(e){
			self.listaTipoProduto = e;
		}, function(errResponse){
		});
	};

	

	self.buscaPorId = function(id){
		if(!id)return;
		tipoProdutoService.buscaPorId(id).
		then(function(p){
			self.tipoProduto = p;
			console.log(self.tipoProduto );
		}, function(errResponse){
		});
	};
	
	if(idTipoProduto){
		self.buscaPorId(idTipoProduto);
	}
	
	
});