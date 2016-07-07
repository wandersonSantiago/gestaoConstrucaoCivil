app.controller('cadastrarFuncionarioController', function($scope, cadastrarFuncionarioService, $routeParams){
	
	var self = this;
  
	
		
	self.cadastrarFuncionario = function(funcionario){
		console.log("funfou o controle");
		console.log(self.funcionario);
		console.log("TESTE:" + self.funcionario.nomeCompleto);
		cadastrarFuncionarioService.funcionarioCreate(self.funcionario);
	
			self.funcionario = funcionario;
		
	}
	
	
	
	
	
	$scope.maskFone= '(99) 9999 - 999?9';
	$scope.maskCnpj= '99.999.999/9999-99';
	$scope.maskIscEstadual= '999.999.999.999';
	$scope.maskCep = '99999-999';
	
	
	
});