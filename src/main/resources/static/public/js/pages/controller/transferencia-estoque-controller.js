app.controller('transferenciaEstoqueController', function($scope,transferenciaEstoqueService, saidaEstoqueService, produtoService, $routeParams, $timeout, $q, $log){
	
	var self = this;
	
		self.listaProduto =[];
		self.baixaEstoque = [];
		self.listaProdutos = [];
		$scope.produto =[];
		self.transferencia = [];
		
		self.lista = function(){
			transferenciaEstoqueService.lista().
				then(function(t){
					self.produtos = t;
					}, function(errResponse){
				});
			};
			self.buscaPorCodigoBarras = function(codigoBarras){
				saidaEstoqueService.buscaPorCodigoBarras(codigoBarras).
				then(function(p){					
					self.listaProdutosComEstoques = p;
								$scope.produto.produto = self.listaProdutosComEstoques.produto;
								$scope.produto.quantidade = self.listaProdutosComEstoques.quantidade;
													
								console.log(self.listaProdutosComEstoques);
				});
			};
			self.listaProdutosComEstoque = function(){
				transferenciaEstoqueService.listaProdutosComEstoque().
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
			
			
			self.funcaoListaProduto = function(quantidadeEstoque, produto,  quantidadeSaida, empreendimento){
				self.listaProduto.push({
					quantidadeEstoque : quantidadeEstoque,
					produto : produto,
					quantidadeSaida : quantidadeSaida,
					empreendimento : empreendimento,
					
					
				});
				self.limpaCampos();
			}
		
	self.verificaProdutoRepetido = function(quantidadeEstoque, produto,  quantidadeSaida, empreendimento){
			for(i = 0; i < self.listaProduto.length; i++ ){
				var produto2 = self.listaProduto[i].produto;
				var produto1 = produto2.id			
				if(produto1 != produto.id){
					self.existe = true;
					}else{
						sweetAlert({ timer : 3000,  text :"ja consta este produto na tabela",  type : "info", width: 300, higth: 300, padding: 20});
						self.existe = false;
						var tamanho = self.listaProduto[i];
						i = tamanho[i + 1];
					}			
			}
			if(self.existe == true){
				self.funcaoListaProduto(quantidadeEstoque, produto,  quantidadeSaida, empreendimento);
				}
			}
		
		self.adicionarProduto = function(quantidadeEstoque, produto,  quantidadeSaida, empreendimento){
		
			if(quantidadeEstoque < quantidadeSaida){
				sweetAlert({ timer : 6000,  text :"Quantidade Superior ao estoque",  type : "info", width: 300, higth: 300, padding: 20});
					
				}
			else{if(quantidadeSaida < 1 || quantidadeSaida == null){
					sweetAlert({ timer : 6000,  text :"Quantidade tem que ser maior que zero",  type : "info", width: 300, higth: 300, padding: 20});
					
				}else{
					$scope.ativaTabela = true;
					if(self.listaProduto.length == 0){				
						self.funcaoListaProduto(quantidadeEstoque, produto,  quantidadeSaida, empreendimento);				
					}
					else{
						self.verificaProdutoRepetido(quantidadeEstoque, produto, quantidadeSaida, empreendimento);				
					}
				}
			}
		}
		self.salva = function(){
			self.transferencia.itemTransferencia = self.listaProduto;
			transferenciaEstoqueService.salva(self.transferencia)
			.then(function(response){
				self.limpaCampos();
				self.transferencia = null;
				self.listaProduto = self.listaProduto=[];
				}, function(errResponse){
					
			});
			
		};
		
		self.limpaCampos = function(){
			self.quantidadeEstoque = null;
			self.produto = null;				
			self.quantidadeSaida = null;
			$scope.produto = null;
			$scope.quantidade = "";
		}
		
		

});