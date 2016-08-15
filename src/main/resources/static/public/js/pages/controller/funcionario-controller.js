app.controller('cadastrarFuncionarioController', function($scope, buscaCepService, cadastrarFuncionarioService, cargoService, $routeParams) {

	var self = this;
	
	var idFuncionario = $routeParams.idFuncionario;
	
	

	self.findCep = function () {
		
		self.cep = $scope.cadFuncCtrl.funcionario.endereco.cep;
		buscaCepService.get({'cep': self.cep}).$promise
		.then(function success(result){
			$scope.cadFuncCtrl.funcionario.endereco = result;
			}).catch(function error(msg) {
			console.error('Error');
		});
		
    }
	
	
	self.cadastrarFuncionario = function(funcionario) {
		cadastrarFuncionarioService.funcionarioCreate(self.funcionario);
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
		
		self.buscarFuncionariosEngenheiro = function(){
			 cadastrarFuncionarioService.funcionarioEngenheiroFindAll().
				then(function(f){
					$scope.engenheiro = f;
					}, function(errResponse){
					toastr.error('Erro ao tentar buscar funcionarios');
				});
			};
			
//busca o funcionario atraves do id
		
		self.buscarFuncionarioPorId = function(id){
			if(!id)return;
			cadastrarFuncionarioService.buscarFuncionario(id).
			then(function(p){
				self.funcionario = p;
				console.log(idFuncionario);
			}, function(errResponse){
				toastr.error('Erro ao buscar funcionario');
			});
		};
//verifica se o params esta com o ide executa o metodo de busca 	
		if(idFuncionario){
			self.buscarFuncionarioPorId(idFuncionario);
			
		}
		
		
			

	$scope.maskFone = '(99) 9999 - 999?9';
	$scope.maskCnpj = '99.999.999/9999-99';
	$scope.maskIscEstadual = '999.999.999.999';
	$scope.maskCep = '99999-999';
	$scope.maskData = '9999-99-99';

});