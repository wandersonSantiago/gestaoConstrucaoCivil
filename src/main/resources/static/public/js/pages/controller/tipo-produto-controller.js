app.controller('tipoProdutoController', function($scope, tipoProdutoService, buscaCepService, $routeParams){
	
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
	
//carrega a lista de empresa, quando acessa o controller
	self.lista = function(){
		tipoProdutoService.lista().
		then(function(e){
			self.listaTipoProduto = e;
		}, function(errResponse){
		});
	};

	
//busca a empresa atraves do id
	self.buscaPorId = function(id){
		if(!id)return;
		tipoProdutoService.buscaPorId(id).
		then(function(p){
			self.tipoProduto = p;
			console.log(self.tipoProduto );
		}, function(errResponse){
		});
	};
//verifica se o params esta com o ide executa o metodo de busca 	
	if(idTipoProduto){
		self.buscaPorId(idTipoProduto);
	}
	
	
});