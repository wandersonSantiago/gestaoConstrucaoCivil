app.controller('configEmpreendimentoController', function($scope, configEmpreendimentoService,   $routeParams){
	
	var self = this;
 
$scope.listaOutros =[];
	
	
	self.ativarExcluirLote = function(listaOutros){
		$scope.listaOutros.filter(function(outro){
		if(outro.selecionado){
			$scope.ativadoExcluirLote = true;
		}
		});
	}
		
		self.apagarOutros = function(listaOutros){
			$scope.listaOutros = listaOutros.filter(function(outro){
			if(!outro.selecionado) return outro;
			$scope.ativadoExcluirLote = null;
		});
}
		self.adicionarOutros = function(descricao, configEmpreendimento ){
			$scope.listaOutros.push({
				descricao : descricao,
				empreendimento : configEmpreendimento
			});
						console.log(descricao);
						$scope.descricao = "";
			}

	self.verificar = function(){
		if(self.configEmpreendimento.empreendimento.tipoEmpreendimento == "CONDOMINIO_DE_EDIFICIO_RESIDENCIAL"){
			$scope.verificaTipoCasa = false;
			$scope.verificaTipoEdificio = true;
		}else
		if(self.configEmpreendimento.empreendimento.tipoEmpreendimento == "CONDOMINIO_DE_CASA"){
			$scope.verificaTipoEdificio = false;
			$scope.verificaTipoCasa = true;
		}
	}
	
	

	self.salvaConfigEmpreendimento = function(configEmpreendimentoEdificio, configEmpreendimentoCasa, listaOutros){
		self.configEmpreendimentoOutros = $scope.listaOutros;
		
		
		if(self.configEmpreendimentoEdificio){
			self.configEmpreendimentoEdificio.empreendimento = $scope.configCtrl.configEmpreendimento.empreendimento;
			configEmpreendimentoService.configEmpreendimentoEdificioSalva(self.configEmpreendimentoEdificio)
			//self.configEmpreendimentoOutros.empreendimento = $scope.configCtrl.configEmpreendimento.empreendimento;
			configEmpreendimentoService.configEmpreendimentoOutrosSalva(self.configEmpreendimentoOutros)
			. then(function(response){
				console.log(self.configEmpreendimentoOutros);
				self.limpaCampos();
				sweetAlert({ timer : 3000,  type : "success", width: 200, higth: 100, padding: 20});
				 },
				function(errResponse){
					 sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
			 	});
				
				
		}else if(self.configEmpreendimentoCasa){
			self.configEmpreendimentoCasa.empreendimento = $scope.configCtrl.configEmpreendimento.empreendimento;
			configEmpreendimentoService.configEmpreendimentoCasaSalva( self.configEmpreendimentoCasa)
			configEmpreendimentoService.configEmpreendimentoOutrosSalva(self.configEmpreendimentoOutros)
			. then(function(response){
				self.limpaCampos();
				sweetAlert({ timer : 3000,  type : "success", width: 200, higth: 100, padding: 20});
			 },
			function(errResponse){
				 sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
		 		 
		 	});
		}
	}
	
		
	self.empreendimentoSemConfiguracaoLista = function(){
		configEmpreendimentoService.empreendimentoSemConfiguracaoLista().
			then(function(t){
				$scope.empreendimentoSemConfiguracao = t;
				}, function(errResponse){
				
			});
		};
		
		self.configEmpreendimentoOutrosLista = function(){
			configEmpreendimentoService.configEmpreendimentoOutrosLista().
				then(function(t){
					self.outrosLista = t;
					}, function(errResponse){
					
				});
			};
		
			self.quantidadeEdificio = function(){
				configEmpreendimentoService.quantidadeEdificio().
				then(function(t){
					self.qtdeEdificio = t;
					}, function(errResponse){
					
				});
			}
			
			self.quantidadeCasa = function(){
				configEmpreendimentoService.quantidadeCasa().
				then(function(t){
					self.qtdeCasa = t;
					}, function(errResponse){
					
				});
			}
			
			self.listaOutros = function(){
				configEmpreendimentoService.listaOutros().
				then(function(t){
					self.listaOutros = t;
					}, function(errResponse){
					
				});
			}
		self.limpaCampos = function(){
			self.configEmpreendimentoEdificio = null;
			self.configEmpreendimentoCasa = null;
			$scope.listaOutros = null;
			$scope.empreendimentoSemConfiguracao = null;
		}
		
		
});
