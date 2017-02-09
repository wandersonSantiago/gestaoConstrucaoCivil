app.controller('transferenciaEstoqueController', function($scope, $location, transferenciaEstoqueService, saidaEstoqueService, produtoService, $routeParams, $timeout, $q, $log){
	
	var self = this;
	
		self.listaProduto =[];
		self.baixaEstoque = [];
		self.listaProdutos = [];
		$scope.listaProdutos =[];
		self.getPage=0;
		self.totalPages = 0;
		self.totalElements = 0;
		$scope.maxResults = 15;
		$scope.produto =[];
		self.totalSoma = 0;
		var idEnviados = $routeParams.idEnviados;
		var idRecebidos = $routeParams.idRecebidos;
		
		
		
		self.verificaStatus = function(){
			if(self.transferencia.statusTransferencia == "PENDENTE"){
				$scope.status = true;
			}else{
				$scope.status = false;
			}
			
		}
		
		self.lista = function(){
			transferenciaEstoqueService.lista().
				then(function(t){
					self.transferencias = t;
					}, function(errResponse){
				});
			};
			
			self.listaEnviadas = function(){
				transferenciaEstoqueService.listaEnviadas().
					then(function(t){
						self.transferenciasEnviadas = t;
						}, function(errResponse){
					});
				};
				
			self.listaEnviadasComPaginacao = function(pages, maxResults){
				self.totalPages = [];
				self.getPage=pages;	
				transferenciaEstoqueService.listaEnviadasComPaginacao(pages, maxResults).
					then(function(t){
						self.transferenciasEnviadas = t.content;	
						$scope.totalPages = t.totalPages;
						self.totalElements = t.totalElements;
						for(i = 0; i < $scope.totalPages ; i++){
							self.totalPages.push(i);
						}	
						}, function(errResponse){
					});
				};	
				
			self.listaRecebidas = function(){
				transferenciaEstoqueService.listaRecebidas().
					then(function(t){
						self.transferenciasRecebidas = t;
						}, function(errResponse){
					});
				};
				
			self.listaRecebidasComPaginacao = function(pages, maxResults){
				self.totalPages = [];
				self.getPage=pages;	
				transferenciaEstoqueService.listaRecebidasComPaginacao(pages, maxResults).
					then(function(t){
						self.transferenciasRecebidas  = t.content;	
						$scope.totalPages = t.totalPages;
						self.totalElements = t.totalElements;
						for(i = 0; i < $scope.totalPages ; i++){
							self.totalPages.push(i);
						}	
						}, function(errResponse){
					});
				};		
				
			self.buscaPorCodigoBarras = function(codigoBarras){
				saidaEstoqueService.buscaPorCodigoBarras(codigoBarras).
				then(function(p){					
					self.listaProdutosComEstoques = p;
					console.log(p.produto.codigo);
								self.produto.produto = self.listaProdutosComEstoques.produto;
								self.produto.quantidade = self.listaProdutosComEstoques.quantidade;
								$scope.transfCtrl.produto.valorUnitario = self.listaProdutosComEstoques.custoMedio;	
								
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
					codigo : produto.codigo,
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
			$scope.produto = $scope.produto = [] ;
			$scope.transfCtrl.produto = null;
			$scope.transfCtrl.codigo = null;
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
		
		self.aceitaTransferencia = function(numeroNota){					 
			transferenciaEstoqueService.aceitaTransferencia(numeroNota)
			.then(function(response){	
				$location.path("/estoque/transferencia/recebidas");
				}, function(errResponse){
					
			});
			
		};
		
		self.rejeitaTransferencia = function(numeroNota){					 
			transferenciaEstoqueService.rejeitaTransferencia(numeroNota)
			.then(function(response){		
				$location.path("/estoque/transferencia/recebidas");
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
				for(i = 0; i < self.transferencia.itens.length; i++ ){
					self.produto = self.transferencia.itens[i].produto;
					self.quantidade = self.transferencia.itens[i].quantidade;
					self.valorUnitario	= self.transferencia.itens[i].valorUnitario;
					self.transferencia.notaFiscal.dataVencimento = new Date(self.transferencia.notaFiscal.dataVencimento);
					self.transferencia.notaFiscal.dataNota = new Date(self.transferencia.notaFiscal.dataNota);
					$scope.somaTotal(); 
				$scope.listaProdutos.push({
							produto : self.produto,
							quantidade : self.quantidade,
							valorUnitario: self.valorUnitario
					});
				self.verificaStatus();
			}
			}, function(errResponse){
			});
		};
		$scope.somaTotal = function(){
			$scope.totaolItem = self.quantidade * self.valorUnitario;
			self.totalSoma += parseFloat($scope.totaolItem );
			$scope.valorTotal = self.totalSoma;
		}
		
		
		
		self.buscaRecebidos = function(id){
			if(!id)return;
			transferenciaEstoqueService.buscaRecebidos(id).
			then(function(p){
				self.transferencia = p;
				for(i = 0; i < self.transferencia.itens.length; i++ ){
					self.produto = self.transferencia.itens[i].produto;
					self.quantidade = self.transferencia.itens[i].quantidade;
					self.valorUnitario	= self.transferencia.itens[i].valorUnitario;
					self.transferencia.notaFiscal.dataVencimento = new Date(self.transferencia.notaFiscal.dataVencimento);
					self.transferencia.notaFiscal.dataNota = new Date(self.transferencia.notaFiscal.dataNota);
					$scope.somaTotal(); 
				$scope.listaProdutos.push({
							produto : self.produto,
							quantidade : self.quantidade,
							valorUnitario: self.valorUnitario
					});
				self.verificaStatus();
			}
			}, function(errResponse){
			});
		};
		if(idEnviados){
			self.buscaEnviados(idEnviados);
		}
		if(idRecebidos){
			self.buscaRecebidos(idRecebidos);
		}
		
		

});