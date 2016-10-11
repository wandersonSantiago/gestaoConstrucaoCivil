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
						console.log(self.listaProdutos);
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
			
			
			self.funcaoListaProduto = function(quantidadeEstoque, produto, areaProduto, quantidadeSaida, andar, torre , apartamento, NumeroCasa){
				self.listaProduto.push({
					quantidadeEstoque : quantidadeEstoque,
					produto : produto,
					areaProduto : areaProduto,
					quantidadeSaida : quantidadeSaida,
					andar : andar,
					torre : torre,
					apartamento : apartamento,
					NumeroCasa : NumeroCasa
					
				});
			}
		
	self.verificaProdutoRepetido = function(quantidadeEstoque, produto, areaProduto, quantidadeSaida, andar, torre , apartamento, NumeroCasa){
			for(i = 0; i < self.listaProduto.length; i++ ){
				var produto2 = self.listaProduto[i].produto;
				var produto1 = produto2.id			
				if(produto1 != produto.id){
					self.existe = true;
					}else{
						sweetAlert({ timer : 3000,  text :"este produto já esta incluso",  type : "info", width: 300, higth: 300, padding: 20});
						self.existe = false;
						var tamanho = self.listaProduto[i];
						i = tamanho[i + 1];
					}			
			}
			if(self.existe == true){
				self.funcaoListaProduto(quantidadeEstoque, produto, areaProduto, quantidadeSaida, andar, torre , apartamento, NumeroCasa);
				}
			}
		
		self.adicionarProduto = function(quantidadeEstoque, produto, areaProduto, quantidadeSaida, andar, torre , apartamento, NumeroCasa){
			
			if(quantidadeEstoque < quantidadeSaida){
				sweetAlert({ timer : 6000,  text :"Quantidade Superior ao estoque",  type : "info", width: 300, higth: 300, padding: 20});
					
				}
			else{if(quantidadeSaida < 1 || quantidadeSaida == null){
					sweetAlert({ timer : 6000,  text :"Quantidade tem que ser maior que zero",  type : "info", width: 300, higth: 300, padding: 20});
					
				}
			else{
					$scope.ativaTabela = true;
					if(self.listaProduto.length == 0){				
						self.funcaoListaProduto(quantidadeEstoque, produto, areaProduto, quantidadeSaida, andar, torre , apartamento, NumeroCasa);				
					}
					else{
						self.verificaProdutoRepetido(quantidadeEstoque, produto, areaProduto, quantidadeSaida, andar, torre , apartamento, NumeroCasa);				
					}
				}
			}
		}
			
		self.salva = function(){
			
			self.baixaEstoque = self.listaProduto;
			console.log(self.baixaEstoque);
			estoqueService.salva(self.baixaEstoque)
			.then(function(response){
				self.quantidadeEstoque = null;
				self.produto = null;
				self.areaProduto = null;
				self.quantidadeSaida = null;
				self.andar = null;
				self.torre = null;
				self.apartamento = null;
				self.NumeroCasa = null;
				self.listaProduto = self.listaProduto=[];
				$scope.quantidade = "";
				$scope.produto.quantidade.quantidade = "";
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