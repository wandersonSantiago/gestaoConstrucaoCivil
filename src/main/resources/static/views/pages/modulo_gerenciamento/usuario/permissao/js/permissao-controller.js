app.controller('permissaoController', function($scope, toastr,  $rootScope, permissaoService, $location,  $http, $routeParams){
	
	var self = this;
	var idPermissao = $routeParams.idPermissao;
	$scope.moduloAdmin = [];
	
	self.modulo = function(){
		permissaoService.modulo().
				then(function(u){
					$scope.modulos = u;
					}, function(errResponse){
				});
			};  
		      
	
	
	self.altera = function(permissao){		
		permissaoService.altera(self.permissao).
			then(function(response){
				self.permissao = null;
				$location.path("/usuario/permissao/listagem");
				}, function(errResponse){
			});
		};	
	
	 self.salva = function(permissao){		
		 permissaoService.salva(self.permissao).
			then(function(response){
				self.permissao = null;
				}, function(errResponse){
			});
	};	
	
	 self.lista = function(){
		 permissaoService.lista().
			then(function(u){
				$scope.permissoes = u;		
				verificaTipoPermissao($scope.permissoes);
				}, function(errResponse){
			});
		};
		
		
		self.buscaPorId = function(id){
			if(!id)return;
			permissaoService.buscaPorId(id).
			then(function(p){
				self.permissao = p;
				}, function(errResponse){
			});
		};
		if(idPermissao){
			self.buscaPorId(idPermissao);
	}
		
		verificaTipoPermissao = function(permissoes){
			for(i = 0 ; i < permissoes.length ; i++){
			if(permissoes[i].tipoModulo == "ADMIN"){
				$scope.moduloAdmin.push(permissoes[i]);
				$scope.visualizaAdmin = true;
			}
			if(permissoes[i].tipoModulo == "CADASTROS"){
				$scope.moduloCadastros.push(permissoes[i]);
				$scope.visualizaCadastros = true;
			}
			if(permissoes[i].tipoModulo == "CHAMADO"){
				$scope.moduloChamado.push(permissoes[i]);
				$scope.visualizaChamado = true;
			}
			if(permissoes[i].tipoModulo == "COMPRAS"){
				$scope.moduloCompras.push(permissoes[i]);
				$scope.visualizaCompras = true;
			}
			if(permissoes[i].tipoModulo == "ESTOQUE"){
				$scope.moduloEstoque.push(permissoes[i]);
				$scope.visualizaEstoque = true;
			}
			if(permissoes[i].tipoModulo == "GERENCIAMENTO"){
				$scope.moduloGerenciamento.push(permissoes[i]);
				$scope.visualizaGerenciamento = true;
			}
			if(permissoes[i].tipoModulo == "RECURSOS_HUMANOS"){
				$scope.moduloRecursosHumanos.push(permissoes[i]);
				$scope.visualizaRecursosHumanos = true;
			}
			if(permissoes[i].tipoModulo == "SERVICOS"){
				$scope.moduloServicos.push(permissoes[i]);
				$scope.visualizaServicos = true;
			}
			}
		};
		
});