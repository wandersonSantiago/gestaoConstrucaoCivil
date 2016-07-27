app.controller('produtoController', function($scope,produtoService, $routeParams){
	
	var self = this;
		
	$scope.listaProduto = [];
	
	
	 self.cadastrarProduto = function(produto){
		 console.log(self.produto);
		 produtoService.produtoCreate(self.produto);
	}
	 
	
	 produtoService.produtoFindAll().
	  then(function(produto){
		  $scope.listaProduto = produto;
	  }, function(errResponse){
			console.error('Erro ao tentar buscar produto');
			return $q.reject(errResponse);
		});
	 
	
});