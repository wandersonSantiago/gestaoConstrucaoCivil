app.controller('pedidoCompraController', function($scope, $rootScope, pedidoCompraService, $location, $router, $routerParams) {

    var self = this;

    self.listaProdutos = [];
    $scope.listaProdutos = [];
    $scope.produto[];
    self.totalSoma = 0;

    self.lista = function() {

        pedidoCompraService.lista(),
            then(function(t) {
                self.pedidoCompra = t;
            }, function(errResponse) {

            });
    };
    self.salva = function() {
        self.pedidoCompra.itens = self.listaProdutos;
        pedidoCompraService.salva(self.pedidoCompra)
            .then(function(response) {
                self.pedidoCompra = null;
                self.produto = null;
                self.listaProdutos = self.listaProdutos = [];
            }, function(errResponse) {

            });
    };
    self.buscarPorId = function(id) {
        pedidoCompraService.buscarPorId(id)
            .then(function(p) {
                self.listaProdutos = p;
            });
    }
});