app.controller('servicoEmpresaController', function($scope,servicoEmpresaService, $routeParams){
	
	var self = this;
		
	
	
	 self.salvaEdificio = function(servicoEdifico , pacoteServico , prestadoraServico){
		 servicoEdifico.pacoteServico = pacoteServico;
		 servicoEdifico.prestadoraServico = prestadoraServico;
		 servicoEmpresaService.salvaEdificio(servicoEdifico).
			then(function(response){
				self.servicoEdificio = null;
				}, function(errResponse){
			});
		}
	 
	 self.alteraEdificio = function(servicoEdifico , pacoteServico , prestadoraServico){
		 servicoEdifico.pacoteServico = pacoteServico;
		 servicoEdifico.prestadoraServico = prestadoraServico;
		 servicoEmpresaService.alteraEdificio(servicoEdifico).
			then(function(response){
				self.servicoEmpresa = null;
				}, function(errResponse){
			});
		}
	 
	 self.listaEdificio = function(){
		 servicoEmpresaService.listaEdificio().
			then(function(t){
				$scope.listaEdificio = t;
				}, function(errResponse){
			});
		};
		
		 self.salvaCasa = function(servicoCasa , pacoteServico , prestadoraServico){
			 servicoCasa.pacoteServico = pacoteServico;
			 servicoCasa.prestadoraServico = prestadoraServico;
			 servicoEmpresaService.salvaCasa(servicoCasa).
				then(function(response){
					self.servicoEdificio = null;
					}, function(errResponse){
				});
			}
		 
		 self.alteraCasa = function(servicoCasa , pacoteServico , prestadoraServico){
			 servicoCasa.pacoteServico = pacoteServico;
			 servicoCasa.prestadoraServico = prestadoraServico;
			 servicoEmpresaService.alteraCasa(servicoCasa).
				then(function(response){
					self.servicoEmpresa = null;
					}, function(errResponse){
				});
			}
		 
		 self.listaCasa = function(){
			 servicoEmpresaService.listaCasa().
				then(function(t){
					$scope.listaCasa = t;
					}, function(errResponse){
				});
			};
			
			 self.salvaEdificacoesComunitaria = function(descricao , pacoteServico , prestadoraServico){
				 servicoOutros = {};
				 servicoOutros.descricao = descricao.descricao;
				 servicoOutros.pacoteServico = pacoteServico;
				 servicoOutros.prestadoraServico = prestadoraServico;
				 console.log(servicoOutros);
				 servicoEmpresaService.salvaEdificacoesComunitaria(servicoOutros).
					then(function(response){
						self.servicoEdificio = null;
						}, function(errResponse){
					});
				}
			 
			 self.alteraEdificacoesComunitaria = function(servicoOutros , pacoteServico , prestadoraServico){
				 servicoOutros.pacoteServico = pacoteServico;
				 servicoOutros.prestadoraServico = prestadoraServico;
				 servicoEmpresaService.alteraEdificacoesComunitaria(servicoOutros).
					then(function(response){
						self.servicoEmpresa = null;
						}, function(errResponse){
					});
				}
			 
			 self.listaEdificacoesComunitaria = function(){
				 servicoEmpresaService.listaEdificacoesComunitaria().
					then(function(t){
						$scope.listaEdificacoesComunitaria = t;
						}, function(errResponse){
					});
				};
		
		$scope.tipo = {
				tipo : ''	
			};		
			self.tipoConf = function(){
			if($scope.tipo.tipo == "edificio"){
				$scope.casa = false;
				$scope.comunitaria = false;
				$scope.edificio = true;
		}
			if($scope.tipo.tipo == "comunitaria"){
				$scope.casa = false;
				$scope.edificio = false;
				$scope.comunitaria = true;
			}
			if($scope.tipo.tipo == "casa"){
				$scope.comunitaria = false;
				$scope.edificio = false;
				$scope.casa = true;
				
			}
			}

	
		
});