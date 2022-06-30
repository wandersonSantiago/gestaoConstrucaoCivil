app.controller('cadastrarFuncionarioController', function($scope, buscaCepService, cadastrarFuncionarioService, cargoService, $routeParams) {

	var self = this;
	self.getPage=0;
	self.totalPages = 0;
	self.totalElements = 0;
	$scope.maxResults = 15;	
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
		console.log(self.funcionario);
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
		
		 self.listaComPaginacao = function(pages, maxResults){
				self.totalPages = [];
				self.getPage=pages;	
			 cadastrarFuncionarioService.listaComPaginacao(pages, maxResults).
				then(function(t){
					self.listaFuncionarios = t.content;
					$scope.totalPages = t.totalPages;
					self.totalElements = t.totalElements;
					for(i = 0; i < $scope.totalPages ; i++){
						self.totalPages.push(i);
					}			
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
			
		
		self.buscarPorId = function(id){
			if(!id)return;
			cadastrarFuncionarioService.buscarPorId(id).
			then(function(p){
				self.funcionario = p;
				console.log(p);
				self.funcionario.dataNascimento = new Date(self.funcionario.dataNascimento);
				self.funcionario.dataAdmissao = new Date(self.funcionario.dataAdmissao);
				
		}, function(errResponse){
			});
		};
		if(idFuncionario){
			self.buscarPorId(idFuncionario);
			
		}

});