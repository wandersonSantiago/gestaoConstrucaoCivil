app.controller('empreendimentoController', function($scope, buscaCepService, empreendimentoService,   $routeParams){
	
	var self = this;
	var idEmpreendimento = $routeParams.idEmpreendimento;
	$scope.listaOutros =[];
	

	self.findCep = function () {
		
		self.cep = $scope.empCtrl.empreendimento.enderecoEmpreendimento.cep;
		buscaCepService.get({'cep': self.cep}).$promise
		.then(function success(result){
			$scope.empCtrl.empreendimento.enderecoEmpreendimento = result;
		}).catch(function error(msg) {
		});
		
    }
	
	self.salva = function(empreendimento){
		console.log(self.empreendimento.dataAbertura);
		empreendimentoService.salva(self.empreendimento)
		. then(function(response){
			self.empreendimento = empreendimento;
			 },
				function(errResponse){
			 	});
		}
	
	self.altera = function(empreendimento){
		empreendimentoService.altera(self.empreendimento)
		. then(function(response){
			self.empreendimento = empreendimento;
				 },
				function(errResponse){
			 	});
		}
	

	 self.lista = function(){
		 empreendimentoService.lista().
			then(function(t){
				$scope.empreendimentos = t;
				}, function(errResponse){
			});
		};
		
		 		
//busca a empreendimento atraves do id
		self.buscaPorId = function(id){
			if(!id)return;
			empreendimentoService.buscaPorId(id).
			then(function(p){
				self.empreendimento = p;
				}, function(errResponse){
			});
		};
	//verifica se o params esta com o ide executa o metodo de busca 	
		if(idEmpreendimento){
			self.buscaPorId(idEmpreendimento);
			
		}
	
});
