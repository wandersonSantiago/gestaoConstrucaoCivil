app.controller('requisicaoEstoqueController', function($scope, requisicaoEstoqueService, $routeParams, $timeout, $location, $q, $log){
	
	 var self = this;
	 var idRequisicaoCasa = $routeParams.idRequisicaoCasa;
	 var idRequisicaoEdificio = $routeParams.idRequisicaoEdificio;
	 var idRequisicaoOutros = $routeParams.idRequisicaoOutros;
	 
		self.listaProduto =[];
		self.baixaEstoque = [];
		self.listaProdutos = [];
		$scope.listaProdutos = [];
		self.requisicao = [];
		self.produto = [];
		self.produtos = [];
		self.objeto = [];
		$scope.produto = [{produto : "", quantidade :""}];
		var quantidadeMaior = true;
		var produtoMesmoImovel = true;		
			self.buscaPorCodigoBarras = function(codigoBarras){
				requisicaoEstoqueService.buscaPorCodigoBarras(codigoBarras).
				then(function(p){					
					self.listaProdutosComEstoques = p;
								$scope.quantidade = self.listaProdutosComEstoques.quantidade;
								$scope.produto = self.listaProdutosComEstoques.produto;
								$scope.valorUnitario = self.listaProdutosComEstoques.custoMedio;
				});
			};
			self.listaProdutosComEstoque = function(){
				requisicaoEstoqueService.listaProdutosComEstoque().
					then(function(t){
						self.listaProdutosComEstoques = t;
						self.listaProdutos = [{produto:"",quantidade:"", valorUnitario:""}];											
						for(i = 0; i < self.listaProdutosComEstoques.length; i++ ){
						
								self.produto = self.listaProdutosComEstoques[i].produto;
								self.quantidade = self.listaProdutosComEstoques[i].quantidade;
								self.valorUnitario = self.listaProdutosComEstoques[i].custoMedio;
								self.listaProdutos.push({
										produto : self.produto,
										quantidade : self.quantidade,
										valorUnitario : self.valorUnitario
								});	
						}	
							}, function(errResponse){
					});
				};
			
		self.ativarExcluirLote = function(listaProduto){
			self.listaProduto.filter(function(produto){
			if(produto.selecionado){
				$scope.ativadoExcluirLote = true;
			}
			});
		}
			
			self.apagarProduto = function(listaProduto){
				
				self.listaProduto = listaProduto.filter(function(produto){
				if(!produto.selecionado) return produto;
				$scope.ativadoExcluirLote = null;				
				});				
				if(self.listaProduto.length == 0){					
					$scope.ativaTabela = false;
				}
		}
			
			
			self.funcaoListaProdutoEdificio = function(objeto){
				
				self.listaProduto.push({
					quantidadeEstoque : objeto.produto.quantidade,
					produto : objeto.produto.produto,
					areaProduto : objeto.areaProduto,
					quantidade : objeto.quantidadeSaida,
					andar : objeto.andar,
					torre : objeto.torre,
					apartamento : objeto.apartamento,
					valorUnitario : objeto.produto.valorUnitario
				});
				
			}
			self.funcaoListaProdutoCasa = function(objeto){
							
					self.listaProduto.push({
						quantidadeEstoque : objeto.produto.quantidade,
						produto : objeto.produto.produto,
						areaProduto : objeto.areaProduto,
						quantidade : objeto.quantidadeSaida,
						andar : objeto.andar,						
						casa : objeto.casa,
						valorUnitario : objeto.produto.valorUnitario												
					});
					
				}
			self.funcaoListaProdutoEdificacoesComunitaria = function(objeto){
				
					self.listaProduto.push({
						quantidadeEstoque : objeto.produto.quantidade,
						produto : objeto.produto.produto,
						areaProduto : objeto.areaProduto,
						quantidade : objeto.quantidadeSaida,					
						descricao : objeto.edificacaoComunitaria.descricao,
						valorUnitario : objeto.produto.valorUnitario						
					});	
			}
		
			verificaProdutoRepetido = function(objeto){
				for(i = 0; i < self.listaProduto.length; i++ ){
					var produto2 = self.listaProduto[i].produto.id;					
					if(produto2 != objeto.produto.produto.id){							
						}
					else{						
						objeto.produto.quantidade = objeto.produto.quantidade - objeto.quantidadeSaida;	
						i = self.listaProduto.length + 1;						
						}
					}			
			}
		
	
		self.adicionarProdutoEdificio = function(objeto){
			if(!$scope.habilitaBuscaPorDescricao){objeto.produto = {produto : $scope.produto, quantidade : $scope.quantidade , valorUnitario : $scope.valorUnitario};}
			verificaQuantidade(objeto);	
			if(quantidadeMaior){
				$scope.ativaTabela = true;
					verificaProdutoRepetido(objeto);
					self.funcaoListaProdutoEdificio(objeto);
			}
			self.limpaCampoProdutos();
		}
		self.adicionarProdutoCasa = function(objeto){
			if(!$scope.habilitaBuscaPorDescricao){objeto.produto = {produto : $scope.produto, quantidade : $scope.quantidade, valorUnitario : $scope.valorUnitario};}		
			verificaQuantidade(objeto);	
			if(quantidadeMaior){
				$scope.ativaTabela = true;
					verificaProdutoRepetido(objeto);
					self.funcaoListaProdutoCasa(objeto);					
			}
			self.limpaCampoProdutos();
		}
		self.adicionarProdutoEdificacaoComunitaria = function(objeto){		    
			if(!$scope.habilitaBuscaPorDescricao){objeto.produto = {produto : $scope.produto, quantidade : $scope.quantidade , valorUnitario : $scope.valorUnitario};}			
			verificaQuantidade(objeto);				
			if(quantidadeMaior){
				$scope.ativaTabela = true;
					verificaProdutoRepetido(objeto);					
					self.funcaoListaProdutoEdificacoesComunitaria(objeto);				
			}
			self.limpaCampoProdutos();
		}
		

		verificaQuantidade = function(objeto){			
			if(objeto.produto.quantidade < objeto.quantidadeSaida){
				sweetAlert({ timer : 6000,  text :"Quantidade Superior ao estoque",  type : "info", width: 300, higth: 300, padding: 20});
				quantidadeMaior = false;
			}
			else if(objeto.quantidadeSaida < 1 || objeto.quantidadeSaida == null){
					sweetAlert({ timer : 6000,  text :"Quantidade tem que ser maior que zero",  type : "info", width: 300, higth: 300, padding: 20});
				quantidadeMaior = false;	
			}
		}
			
		
		
		self.salvaEdificio = function(){	
			self.requisicaoEdificio = {};			
			self.requisicaoEdificio.itens = self.listaProduto;
			console.log(self.requisicaoEdificio);
			requisicaoEstoqueService.salvaEdificio(self.requisicaoEdificio)
			.then($timeout(function(response){
				//self.listaProdutosComEstoque();
				//self.limpaCampo();
			},500), function(errResponse){					
			});			
		}
		
		self.salvaCasa = function(){
			self.requisicaoCasa = {};
			self.requisicaoCasa.itens = self.listaProduto; 
			console.log(self.listaProduto);
			requisicaoEstoqueService.salvaCasa(self.requisicaoCasa)
			.then($timeout(function(response){
				//self.listaProdutosComEstoque();
				//self.limpaCampo();
			},500), function(errResponse){					
			});
		}
		
		self.salvaEdificacoesComunitaria = function(){
			self.requisicaoOutros = {};
			self.requisicaoOutros.itens = self.listaProduto;
			requisicaoEstoqueService.salvaOutros(self.requisicaoOutros)
			.then( $timeout(function(response){
			//	self.listaProdutosComEstoque();
			//	self.limpaCampo();
			},500), function(errResponse){		
			});
		}
		
		self.listaRequisicaoEdificio = function(){
			requisicaoEstoqueService.listaRequisicaoEdificio().
				then(function(t){
					$scope.listaRequisicaoEdificio = t;	
					}, function(errResponse){
				});
			};
		self.listaRequisicaoCasa = function(){
			requisicaoEstoqueService.listaRequisicaoCasa().
				then(function(t){
					$scope.listaRequisicaoCasa = t;			
					}, function(errResponse){
				});
			};
		self.listaRequisicaoOutros = function(){
				requisicaoEstoqueService.listaRequisicaoOutros().
					then(function(t){
						$scope.listaRequisicaoOutros = t;			
						}, function(errResponse){
					});
				};
		
		self.limpaCampo = function(){
			self.objeto = null;
			$scope.quantidade = null;
			$scope.produto = null;
			$scope.codigo;
			self.listaProduto = self.listaProduto=[];			
			$scope.ativadoExcluirLote = false;
			$scope.ativaTabela = false;		
			$scope.valorUnitario = null;
			
		}
		
		self.limpaCampoProdutos = function(){
			
			self.objeto = null;
			$scope.quantidade = null;
			$scope.produto = null;
			$scope.codigo;			
			$scope.valorUnitario = null;
		}
		
		self.buscaPorIdCasa = function(id){
			if(!id)return;
			requisicaoEstoqueService.buscaPorIdCasa(id).
			then(function(p){
				self.requisicao = p;
				self.itensCasa = p.itens;
				self.requisicao.informacaoRequisicao.dataSaida = new Date(p.informacaoRequisicao.dataSaida);
				self.verificaStatus(self.requisicao);
			}, function(errResponse){
			});
		};		
		if(idRequisicaoCasa){
			self.buscaPorIdCasa(idRequisicaoCasa);
		}
		self.buscaPorIdEdificio = function(id){
			if(!id)return;
			requisicaoEstoqueService.buscaPorIdEdificio(id).
			then(function(p){
				self.requisicao = p;
				self.itensCasa = p.itens;
				self.requisicao.informacaoRequisicao.dataSaida = new Date(p.informacaoRequisicao.dataSaida);
				self.verificaStatus(self.requisicao);
			}, function(errResponse){
			});
		};		
		if(idRequisicaoEdificio){
			self.buscaPorIdEdificio(idRequisicaoEdificio);
		}
		self.buscaPorIdOutros = function(id){
			if(!id)return;
			requisicaoEstoqueService.buscaPorIdOutros(id).
			then(function(p){
				self.requisicao = p;
				self.itensCasa = p.itens;
				self.requisicao.informacaoRequisicao.dataSaida = new Date(p.informacaoRequisicao.dataSaida);
				self.verificaStatus(self.requisicao);
			}, function(errResponse){
			});
		};		
		if(idRequisicaoOutros){
			self.buscaPorIdOutros(idRequisicaoOutros);
		}
		
		$scope.tipo = {
			tipo : ''	
		};		
		self.tipoConf = function(){
		if($scope.tipo.tipo == "edificio"){
			$scope.casa = false;
			$scope.comunitaria = false;
			$scope.edificio = true;
			self.limpaCampo();
			
	}
		if($scope.tipo.tipo == "comunitaria"){
			$scope.casa = false;
			$scope.edificio = false;
			$scope.comunitaria = true;
			self.limpaCampo();
			
		}
		if($scope.tipo.tipo == "casa"){
			$scope.comunitaria = false;
			$scope.edificio = false;
			$scope.casa = true;
			self.limpaCampo();
			
			
		}
	}
		self.aceitarRequisicaoCasa = function(numero){					 
			requisicaoEstoqueService.aceitarRequisicaoCasa(numero)
			.then(function(response){	
				$location.path("/estoque/requisicao/listagem");
				}, function(errResponse){
					
			});
			
		};
		
		self.rejeitaRequisicaoCasa = function(numero){					 
			requisicaoEstoqueService.rejeitaRequisicaoCasa(numero)
			.then(function(response){		
				$location.path("/estoque/requisicao/listagem");
				}, function(errResponse){
					
			});
			
		};
		self.aceitarRequisicaoEdificio = function(numero){					 
			requisicaoEstoqueService.aceitarRequisicaoEdificio(numero)
			.then(function(response){	
				$location.path("/estoque/requisicao/listagem");
				}, function(errResponse){
					
			});
			
		};
		
		self.rejeitaRequisicaoEdificio = function(numero){					 
			requisicaoEstoqueService.rejeitaRequisicaoEdificio(numero)
			.then(function(response){		
				$location.path("/estoque/requisicao/listagem");
				}, function(errResponse){
					
			});
			
		};
		self.aceitarRequisicaoOutros = function(numero){					 
			requisicaoEstoqueService.aceitarRequisicaoOutros(numero)
			.then(function(response){	
				$location.path("/estoque/requisicao/listagem");
				}, function(errResponse){
					
			});
			
		};
		
		self.rejeitaRequisicaoOutros = function(numero){					 
			requisicaoEstoqueService.rejeitaRequisicaoOutros(numero)
			.then(function(response){		
				$location.path("/estoque/requisicao/listagem");
				}, function(errResponse){
					
			});
			
		};
		self.verificaStatus = function(requisicao){
			if(requisicao.informacaoRequisicao.statusRequisicao == "PENDENTE"){
				$scope.status = true;
			}else{
				$scope.status = false;
			}
			
		}
		

});