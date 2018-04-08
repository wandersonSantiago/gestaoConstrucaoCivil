app.factory('role', function($rootScope, toastr, $http, $q, usuarioService , $location){
	
	
	return{
	
		permission : function(){
			usuarioService.user().
			then(function(u){
				$rootScope.user = u;
			for(i = 0 ; i < $rootScope.user.usuario.perfilsUsuario.length ; i ++){
				
				if($rootScope.user.usuario.perfilsUsuario[i] == "ADMIN"){
					$rootScope.ROLE_ADMIN = true;					
					}else{
						if($location.path() == '/empresaContratada/cadastrar'){
							$location.path('/semAcesso');
							}					
				}
				if($rootScope.user.usuario.perfilsUsuario[i] == "ADMINISTRADOR_EMPRESA"){
					$rootScope.ROLE_ADMINISTRADOR_EMPRESA = true;					
					}else{
						if($location.path() == '/empresaContratada/cadastrar'){
							$location.path('/semAcesso');
							}					
				}
				if($rootScope.user.usuario.perfilsUsuario[i] == "GESTOR"){
					$rootScope.ROLE_GESTOR = true;					
					}else{
						if($location.path() == '/empresaContratada/cadastrar'){
							$location.path('/semAcesso');
							}					
				}
				
				//ESTOQUE
				if($rootScope.user.usuario.perfilsUsuario[i] == "ESTOQUE_CADASTRO"){
					$rootScope.ROLE_ESTOQUE_CADASTRO = true;					
					}else{
						if($location.path() == '/empresaContratada/cadastrar'){
							$location.path('/semAcesso');
							}					
				}
				if($rootScope.user.usuario.perfilsUsuario[i] == "ESTOQUE_CONSULTA"){
					$rootScope.ROLE_ESTOQUE_CONSULTA = true;					
					}else{
						if($location.path() == '/empresaContratada/cadastrar'){
							$location.path('/semAcesso');
							}					
				}
				
				//COTACAO
				
				if($rootScope.user.usuario.perfilsUsuario[i] == "COTACAO_CONSULTA"){
					$rootScope.ROLE_COTACAO_CONSULTA = true;					
					}else{
						if($location.path() == '/empresaContratada/cadastrar'){
							$location.path('/semAcesso');
							}					
				}
				if($rootScope.user.usuario.perfilsUsuario[i] == "COTACAO_CADASTRO"){
					$rootScope.ROLE_COTACAO_CADASTRO = true;					
					}else{
						if($location.path() == '/empresaContratada/cadastrar'){
							$location.path('/semAcesso');
							}					
				}
				
				
			}
				
			});
		}
		
	
	}
});