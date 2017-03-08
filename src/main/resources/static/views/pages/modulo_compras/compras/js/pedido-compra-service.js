app.factory('pedidoCompraService', function($rootScope, toastr, $http, $q) {

    return {
        salva: function(pedidoCompra) {
            return $http.post('/rest/almoxarifado/pedidoCompra/salva', pedidoCompra)
                .then(function(response) {
                	toastr.info("Salvo com sucesso!!!");     return response.data;
                    },
                    function(errResponse) {
                    	sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
    					 return $q.reject(errResponse);
                    });
        },
        
        altera: function(pedidoCompra) {

            return $http.put('/rest/almoxarifado/pedidoCompra/altera', pedidoCompra)
                .then(function(response) {
                	toastr.info("Alterado com sucesso!!!");  return response.data;
                }, function(errResponse) {
                	sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					 return $q.reject(errResponse);
                });
        },       
        lista: function(){
			return $http.get('/rest/almoxarifado/pedidoCompra/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
        buscaPorId: function(param) {
            return $http.get('rest/almoxarifado/pedidoCompra/' + param)
                .then(function(response) {
                    return response.data;
                }, function(errResponse) {
                	sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					    return $q.reject(errResponse);
                });
        }
    }
});