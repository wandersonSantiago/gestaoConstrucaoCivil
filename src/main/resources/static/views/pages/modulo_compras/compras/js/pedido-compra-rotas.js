app.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {


    $routeProvider

        .when('/compras/pedido', {
            templateUrl: "views/pages/modulo_compras/compras/pedidos.html",
        })
        .when('/compras/cadastro/', {
            templateUrl: "view/pages/modulo_compras/compras/cadastro.html",
        })



}]);