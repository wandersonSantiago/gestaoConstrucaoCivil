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
	
	
	self.salva = function(funcionario) {
		cadastrarFuncionarioService.salva(self.funcionario).
		then(function(response){
			self.funcionario = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(funcionario) {
		cadastrarFuncionarioService.altera(self.funcionario).
		then(function(response){
			self.funcionario = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 cadastrarFuncionarioService.lista().
			then(function(f){
				self.listaFuncionarios = f;
				$scope.listaFuncionario = f;				
				}, function(errResponse){
			});
		};
		 self.estadoCivil = function(){
			 cadastrarFuncionarioService.estadoCivil().
				then(function(f){
					self.listaEstadoCivil = f;									
					}, function(errResponse){
				});
			};
		
		self.buscarFuncionariosEngenheiro = function(){
			 cadastrarFuncionarioService.buscarFuncionariosEngenheiro().
				then(function(f){
					$scope.engenheiro = f;
					}, function(errResponse){
				});
			};
			
//busca o funcionario atraves do id
		
		self.buscarPorId = function(id){
			if(!id)return;
			cadastrarFuncionarioService.buscarPorId(id).
			then(function(p){
				self.funcionario = p;
		}, function(errResponse){
			});
		};
//verifica se o params esta com o ide executa o metodo de busca 	
		if(idFuncionario){
			self.buscarPorId(idFuncionario);
			
		}

});