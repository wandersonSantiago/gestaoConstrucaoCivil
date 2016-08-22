app.controller('empreendimentoController', function($scope, buscaCepService, empreendimentoService,   $routeParams){
	
	var self = this;
  
	var idEmpreendimento = $routeParams.idEmpreendimento;
	
	$scope.listaOutros =[];
	
	


	self.findCep = function () {
		
		self.cep = $scope.empCtrl.empreendimento.enderecoEmpreendimento.cep;
		console.log(self.cep );
		buscaCepService.get({'cep': self.cep}).$promise
		.then(function success(result){
			$scope.empCtrl.empreendimento.enderecoEmpreendimento = result;
		

	
		}).catch(function error(msg) {
			console.error('Error');
		});
		
    }
	
	
	
	self.ativarExcluirLote = function(listaOutros){
		$scope.listaOutros.filter(function(outro){
		if(outro.selecionado){
			$scope.ativadoExcluirLote = true;
		}
		});
	}
		

	//apagar outros empreendimentos, somente da lista de front
	self.apagarOutros = function(listaOutros){
			$scope.listaOutros = listaOutros.filter(function(outro){
			
			if(!outro.selecionado) return outro;
			$scope.ativadoExcluirLote = null;
		});
		
		
	}
	
	//verifica qual tipo de empreendimento, para renderizar os componentes corretos
	self.verificar = function(){
	/*if(self.configEmpreendimentoEdificio == null){
		$scope.verificaTipoEdificio = false;
		$scope.verificaTipoCasa = false;
		console.log("nullo empre");
	}*/
		if(self.configEmpreendimento.empreendimento.tipoEmpreendimento == "CONDOMINIO_DE_EDIFICIO_RESIDENCIAL"){
			$scope.verificaTipoCasa = false;
			$scope.verificaTipoEdificio = true;
			
		}else
		if(self.configEmpreendimento.empreendimento.tipoEmpreendimento == "CONDOMINIO_DE_CASA"){
			$scope.verificaTipoEdificio = false;
			$scope.verificaTipoCasa = true;
		
		}
	}
	
	
	//cria uma lista de outros
	self.adicionarOutros = function(){
			
		$scope.listaOutros.push({
			
			descricao : descricaoOutros.value
		});
										
					descricaoOutros.value = "";
					$scope.descricaoOutro = null;
	}
	
	
	self.createEmpreendimento = function(empreendimento){
		empreendimentoService.empreendimentoCreate(self.empreendimento);
		self.empreendimento = empreendimento;
		
	}
	
	
	self.createConfigEmpreendimento = function(configEmpreendimentoEdificio, configEmpreendimentoCasa, listaOutros){
		
		if(self.configEmpreendimentoEdificio){
			self.configEmpreendimentoEdificio.empreendimento = $scope.empCtrl.configEmpreendimento.empreendimento;
			empreendimentoService.configEmpreendimentoEdificioCreate(self.configEmpreendimentoEdificio);

			self.configEmpreendimentoEdificio = null;
			
			
		}else if(self.configEmpreendimentoCasa){
			self.configEmpreendimentoCasa.empreendimento = $scope.empCtrl.configEmpreendimento.empreendimento;
			empreendimentoService.configEmpreendimentoCasaCreate( self.configEmpreendimentoCasa);
	
			self.configEmpreendimentoCasa = null;
		
		}

	}
	
	
	self.updateEmpreendimento = function(empreendimento){
		empreendimentoService.empreendimentoUpdate(self.empreendimento);
		self.empreendimento = empreendimento;
		
	}
	
	self.empreendimentoSemConfiguracaoFindAll = function(){
		 empreendimentoService.empreendimentoSemConfiguracaoFindAll().
			then(function(t){
				$scope.empreendimentoSemConfiguracao = t;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar empreendimento');
			});
		};
		
	 self.buscarEmpreendimentos = function(){
		 empreendimentoService.empreendimentoFindAll().
			then(function(t){
				$scope.empreendimentos = t;
				}, function(errResponse){
				toastr.error('Erro ao tentar buscar empreendimento');
			});
		};
		
		 self.buscarTipoEmpreendimentos = function(){
			 empreendimentoService.tipoEmpreendimentoFindAll().
				then(function(t){
					$scope.listarTipoEmpreendimento = t;
					}, function(errResponse){
					toastr.error('Erro ao tentar buscar tipo empreendimento');
				});
			};
		
//busca a empreendimento atraves do id
		self.buscarEmpreendimentoPorId = function(id){
			if(!id)return;
			empreendimentoService.empreendimentoFindOne(id).
			then(function(p){
				self.empreendimento = p;
				}, function(errResponse){
				toastr.error('Erro ao buscar empreendimento');
			});
		};
	//verifica se o params esta com o ide executa o metodo de busca 	
		if(idEmpreendimento){
			self.buscarEmpreendimentoPorId(idEmpreendimento);
			
		}
				
		
			
	$scope.maskFone = '(99) 9999 - 999?9';
	$scope.maskCnpj = '99.999.999/9999-99';
	$scope.maskIscEstadual = '999.999.999.999';
	$scope.maskCep = '99999-999';
	$scope.maskData = '9999-99-99';
	
});
