app.controller('pacoteServicoController', function($scope, pacoteServicoService, $location,  $routeParams, $timeout){
	
	var self = this;
	self.getPage=0;
	self.totalPages = 0;
	self.totalElements = 0;
	$scope.maxResults = 15;	
	var idPacoteServico = $routeParams.idPacoteServico;
	
	
	
	 self.salva = function(pacoteServico){
		 pacoteServicoService.salva(self.pacoteServico).
			then(function(response){
				self.pacoteServico = null;
				}, function(errResponse){
			});
		 
	}
	 
	 self.altera = function(pacoteServico){
		 pacoteServicoService.altera(self.pacoteServico).
			then(function(response){
				self.pacoteServico = null;
				$location.path('/servicos/pacotes/lista');
				}, function(errResponse){
			});
		 
	}	 
	 
		self.buscaTodosComPaginacao = function(pages, maxResults){
			self.totalPages = [];
			self.getPage=pages;			
			pacoteServicoService.buscaTodosComPaginacao(pages, maxResults).
			then(function(e){			
				self.listaPacoteServicos = e.content;
				$scope.totalPages = e.totalPages;
				self.totalElements = e.totalElements;
				for(i = 0; i < $scope.totalPages ; i++){
					self.totalPages.push(i);
				}
			}, function(errResponse){
			});
		};
		
		self.lista = function(){					
			pacoteServicoService.lista().
			then(function(e){			
				self.listaPacoteServicos = e;		
				 $scope.ativaTabela = true;
			}, function(errResponse){
			});
		};
		self.buscaPorId = function(id){
			if(!id)return;
			pacoteServicoService.buscaPorId(id).
			then(function(p){
				self.pacoteServico = p;
				}, function(errResponse){
			});
		};
		
	
		if(idPacoteServico){
			
			self.buscaPorId(idPacoteServico);
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
		    	 pacoteServicoService.relatorioPorData(dataInicial, dataFinal).
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