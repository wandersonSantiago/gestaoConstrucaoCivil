app.controller('estoqueController', function($scope,estoqueService, produtoService, $routeParams, $timeout, $q, $log){
	
	var self = this;
	
		self.listaProduto =[];
		self.baixaEstoque = [];
		self.listaProdutos = [];
		var idProdutoEstoque = $routeParams.idProdutoEstoque;
		self.lista = function(){
			 produtoService.lista().
				then(function(t){
					self.produtos = t;
					}, function(errResponse){
				});
			};
			
			self.buscaPorId = function(id){
				if(!id)return;
				estoqueService.buscaPorId(id).
				then(function(p){
					self.produtoEstoque = p;
				}, function(errResponse){
				});
			};
			
			self.buscaPorCodigoBarras = function(codigoBarras){
				estoqueService.buscaPorCodigoBarras(codigoBarras).
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
				estoqueService.listaProdutosComEstoque().
					then(function(t){
						self.listaProdutosComEstoques = t;
						
						for(i = 0; i < self.listaProdutosComEstoques.length; i++ ){
						
								self.produto = self.listaProdutosComEstoques[i].produto;
								self.quantidade = self.listaProdutosComEstoques[i].quantidade;
							self.listaProdutos.push({
										produto : self.produto,
										quantidade : self.quantidade									
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
				if(listaProduto.length == 0){
					$scope.ativaTabela = false;
				}
			});
		}
			
			
			self.funcaoListaProduto = function(objeto){
				
				self.listaProduto.push({
					quantidadeEstoque : objeto.produto.quantidade,
					produto : objeto.produto.produto,
					areaProduto : objeto.areaProduto,
					quantidadeSaida : objeto.quantidadeSaida,
					andar : objeto.andar,
					torre : objeto.torre,
					apartamento : objeto.apartamento,
					casa : objeto.casa,
					descricao : objeto.edificacaoComunitaria
					
				});
				
			}
		
	self.verificaProdutoRepetido = function(objeto){
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
			if(self.existe == true){
				self.funcaoListaProduto(objeto);
				}
			}
		
		self.adicionarProduto = function(objeto){
			
			
			if(objeto.produto.quantidade < objeto.quantidadeSaida){
				sweetAlert({ timer : 6000,  text :"Quantidade Superior ao estoque",  type : "info", width: 300, higth: 300, padding: 20});
					
				}
			else{if(objeto.quantidadeSaida < 1 || objeto.quantidadeSaida == null){
					sweetAlert({ timer : 6000,  text :"Quantidade tem que ser maior que zero",  type : "info", width: 300, higth: 300, padding: 20});
					
				}
			else{
					$scope.ativaTabela = true;
					if(self.listaProduto.length == 0){				
						self.funcaoListaProduto(objeto);				
					}
					else{
					
						self.verificaProdutoRepetido(objeto);				
					}
				}
			}
		}
			
		self.salva = function(){			
			
			if($scope.tipo.tipo == "edificio"){				
				self.baixaEstoque = self.listaProduto;
				estoqueService.salvaEdificio(self.baixaEstoque)
				.then(function(response){
					self.limpaCampo();				
					}, function(errResponse){
						
				});
			}else{
				if($scope.tipo.tipo == "casa"){
					self.baixaEstoqueCasa = self.listaProduto;
					estoqueService.salvaCasa(self.baixaEstoqueCasa)
					.then(function(response){
						self.limpaCampo();
						}, function(errResponse){
							
					});
					
				}else{ if($scope.tipo.tipo == "comunitaria"){
				self.baixaEstoqueOutros = self.listaProduto;
				estoqueService.salvaOutros(self.baixaEstoqueOutros)
				.then(function(response){
					self.limpaCampo();
				}, function(errResponse){
			
				});
				}
			}
		}
			
	};
		self.limpaCampo = function(){
			self.objeto = null;
			self.listaProduto = self.listaProduto=[];			
			$scope.ativadoExcluirLote = false;
			
		}
		
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
		if(idProdutoEstoque){
			self.buscaPorId(idProdutoEstoque);
		}
		
		$scope.busca = {
				busca : ''	
			};		
			self.tipoBusca = function(){
			if($scope.busca.busca == "descricao"){
				$scope.fornecedor = false;
				$scope.fabricante = false;
				$scope.tipo = false;
				$scope.categoria = false;
				$scope.descricao = true;
		}
			if($scope.busca.busca == "categoria"){
				$scope.fornecedor = false;
				$scope.fabricante = false;
				$scope.tipo = false;
				$scope.descricao = false;
				$scope.categoria = true;
			}
			if($scope.busca.busca == "tipo"){
				$scope.fornecedor = false;
				$scope.fabricante = false;
				$scope.categoria = false;
				$scope.descricao = false;
				$scope.tipo = true;
				
			}
			if($scope.busca.busca == "fabricante"){
				$scope.fornecedor = false;
				$scope.tipo = false;
				$scope.categoria = false;
				$scope.descricao = false;
				$scope.fabricante = true;
				
			}
			if($scope.busca.busca == "fornecedor"){
				$scope.fabricante = false;
				$scope.tipo = false;
				$scope.categoria = false;
				$scope.descricao = false;
				$scope.fornecedor = true;
				
			}
			}

});