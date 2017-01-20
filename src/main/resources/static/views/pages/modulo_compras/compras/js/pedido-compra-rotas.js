app.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {


    $routeProvider

        .when('/compras/lista', {
            templateUrl:"views/pages/modulo_compras/compras/lista.html",
        })
        .when('/compras/cadastro/', {
            templateUrl:"views/pages/modulo_compras/compras/cadastro.html",
        })

        .when('/compras/visualizar/:idVisualizarCompras', {
            templateUrl:"views/pages/modulo_compras/compras/visualizar.html",
        })

}]);