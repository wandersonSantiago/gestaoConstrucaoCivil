app.controller('prestadoraServicoController', function($scope, buscaCepService, prestadoraServicoService, $routeParams, $timeout){
	
	var self = this;
	self.getPage=0;
	self.totalPages = 0;
	self.totalElements = 0;
	$scope.maxResults = 15;		
	var idPrestadoraServico = $routeParams.idPrestadoraServico;
	
	

	

//BUSCA CEP
	self.findCep = function () {
		
		self.cep = $scope.prestadoraCtrl.prestadoraServico.dadoEmpresa.endereco.cep;
		buscaCepService.get({'cep': self.cep}).$promise
		.then(function success(result){
			$scope.prestadoraCtrl.prestadoraServico.dadoEmpresa.endereco = result;
			}).catch(function error(msg) {
		});
		
    }
	
	
	
//CADASTRAR
	 self.salva = function(prestadoraServico){
		 prestadoraServicoService.salva(self.prestadoraServico).
			then(function(response){
				self.prestadoraServico = null;
				}, function(errResponse){
			});
		 
	}
	 
	
//ALTERAR	 
	 	self.altera = function(prestadoraServico){
			prestadoraServicoService.altera(self.prestadoraServico).
			then(function(response){
				self.prestadoraServico = null;
				}, function(errResponse){
			});
		}
	 	

//BUSCAR		 
			self.buscaTodosComPaginacao = function(pages, maxResults){
				self.totalPages = [];
				self.getPage=pages;			
				prestadoraServicoService.buscaTodosComPaginacao(pages, maxResults).
				then(function(e){			
					self.prestadoraServicos = e.content;
					$scope.totalPages = e.totalPages;
					self.totalElements = e.totalElements;
					for(i = 0; i < $scope.totalPages ; i++){
						self.totalPages.push(i);
					}
				}, function(errResponse){
				});
			};
			
			self.lista = function(){	
				prestadoraServicoService.lista().
				then(function(e){			
					self.prestadoraServicos = e;	
					 $scope.ativaTabela = true;
				}, function(errResponse){
				});
			};
			
			self.buscaPorId = function(id){
				if(!id)return;
				prestadoraServicoService.buscaPorId(id).
					then(function(p){
						self.prestadoraServico = p;
							}, function(errResponse){
					});
				};
				
				if(idPrestadoraServico){
					
					self.buscaPorId(idPrestadoraServico);
				}
			 
				//==============================RELATORIO======================================================
				
				 $scope.ativaTabela = false;
			     $scope.ativaGrafico = false;
			     
			     $scope.porTotal = true;
			     $scope.porPeriodo = false;
			     
				self.exportar = function(tipoImpressao){ 
				      switch(tipoImpressao){ 
				          case 'pdf': $scope.$broadcast('export-pdf', {}); 
				                      break; 
				          case 'excel': $scope.$broadcast('export-excel', {}); 
				                      break; 
				          case 'doc': $scope.$broadcast('export-doc', {});
				                      break; 
				          default: console.log('no event caught'); 
				       }
					};
					
					 $scope.ativaBuscaRelatorio =  function(botao){
				    	 if(botao == 'periodo'){
				    		 $scope.porTotal = false;
				    	     $scope.porPeriodo = true;
				    	 }else if(botao == 'total'){
				    		 $scope.porTotal = true;
				    	     $scope.porPeriodo = false;;
				    	 }
				     };
				     
				     self.ativaBotaoTabelaGrafico =  function(botao){
				    	 if(botao === false){
				    		 $scope.ativaTabela = true;
				    		 $scope.ativaGrafico = false;
				    	 }else if(botao === true){
				    		 $scope.ativaGrafico = true;
				    		 $scope.ativaTabela = false;
				    	 }
				     };
				     
				     self.relatorioPorData = function(dataInicial , dataFinal){
				    	 prestadoraServicoService.relatorioPorData(dataInicial, dataFinal).
							then(function(f){
								self.listaPacoteServicos = t;
								 $scope.ativaTabela = true;
									}, function(errResponse){
							});
				     };
				     
				     
				     $scope.labels = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho"];
				     $scope.series = ['Series A', 'Series B'];
				     $scope.data = [
				       [65, 59, 80, 81, 56, 55, 40],
				       [28, 48, 40, 19, 86, 27, 90]
				     ];
				     $scope.onClick = function (points, evt) {
				       console.log(points, evt);
				     };
				     
				     // Simulate async data update 
				     $timeout(function () {
				       $scope.data = [
				         [28, 48, 40, 19, 86, 27, 90],
				         [65, 59, 80, 81, 56, 55, 40]
				       ];
				     }, 3000);
	
});