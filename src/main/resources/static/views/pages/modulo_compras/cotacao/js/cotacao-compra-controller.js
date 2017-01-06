app.controller('cotacaoCompraController', function($scope, $rootScope, cotacaoCompraService, $location, $routeParams){
	
		var self = this;
		self.listaCotacao = [];
		self.existe = true;	
		$scope.visialuzarTable = true;
		$scope.cotacaoEmpresa = [{cotacao : "", forncedeor : "", itens : ""}];
		$scope.itens = [];
		var idCotacaoAberta = $routeParams.idCotacaoAberta;
		var idVencedores = $routeParams.idVencedores;
		var idConcorrentes = $routeParams.idConcorrentes;
		
	
		self.salvaCotacaoEmpresa = function(cotacao , fornecedor, listaCotacao){
			for(i = 0; i < listaCotacao.length ; i ++ ){ $scope.itens.push({	item : listaCotacao[i],	valorUnitario : listaCotacao[i].valorUnitario, observacao : listaCotacao[i].observacao});} 
			$scope.cotacaoEmpresa = {cotacao : cotacao, fornecedor : fornecedor, itens : $scope.itens };			
			cotacaoCompraService.salvaCotacaoEmpresa($scope.cotacaoEmpresa).
			then(function(response){
				self.limpaCampos();	
				$scope.valorTotalCotacao = null;
				}, function(errResponse){
			});
		}
		
	 self.salva = function(cotacao){
		 self.cotacao.itens = self.listaCotacao;
		 cotacaoCompraService.salva(self.cotacao).
				then(function(response){
					self.limpaCampos();					
					}, function(errResponse){
				});
	 }
	 self.altera = function(cotacao){
		 self.cotacao.itens = self.listaCotacao;
		 cotacaoCompraService.altera(self.cotacao).
				then(function(response){
					self.limpaCampos();	
					}, function(errResponse){
				});
	 }
	 
	 self.lista = function(){
		 cotacaoCompraService.lista().
			then(function(t){
				$scope.cotacao = t;
				self.cotacoes = t;
				for(i = 0 ; i < t.length ; i++){					
					if(t[i].statusCotacao === "FECHADO"){
						$rootScope.fechado = true;
						self.cotacoes[i].fechado = true;
					}
				}
				}, function(errResponse){
			});
		};
		
		self.listaConcorrentes = function(id){
			 cotacaoCompraService.listaConcorrentes(id).
				then(function(t){
					$scope.concorrentes = t;					
					for(i = 0 ; i < t.length ; i++){						
						$scope.concorrentes[i].cotacao.dataCriacao = new Date(t[i].cotacao.dataCriacao);
						$scope.concorrentes[i].cotacao.dataFechamento = new Date(t[i].cotacao.dataFechamento);
						$scope.concorrentes[i].cotacao.dataLimite = new Date(t[i].cotacao.dataLimite);							
					}
					}, function(errResponse){
				});
			};
			if(idConcorrentes){
				self.listaConcorrentes(idConcorrentes);
			}	
			
			self.buscaConcorrentePorId = function(id){
				var t = $scope.concorrentes;					
				for(i = 0 ; i < t.length ; i++){
					if(id == t[i].id){
						$location.path('/cotacao/concorrente');
						$rootScope.cotacao = t[i];
					}
									
				}
			}
			
			self.listaVencedores = function(id){
				 cotacaoCompraService.listaVencedores(id).
					then(function(t){
						$scope.vencedores = t;					
						for(i = 0 ; i < t.length ; i++){						
							$scope.vencedores[i].cotacao.dataCriacao = new Date(t[i].cotacao.dataCriacao);
							$scope.vencedores[i].cotacao.dataFechamento = new Date(t[i].cotacao.dataFechamento);
							$scope.vencedores[i].cotacao.dataLimite = new Date(t[i].cotacao.dataLimite);							
						}
						}, function(errResponse){
					});
				};
				if(idVencedores){
					self.listaVencedores(idVencedores);
				}
				
			self.buscaVencedorPorId = function(id){
				var t = $scope.vencedores;					
				for(i = 0 ; i < t.length ; i++){
					if(id == t[i].id){
						$location.path('/cotacao/vencedor');
						$rootScope.cotacao = t[i];
					}									
				}
			}
				
		self.fecharCotacao = function(id) {
			swal({ 
				  title: 'Encerrar!!!',
				  text: "Tem que certeza que deseja encerrar esta cotação ?",
				  type: 'info',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Encerrar!'
				}).then(function () {
					cotacaoCompraService.fecharCotacao(id).
			then(function(response){				
				
			});
		})
	}
	
			
		
		self.adicionarProdutos = function(descricao, quantidade){
			self.listaCotacao.push({
				descricao :  descricao,
				quantidade : quantidade
			});
			$scope.cotacao = null;
			$scope.visialuzarTable = true;
			$scope.cotacaoCtrl.descricao = null;
			$scope.cotacaoCtrl.quantidade = null;
		}
			
	
		self.SomaTotal = function(listaCotacao){
			var totalSoma = 0;
			for(i =0; i < listaCotacao.length ; i ++){
				var total = listaCotacao[i];
				totalSoma += parseFloat(total.valorTotal);	
				$scope.valorTotalCotacao = totalSoma;
					}
			}
		
		$scope.somaUnitario = function(quantidade, valorUnitario){
			return $scope.valorTotal = quantidade * valorUnitario;
		}
		
		self.ativarExcluirLote = function(listaCotacao){
			self.listaCotacao.filter(function(f){
			if(f.selecionado){
				$scope.ativadoExcluirLote = true; }
			});
		}
			

		//apagar outros empreendimentos, somente da lista de front
		self.apagarProdutos = function(listaCotacao){
				self.listaCotacao = self.listaCotacao.filter(function(f){
				if(!f.selecionado) return f;
				$scope.valorTotalCotacao -= f.valorTotal;
				$scope.ativadoExcluirLote = false;
			});
		}
		
		self.buscaPorCotacaoId = function(id){
			if(!id)return;
			cotacaoCompraService.buscaPorCotacaoId(id).
			then(function(p){
				self.cotacao = p;				
				self.listaCotacao = self.cotacao.itens;
				self.cotacao.dataLimite = new Date(self.cotacao.dataLimite);
				self.cotacao.dataCriacao = new Date(self.cotacao.dataCriacao);
			});
		};

	
		if(idCotacaoAberta){
			self.buscaPorCotacaoId(idCotacaoAberta);
		}
		
		self.limpaCampos = function(){
			
			$scope.cotacaoCtrl = null;
			self.listaCotacao = self.listaCotacao;
			$scope.visialuzarTable = false;
		}
});