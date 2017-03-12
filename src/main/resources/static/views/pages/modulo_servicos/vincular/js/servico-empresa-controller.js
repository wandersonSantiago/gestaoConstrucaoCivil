app.controller('servicoEmpresaController', function($scope, $rootScope, servicoEmpresaService, $routeParams , FileUploader, $timeout){
	
	var self = this;
	self.getPage=0;
	self.totalPages = 0;
	self.totalElements = 0;
	$scope.maxResults = 15;		
	var  idServicoComunitario = $routeParams.idServicoComunitario;
	var  idServicoEdificio = $routeParams.idServicoEdificio;
	var  idServicoCasa = $routeParams.idServicoCasa;
	var  idServico = $routeParams.idServico;
	
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
	 
	
		
		self.buscaTodosComPaginacaoEdificio = function(pages, maxResults){
			self.totalPages = [];
			self.getPage=pages;			
			servicoEmpresaService.buscaTodosComPaginacaoEdificio(pages, maxResults).
			then(function(e){			
				$scope.listaEdificio  = e.content;
				$scope.totalPages = e.totalPages;
				self.totalElements = e.totalElements;
				for(i = 0; i < $scope.totalPages ; i++){
					self.totalPages.push(i);
				}
			}, function(errResponse){
			});
		};
		
		self.listaEdificio = function(){		
			servicoEmpresaService.listaEdificio().
			then(function(e){			
				$scope.listaEdificio  = e;
				$scope.ativaTabela = true;
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
		 
		
			self.buscaTodosComPaginacaoCasa = function(pages, maxResults){
				self.totalPages = [];
				self.getPage=pages;			
				servicoEmpresaService.buscaTodosComPaginacaoCasa(pages, maxResults).
				then(function(e){			
					$scope.listaCasa  = e.content;
					$scope.totalPages = e.totalPages;
					self.totalElements = e.totalElements;
					for(i = 0; i < $scope.totalPages ; i++){
						self.totalPages.push(i);
					}
				}, function(errResponse){
				});
			};
			
			self.listaCasa = function(){						
				servicoEmpresaService.listaCasa().
				then(function(e){			
					$scope.listaCasa  = e;	
					$scope.ativaTabela = true;
				}, function(errResponse){
				});
			};
			
			 self.salvaEdificacoesComunitaria = function(descricao , pacoteServico , prestadoraServico){
				 servicoOutros = {};
				 servicoOutros.descricao = descricao.descricao;
				 servicoOutros.pacoteServico = pacoteServico;
				 servicoOutros.prestadoraServico = prestadoraServico;
				 servicoEmpresaService.salvaEdificacoesComunitaria(servicoOutros).
					then(function(response){
						self.servicoEdificio = null;
						}, function(errResponse){
					});
				}
			 
			 self.alteraEdificacoesComunitaria =  function(descricao , pacoteServico , prestadoraServico){
				 servicoOutros = {};
				 servicoOutros.descricao = descricao.descricao;
				 servicoOutros.pacoteServico = pacoteServico;
				 servicoOutros.prestadoraServico = prestadoraServico;
				 servicoEmpresaService.alteraEdificacoesComunitaria(servicoOutros).
					then(function(response){
						self.servicoEmpresa = null;
						}, function(errResponse){
					});
				}
			 			
				self.buscaTodosComPaginacaoEdificacoesComunitaria = function(pages, maxResults){
					self.totalPages = [];
					self.getPage=pages;			
					servicoEmpresaService.buscaTodosComPaginacaoEdificacoesComunitaria(pages, maxResults).
					then(function(e){			
						$scope.listaEdificacoesComunitaria  = e.content;
						$scope.totalPages = e.totalPages;
						self.totalElements = e.totalElements;
						for(i = 0; i < $scope.totalPages ; i++){
							self.totalPages.push(i);
						}
					}, function(errResponse){
					});
				};
				self.listaEdificacoesComunitaria = function(){	
					servicoEmpresaService.listaEdificacoesComunitaria().
					then(function(e){			
						$scope.listaEdificacoesComunitaria  = e;		
						$scope.ativaTabela = true;
					}, function(errResponse){
					});
				};
		
				self.buscaServicoEdificioPorId = function(id){
					if(!id)return;
					servicoEmpresaService.buscaServicoEdificioPorId(id).
					then(function(p){
						self.servicoEdifico = p;
						self.pacoteServico = p.pacoteServico;
						self.prestadoraServico = p.prestadoraServico;
						}, function(errResponse){
					});
				};
				
			
				if(idServicoEdificio){
					
					self.buscaServicoEdificioPorId(idServicoEdificio);
				}
				
				self.buscaServicoCasaPorId = function(id){
					if(!id)return;
					servicoEmpresaService.buscaServicoCasaPorId(id).
					then(function(p){
						self.servicoCasa = p;
						self.pacoteServico = p.pacoteServico;
						self.prestadoraServico = p.prestadoraServico;
						}, function(errResponse){
					});
				};
				
			
				if(idServicoCasa){
					
					self.buscaServicoCasaPorId(idServicoECasa);
				}
				
				self.buscaServicoComunitarioPorId = function(id){
					if(!id)return;
					servicoEmpresaService.buscaServicoComunitarioPorId(id).
					then(function(p){
						self.descricao = p.descricao;
						self.pacoteServico = p.pacoteServico;
						self.prestadoraServico = p.prestadoraServico;
						}, function(errResponse){
					});
				};				
			
				if(idServicoComunitario){
					
					self.buscaServicoComunitarioPorId(idServicoComunitario);
				}
				
		$scope.tipo = {
				tipo : ''	
			};		
			self.tipoConf = function(){
			if($scope.tipo.tipo == "edificio"){
				$scope.casa = false;
				$scope.comunitaria = false;
				$scope.edificio = true;
				self.torre = false;
				self.numeroCasa = false;
				self.descricao = false;
				$scope.ativaTabela = false;
				 $scope.ativaGrafico = false;
		}
			if($scope.tipo.tipo == "comunitaria"){
				$scope.casa = false;
				$scope.edificio = false;
				$scope.comunitaria = true;
				self.torre = false;
				self.numeroCasa = false;
				self.descricao = false;
				$scope.ativaTabela = false;
				 $scope.ativaGrafico = false;
			}
			if($scope.tipo.tipo == "casa"){
				$scope.comunitaria = false;
				$scope.edificio = false;
				$scope.casa = true;
				self.torre = false;
				self.numeroCasa = false;
				self.descricao = false;
				$scope.ativaTabela = false;
				 $scope.ativaGrafico = false;
				
			}
			}
			//PAGAMENTOS
			self.buscarServicosDaPrestadora = function(prestadora){
				servicoEmpresaService.buscarServicosDaPrestadora(prestadora).
				then(function(e){			
					$rootScope.servicos  = e;
					$rootScope.ativaTabela = true;
				}, function(errResponse){
				});
			};
			
			self.buscarServicosDaPrestadoraPorId = function(prestadora){
				servicoEmpresaService.buscarServicosDaPrestadoraPorId(prestadora).
				then(function(e){			
					self.servicoEmpresa  = e;
				}, function(errResponse){
				});
			};
			if(idServico){
				self.buscarServicosDaPrestadoraPorId(idServico);
			}
			
			self.salvarPagamento = function(prestadora){
				servicoEmpresaService.salvarPagamento(prestadora).
				then(function(e){			
					self.servicoEmpresa  = null;
					$location.path('#/servicos/vistoria');
				}, function(errResponse){
				});
			};

	//VISTORIA
			
			self.consultarServicoEdificio = function(torre, andar, apartamento){
				servicoEmpresaService.consultarServicoEdificio(torre, andar, apartamento).
				then(function(e){			
					$scope.listaEdificio  = e;
					console.log(e);
					$scope.ativaTabela = true;
				}, function(errResponse){
				});
			};
			
			self.consultarServicoCasa = function(casa, andar){
				servicoEmpresaService.consultarServicoCasa(casa, andar).
				then(function(e){			
					$scope.listaCasa  = e;		
					$scope.ativaTabela = true;
				}, function(errResponse){
				});
			};
			
			self.consultarServicoEdificacoesComunitaria = function(outros){
				servicoEmpresaService.consultarServicoEdificacoesComunitaria(outros).
				then(function(e){			
					$scope.listaEdificacoesComunitaria  = e;
					$scope.ativaTabela = true;
				}, function(errResponse){
				});
			};
			
			 self.alteraVistoriaEdificio = function(servicoEdifico , pacoteServico , prestadoraServico, ocorrencia){		
				 servicoEdifico.pacoteServico = pacoteServico;
				 servicoEdifico.prestadoraServico = prestadoraServico;
				
				 if(ocorrencia){
					 servicoEdifico.ocorrencias = [{ocorrencia : ocorrencia}];
				 }
				 servicoEmpresaService.alteraVistoriaEdificio(servicoEdifico).
					then(function(response){
						self.ocorrencia = null;
						self.servicoEmpresa = null;
						}, function(errResponse){
					});
				}
			
			 
			 var uploader = $scope.uploader =  new FileUploader({
	               url : '/rest/file/upload',
	               queueLimit : 5
	           });
	            
	          /* uploader.onCompleteAll = function(){
	            alert('Upload Completo!');
	            //uploader.clearQueue();
	           }*/
			 
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
			    	 servicoEmpresaService.relatorioPorData(dataInicial, dataFinal).
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