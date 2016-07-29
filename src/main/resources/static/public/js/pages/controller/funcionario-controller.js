app.controller('cadastrarFuncionarioController', function($scope, cadastrarFuncionarioService, cargoService, $routeParams) {

	var self = this;

	self.cadastrarFuncionario = function(funcionario) {
		adastrarFuncionarioService.funcionarioCreate(self.funcionario);
		self.funcionario = funcionario;

	}

	 self.buscarFuncionarios = function(){
		 cadastrarFuncionarioService.funcionarioFindAll().
			then(function(f){
				self.listaFuncionarios = f;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar funcionarios');
			});
		};
		
			

	$scope.maskFone = '(99) 9999 - 999?9';
	$scope.maskCnpj = '99.999.999/9999-99';
	$scope.maskIscEstadual = '999.999.999.999';
	$scope.maskCep = '99999-999';
	$scope.maskData = '9999-99-99';

});