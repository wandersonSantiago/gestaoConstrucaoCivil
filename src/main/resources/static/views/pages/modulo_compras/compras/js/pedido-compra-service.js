app.factory('pedidoCompraService', function($rootScope, toastr, $http, $q) {

    return {
        salvaPedidoCompra: function(pedidoCompra) {

            return $http.post('/rest/almoxarifado/estoque/pedidoCompra/salva'.pedidoCompra)
                .then(function(response) {
                        weetAlert({ timer: 3000, text: "Salvo com sucesso", type: "success", width: 300, higth: 100, padding: 20 });
                        return response.data;
                    },
                    function(errResponse) {
                        sweetAlert({ timer: 3000, text: "falha na conexão", type: "error", width: 300, higth: 300, padding: 20 });
                        return $q.reject(errResponse);
                    });
        },
        altera: function(pedidoCompra) {

            return $http.put('/rest/almoxarifado/pedidoCompra/salva', pedidoCompra)
                .then(function(response) {
                    sweetAlert({ timer: 3000, text: "Salvo com sucesso", type: "success", width: 300, higth: 100, padding: 20 });
                    return response.data;
                }, function(errResponse) {
                    sweetAlert({ timer: 3000, text: "falha na conexão", type: "error", width: 300, higth: 300, padding: 20 });
                    return $q.reject(errResponse);
                });
        },
        lista: function() {
            return $http.get('/rest/almoxarifado/pedidoCompra/lista')
                .then(function(response) {
                    return response.data;
                }, function(errResponse) {
                    sweetAlert({ timer: 3000, text: "falha na conexão", type: "error", width: 300, higth: 300, padding: 20 });
                    return $q.reject(errResponse);
                });
        },
        buscaPorPedidoCompraId: function(param) {
            return $http.get('rest/almoxarifado/pedidoCompra/' + param)
                .then(function(response) {
                    return response.data;
                }, function(errResponse) {
                    sweetAlert({ timer: 3000, text: "falha na conexão", type: "error", width: 300, higth: 300, padding: 20 });
                    return $q.reject(errResponse);
                });
        }
    }
});