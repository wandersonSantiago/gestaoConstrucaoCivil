app.controller('transferenciaEstoqueController', function($scope,transferenciaEstoqueService, saidaEstoqueService, produtoService, $routeParams, $timeout, $q, $log){
	
	var self = this;
	
		self.listaProduto =[];
		self.baixaEstoque = [];
		self.listaProdutos = [];
		$scope.produto =[];
		self.totalSoma = 0;
		var idEnviados = $routeParams.idEnviados;
		var idRecebidos = $routeParams.idRecebidos;
		
		self.lista = function(){
			transferenciaEstoqueService.lista().
				then(function(t){
					self.transferencias = t;
					}, function(errResponse){
				});
			};
			self.buscaPorCodigoBarras = function(codigoBarras){
				saidaEstoqueService.buscaPorCodigoBarras(codigoBarras).
				then(function(p){					
					self.listaProdutosComEstoques = p;
					console.log(p);
								self.produto.produto = self.listaProdutosComEstoques.produto;
								self.produto.quantidade = self.listaProdutosComEstoques.quantidade;
								//$scope.produto.custoMedio = self.listaProdutosComEstoques.custoMedio;								
				});
				
			};
			
			self.listaProdutosComEstoque = function(){
				transferenciaEstoqueService.listaProdutosComEstoque().
					then(function(t){
						self.listaProdutosComEstoques = t;
						for(i = 0; i < self.listaProdutosComEstoques.length; i++ ){
								self.produto = self.listaProdutosComEstoques[i].produto;
								self.quantidade = self.listaProdutosComEstoques[i].quantidade;
								self.custoMedio	=self.listaProdutosComEstoques[i].custoMedio;
							self.listaProdutos.push({
										produto : self.produto,
										quantidade : self.quantidade,
										valorUnitario: self.custoMedio
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
				if(self.listaProduto.length == 0){
					$scope.ativaTabela = false;
				}
			});
		}
			
			
			self.funcaoListaProduto = function(quantidadeEstoque, produto,  quantidadeSaida, empreendimento , valorUnitario){
				self.listaProduto.push({
					quantidadeEstoque : quantidadeEstoque,
					produto : produto,
					quantidade : quantidadeSaida,
					empreendimento : empreendimento,
					valorUnitario : valorUnitario,
					
				});
				self.limpaCampos();
			}
		
	self.verificaProdutoRepetido = function(quantidadeEstoque, produto,  quantidadeSaida, empreendimento , valorUnitario){
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
				self.funcaoListaProduto(quantidadeEstoque, produto,  quantidadeSaida, empreendimento, valorUnitario);
				}
			}
	
			self.SomaTotal = function(quantidadeEstoque, produto,  quantidadeSaida, empreendimento, valorUnitario){
								
				$scope.totaolItem = quantidadeSaida * valorUnitario;
				self.totalSoma += parseFloat($scope.totaolItem );
				$scope.valorTotal = self.totalSoma;
				}			
			
	
		
		self.adicionarProduto = function(quantidadeEstoque, produto,  quantidadeSaida, empreendimento, valorUnitario){
		
			if(quantidadeEstoque < quantidadeSaida){
				sweetAlert({ timer : 6000,  text :"Quantidade Superior ao estoque",  type : "info", width: 300, higth: 300, padding: 20});
					
				}
			else{if(quantidadeSaida < 1 || quantidadeSaida == null){
					sweetAlert({ timer : 6000,  text :"Quantidade tem que ser maior que zero",  type : "info", width: 300, higth: 300, padding: 20});
					
				}else{
					$scope.ativaTabela = true;
					if(self.listaProduto.length == 0){				
						self.funcaoListaProduto(quantidadeEstoque, produto,  quantidadeSaida, empreendimento, valorUnitario);
						self.SomaTotal(quantidadeEstoque, produto,  quantidadeSaida, empreendimento, valorUnitario);
					}
					else{
						self.verificaProdutoRepetido(quantidadeEstoque, produto, quantidadeSaida, empreendimento, valorUnitario);
						self.SomaTotal(quantidadeEstoque, produto,  quantidadeSaida, empreendimento, valorUnitario);
					}
				}
			}
		}
		self.salva = function(){		
			self.transferencia.notaFiscal.valorTotal = $scope.valorTotal;
			self.transferencia.itens = self.listaProduto;
			transferenciaEstoqueService.salva(self.transferencia)
			.then(function(response){
				self.limpaCampos();
				self.listaProdutosComEstoque();
				self.transferencia = null;
				self.produto = null;
				$scope.ativaTabela = false;
				self.listaProduto = self.listaProduto=[];
				}, function(errResponse){
					
			});
			
		};
		
		self.limpaCampos = function(){
			self.quantidadeEstoque = null;
			self.quantidadeSaida = null;
			$scope.quantidade = "";
		}
		
		self.buscaEnviados = function(id){
			if(!id)return;
			transferenciaEstoqueService.buscaEnviados(id).
			then(function(p){
				self.transferencia = p;
				console.log(self.transferencia);
			}, function(errResponse){
			});
		};
		self.buscaRecebidos = function(id){
			if(!id)return;
			transferenciaEstoqueService.buscaRecebidos(id).
			then(function(p){
				self.transferencia = p;
			}, function(errResponse){
			});
		};
		
		if(idEnviados){
			self.buscaEnviados(idEnviados);
		}
		if(idRecebidos){
			self.buscaRecebidosc(idRecebidos);
		}
		
		

});