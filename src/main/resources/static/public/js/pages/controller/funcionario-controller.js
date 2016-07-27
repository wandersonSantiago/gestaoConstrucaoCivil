app.controller('cadastrarFuncionarioController', function($scope, cargoService, empreendimentoService,
		cadastrarFuncionarioService, $routeParams) {

	var self = this;
	$scope.listaCargo = [];
	$scope.listarEmpreendimento = [];

	self.cadastrarFuncionario = function(funcionario) {
		console.log("funfou o controle");
		console.log(self.funcionario);
		console.log("TESTE:" + self.funcionario.dataNascimento);
		cadastrarFuncionarioService.funcionarioCreate(self.funcionario);

		self.funcionario = funcionario;

	}

	
	cargoService.cargoFindAll().
	  then(function(c){
		  $scope.listaCargo = c;
	  }, function(errResponse){
			console.error('Erro ao tentar buscar cargo');
			return $q.reject(errResponse);
		});

	empreendimentoService.empreendimentoFindAll().
	  then(function(t){
		  $scope.listarEmpreendimento = t;
	  }, function(errResponse){
			console.error('Erro ao tentar buscar empreendimento');
			return $q.reject(errResponse);
		});
	

	

	$scope.maskFone = '(99) 9999 - 999?9';
	$scope.maskCnpj = '99.999.999/9999-99';
	$scope.maskIscEstadual = '999.999.999.999';
	$scope.maskCep = '99999-999';
	$scope.maskData = '9999-99-99';

});