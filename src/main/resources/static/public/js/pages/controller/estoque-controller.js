app.controller('estoqueController', function($scope,estoqueService, produtoService, $routeParams, $timeout, $q, $log){
	
	var self = this;
	
		self.listaProduto =[];
		self.baixaEstoque = [];
		self.listaProdutos = [];
		
		self.lista = function(){
			 produtoService.lista().
				then(function(t){
					self.produtos = t;
					}, function(errResponse){
				});
			};
			self.listaProdutosComEstoque = function(){
				estoqueService.listaProdutosComEstoque().
					then(function(t){
						self.listaProdutosComEstoques = t;
						
						for(i = 0; i < self.listaProdutosComEstoques.length; i++ ){
							for(c = 0; c < self.listaProdutosComEstoques[i].produto.length ; c++){
								self.produto = self.listaProdutosComEstoques[i].produto[c];
								self.quantidade = self.listaProdutosComEstoques[i];
							self.listaProdutos.push({
										produto : self.produto,
										quantidade : self.quantidade
									
								});
							}
						
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
						sweetAlert({ timer : 3000,  text :"ja consta este produto na tabela",  type : "info", width: 300, higth: 300, padding: 20});
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
				sweetAlert({ timer : 3000,  text :"Quantidade Superior ao estoque",  type : "info", width: 300, higth: 300, padding: 20});
					
				}else{
					$scope.ativaTabela = true;
					if(self.listaProduto.length == 0){				
						self.funcaoListaProduto(quantidadeEstoque, produto, areaProduto, quantidadeSaida, andar, torre , apartamento, NumeroCasa);				
					}else{
						self.verificaProdutoRepetido(quantidadeEstoque, produto, areaProduto, quantidadeSaida, andar, torre , apartamento, NumeroCasa);				
					}
				}
			}
			
		self.salva = function(){
			
			self.baixaEstoque = self.listaProduto;
			estoqueService.salva(self.baixaEstoque)
			.then(function(response){
					console.log("salvou");
					self.listaProdutosComEstoque();
				}, function(errResponse){
					console.log("não salvou");
			});
			
		};
		
self.verifica = function(verifica){
			
			if($scope.torreCheck == true){
				$scope.torre = true;
			}
			if($scope.andarCheck == true){
				$scope.andar = true;
				$scope.torre = true;
			}if($scope.apartamentoCheck == true){
				$scope.andar = true;
				$scope.torre = true;
				$scope.apartamento = true;
			}
			if($scope.torreCheck == false && $scope.andarCheck == false
					&& $scope.apartamentoCheck == false){
				$scope.torre = false;
			}
			if($scope.andarCheck == false && $scope.apartamentoCheck == false){
				$scope.andar = false;
				
			}if($scope.apartamentoCheck == false){
				
				$scope.apartamento = false;
			}
		}





//=================================================================================teste=========================


 /* var self = this;

  self.simulateQuery = false;
  self.isDisabled    = false;

  // list of `state` value/display objects
  self.states        = loadAll();
  self.querySearch   = querySearch;
  self.selectedItemChange = selectedItemChange;
  self.searchTextChange   = searchTextChange;

  self.newState = newState;

  function newState(state) {
    alert("Sorry! You'll need to create a Constitution for " + state + " first!");
  }

  // ******************************
  // Internal methods
  // ******************************

  *//**
   * Search for states... use $timeout to simulate
   * remote dataservice call.
   *//*
  function querySearch (query) {
    var results = query ? self.listaProdutos.filter( createFilterFor(query) ) : self.listaProdutos,
        deferred;
    if (self.simulateQuery) {
      deferred = $q.defer();
      $timeout(function () { deferred.resolve( results ); }, Math.random() * 1000, false);
      return deferred.promise;
    } else {
      return results;
    }
  }

  function searchTextChange(text) {
    $log.info('Text changed to ' + text);
  }

  function selectedItemChange(item) {
    $log.info('Item changed to ' + JSON.stringify(item));
  }

  *//**
   * Build `states` list of key/value pairs
   *//*
  //console.log($scope.listaProdutos);
  function loadAll() {
    return allStates.split(/, +/g).map( function (state) {
      return {
        value: state.toLowerCase(),
        display: allStates
     };
    });
  }

  *//**
   * Create filter function for a query string
   *//*
  function createFilterFor(query) {
    var lowercaseQuery = angular.lowercase(query);

    return function filterFn(state) {
      return (state.value.indexOf(lowercaseQuery) === 0);
    };

  }*/

		
});