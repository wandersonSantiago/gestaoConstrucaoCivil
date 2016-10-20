app.controller('saidaEstoqueController', function($scope, saidaEstoqueService, $routeParams, $timeout, $q, $log){
	
	var self = this;
	
		self.listaProduto =[];
		self.baixaEstoque = [];
		self.listaProdutos = [];
		$scope.listaProdutos = [];
				
			self.buscaPorCodigoBarras = function(codigoBarras){
				saidaEstoqueService.buscaPorCodigoBarras(codigoBarras).
				then(function(p){
					self.listaProdutosComEstoques = p;
					for(i = 0; i < self.listaProdutosComEstoques.length; i++ ){
						
						self.produto = self.listaProdutosComEstoques[i].produto;
						self.quantidade = self.listaProdutosComEstoques[i].quantidade;
					self.listaProdutos.push({
								produto : self.produto,
								quantidade : self.quantidade
							
						});
					}
				});
			};
			self.listaProdutosComEstoque = function(){
				saidaEstoqueService.listaProdutosComEstoque().
					then(function(t){
						self.listaProdutosComEstoques = t;
						
						for(i = 0; i < self.listaProdutosComEstoques.length; i++ ){
						
								self.produto = self.listaProdutosComEstoques[i].produto;
								self.quantidade = self.listaProdutosComEstoques[i].quantidade;
								$scope.listaProdutos.push({
										produto : self.produto,
										quantidade : self.quantidade									
								});	
							
						}				self.listaProdutos = $scope.listaProdutos;
					
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
					quantidadeSaida : objeto.quantidadeSaida,
					andar : objeto.andar,
					torre : objeto.torre,
					apartamento : objeto.apartamento					
				});
				
			}
			self.funcaoListaProdutoCasa = function(objeto){
							
					self.listaProduto.push({
						quantidadeEstoque : objeto.produto.quantidade,
						produto : objeto.produto.produto,
						areaProduto : objeto.areaProduto,
						quantidadeSaida : objeto.quantidadeSaida,
						andar : objeto.andar,						
						casa : objeto.casa												
					});
					
				}
			self.funcaoListaProdutoEdificacoesComunitaria = function(objeto){
				console.log(objeto);
					self.listaProduto.push({
						quantidadeEstoque : objeto.produto.quantidade,
						produto : objeto.produto.produto,
						areaProduto : objeto.areaProduto,
						quantidadeSaida : objeto.quantidadeSaida,					
						descricao : objeto.edificacaoComunitaria.descricao						
					});		
			}
		
			verificaProdutoRepetido = function(objeto){
			for(i = 0; i < self.listaProduto.length; i++ ){
				var produto2 = self.listaProduto[i].produto;
				var produto1 = produto2.id					
				if(produto1 != objeto.produto.produto.id){
					self.existe = true;
					}else{
						sweetAlert({ timer : 3000,  text :"este produto já esta incluso",  type : "info", width: 300, higth: 300, padding: 20});
						self.existe = false;
						var tamanho = self.listaProduto[i];
						i = tamanho[i + 1];
					}			
				}			
			}
		
	
		self.adicionarProdutoEdificio = function(objeto){
			
			veirificaQuantidade(objeto);			
			$scope.ativaTabela = true;
			if(self.listaProduto.length == 0){				
				self.funcaoListaProdutoEdificio(objeto);				
			}
			else{			
				verificaProdutoRepetido(objeto);	
				if(self.existe == true){
					self.funcaoListaProdutoEdificio(objeto);
					}
			}			
		}
		self.adicionarProdutoCasa = function(objeto){
					
			veirificaQuantidade(objeto);			
			$scope.ativaTabela = true;
			if(self.listaProduto.length == 0){				
				self.funcaoListaProdutoCasa(objeto);				
			}
			else{			
				verificaProdutoRepetido(objeto);	
				if(self.existe == true){
					self.funcaoListaProdutoCasa(objeto);
					}
			}			
		}
		self.adicionarProdutoEdificacaoComunitaria = function(objeto){
			
			veirificaQuantidade(objeto);			
			$scope.ativaTabela = true;
			if(self.listaProduto.length == 0){				
				self.funcaoListaProdutoEdificacoesComunitaria(objeto);				
			}
			else{			
				verificaProdutoRepetido(objeto);	
				if(self.existe == true){
					self.funcaoListaProdutoEdificacoesComunitaria(objeto);
					}
			}			
		}

		veirificaQuantidade = function(objeto){
			
			if(objeto.produto.quantidade < objeto.quantidadeSaida){
				sweetAlert({ timer : 6000,  text :"Quantidade Superior ao estoque",  type : "info", width: 300, higth: 300, padding: 20});
				}
			else if(objeto.quantidadeSaida < 1 || objeto.quantidadeSaida == null){
					sweetAlert({ timer : 6000,  text :"Quantidade tem que ser maior que zero",  type : "info", width: 300, higth: 300, padding: 20});
			}
		}
			
		
		
		self.salvaEdificio = function(){
			self.baixaEstoque = self.listaProduto;
			saidaEstoqueService.salvaEdificio(self.baixaEstoque)
			.then(function(response){
				self.limpaCampo();				
				}, function(errResponse){					
			});			
		}
		
		self.salvaCasa = function(){
			self.baixaEstoqueCasa = self.listaProduto;
			saidaEstoqueService.salvaCasa(self.baixaEstoqueCasa)
			.then(function(response){
				self.limpaCampo();
				}, function(errResponse){					
			});
		}
		
		self.salvaEdificacoesComunitaria = function(){
			self.baixaEstoqueOutros = self.listaProduto;
			saidaEstoqueService.salvaOutros(self.baixaEstoqueOutros)
			.then( $timeout(function(response){
				self.listaProdutosComEstoque();
				self.limpaCampo();
				$scope.listaProdutos = null;
				$scope.listaProdutos = self.listaProdutos;
			},1000), function(errResponse){		
			});
		}
		
		
		self.limpaCampo = function(){
			self.objeto = null;
			self.listaProduto = self.listaProduto=[];			
			$scope.ativadoExcluirLote = false;
			$scope.ativaTabela = false;
			
			
			
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
		

});