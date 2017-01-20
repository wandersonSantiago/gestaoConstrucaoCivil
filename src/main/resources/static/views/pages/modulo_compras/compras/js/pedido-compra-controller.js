app.controller('pedidoCompraController', function($scope, $rootScope, pedidoCompraService, produtoService, $routeParams, $location) {

    var self = this;
    self.pedidoCompra = {};
    self.totalSoma = 0;
    	var idVisualizarCompras = $routeParams.idVisualizarCompras;
  //  self.listaItensNota = [];
    self.listaItensProduto = [];
    
    
    self.lista = function() {
        pedidoCompraService.lista()
            .then(function(t) {
                self.pedidoCompras = t;
            }, function(errResponse) {

            });
    };
    self.salva = function() {
        self.pedidoCompra.itens = self.listaItensProduto;
        pedidoCompraService.salva(self.pedidoCompra)
            .then(function(response) {
                self.pedidoCompra = null;
                self.produto = null;
                self.listaItensProduto = self.listaItensProduto = [];
            }, function(errResponse) {

            });
    };
    self.buscarPorId = function(id) {
        pedidoCompraService.buscaPorId(id)
            .then(function(p) {
            	console.log(p);
            	self.pedidoCompra.previsao = new Date(p.previsao);
            	self.pedidoCompra = p;
            	self.listaItensProduto = self.pedidoCompra.itens;
            });
    }
    
    if(idVisualizarCompras){
    	self.buscarPorId(idVisualizarCompras);
    }
    
    self.buscaPorCodigoBarras = function(codigoBarras){
		produtoService.buscaPorCodigoBarras(codigoBarras).
		then(function(p){
			self.produtos = p;
			if(p){
				$scope.pedidoCompraCtrl.produto = null;
				self.adicionarProdutos(self.produtos);						
			}		
		},function(errResponse){
		});
	};
	
	self.adicionarProdutos = function(produto){
		$scope.pedidoCompraCtrl.produto = null;
	if(self.listaItensProduto.length == 0){				
			self.salvaProdutoNaLista(produto);				
		}
		else{
			self.verificaProdutoRepetido(produto);				
		}
	};
	
	self.salvaProdutoNaLista = function(produto){
		self.listaItensProduto.push({
			produto 
		});
		produto = "";
		$scope.visualizarTable = true;
	};
	
	self.verificaProdutoRepetido = function(produto){
		for(i = 0; i < self.listaItensProduto.length ; i ++){
				var item = self.listaItensProduto[i];
				var produto1 = item.produto.id;
			if(produto1 != produto.id){
					self.existe = true;
				}else{
					sweetAlert({ timer : 3000,  text :"ja consta este produto na tabela",  type : "info", width: 300, higth: 300, padding: 20});
					var tamanho = self.listaItensProduto[i];
					i = tamanho[i + 1];
					self.existe = false;
				}
			}
		if(self.existe){
		self.salvaProdutoNaLista(produto);
		}
	}
	
	self.SomaTotal = function(listaItensProduto){
		var totalSoma = 0;
		for(i =0; i < listaItensProduto.length ; i ++){
			var total = listaItensProduto[i];
			totalSoma += parseFloat(total.valorTotal);	
			$scope.valorTotalNota = totalSoma;
				}
		}
	
	$scope.somaUnitario = function(quantidade, valorUnitario){
		return $scope.valorTotal = quantidade * valorUnitario;
	}
	
	
	
	self.ativarExcluirLote = function(listaItensProduto){
		self.listaItensProduto.filter(function(f){
		if(f.selecionado){
			$scope.ativadoExcluirLote = true; }
		});
	}
	
	self.apagarProdutos = function(listaItensProduto){
		self.listaItensProduto = self.listaItensProduto.filter(function(f){
		if(!f.selecionado) return f;
		$scope.valorTotalNota -= f.valorTotal;
		$scope.ativadoExcluirLote = false;
	});
}
});