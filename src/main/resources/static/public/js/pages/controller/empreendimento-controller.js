app.controller('empreendimentoController', function($scope, empreendimentoService,  $routeParams){
	
	var self = this;
  
	var idEmpreendimento = $routeParams.idEmpreendimento;
	
	$scope.listaOutros =[];
			
	self.adicionarOutros = function(){
		
		var outros = document.getElementById("descricaoOutros");
		
		$scope.listaOutros.push({
			
			descricao : descricaoOutros.value
		});
					console.log($scope.listaOutros);
					
					descricaoOutros.value = "";
	}
	
	
	self.createEmpreendimento = function(empreendimento){
		empreendimentoService.empreendimentoCreate(self.empreendimento);
		self.empreendimento = empreendimento;
		
	}
	
	self.createConfigEmpreendimento = function(configEmpreendimento, listaOutros){
		empreendimentoService.configEmpreendimento(self.configEmpreendimento, $scope.listaOutros );
	//	empreendimentoService.configEmpreendimentoOutrosCreate($scope.listaOutros);
		
		
	}
	
	
	self.updateEmpreendimento = function(empreendimento){
		empreendimentoService.empreendimentoUpdate(self.empreendimento);
		self.empreendimento = empreendimento;
		
	}
	
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