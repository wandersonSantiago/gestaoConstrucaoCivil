app.controller('enderecoController', function($scope, enderecoService, $routeParams){
	
	var self = this;

	
	
	
	 self.lista = function(){
		 enderecoService.lista().
			then(function(c){
				$scope.ufs = c;
				}, function(errResponse){
			
			});
		};

	
});