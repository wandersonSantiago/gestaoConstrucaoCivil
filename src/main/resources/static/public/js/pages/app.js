var app = angular.module('gcc',['ngAnimate','ngRoute','ngMaterial', 'angucomplete-alt','ngResource','toastr','ui.bootstrap','blockUI', 'ngStorage','ui.utils.masks', 'ui.mask'])
.run(function ($rootScope, $location, usuarioService, auth) {
	 
	var self = this;
	  $rootScope.$on('$locationChangeStart', function () { 
		self.role = function(){
				usuarioService.user().
					then(function(u){
						$rootScope.user = u;
						if($rootScope.user.usuario.empreendimento.tipoEmpreendimento ==  "CONDOMINIO_DE_EDIFICIO_RESIDENCIAL"){
							$rootScope.tipoEmpreendimento = true;
						}else{
							$rootScope.tipoEmpreendimento = false;
						}
						for(i = 0 ; i < $rootScope.user.usuario.perfilsUsuario.length ; i++ ){	
							
						var	perfil = $rootScope.user.usuario.perfilsUsuario[i];
						
							self.role_admin(perfil);
							self.role_administrador_empresa(perfil);
							self.role_gestor(perfil);
							self.role_estoque_cadastro(perfil);
							self.role_estoque_consulta(perfil);
							self.role_cotacao_consulta(perfil);
							self.role_cotacao_cadastro(perfil);
							
						}
						}, function(errResponse){
					});
				};
							
				
				self.role_admin = function(perfil){
					if(perfil == "ADMIN" ){
						$rootScope.ROLE_ADMIN = true;						
					}
				}
				
				self.role_administrador_empresa = function(perfil){
					if(perfil == "ADMINISTRADOR_EMPRESA" ){
						$rootScope.ROLE_ADMINISTRADOR_EMPRESA = true;						
					}
				}
				
				self.role_gestor = function(perfil){					
					if(perfil == "GESTOR" ){
						$rootScope.ROLE_GESTOR = true;
					}
				}
				
				self.role_estoque_cadastro = function(perfil){
					if(perfil == "ESTOQUE_CADASTRO"){
						$rootScope.ROLE_ESTOQUE_CADASTRO = true;
					}
				}	
				
				self.role_estoque_consulta = function(perfil){
					if(perfil == "ESTOQUE_CONSULTA"){
						$rootScope.ROLE_ESTOQUE_CONSULTA = true;
					}					
				}
				
				self.role_cotacao_consulta = function(perfil){
					if(perfil == "COTACAO_CONSULTA"){
						$rootScope.ROLE_COTACAO_CONSULTA = true;
					}
				}
				
				self.role_cotacao_cadastro = function(perfil){
					if(perfil == "COTACAO_CADASTRO"){
						$rootScope.ROLE_COTACAO_CADASTRO = true;
					}
				}
				
				
				
				 self.role();
				 
				 var tempo = new Number();
					
				 tempo = 1800;
				
		 startCountdown = function(){		     
		 
		     if((tempo - 1) >= 0){		    	 	
		
		         var min = parseInt(tempo/60);	         
		 
		         var seg = tempo%60;	  
		
		         if(min < 10){
		
		             min = "0"+min;
		
		             min = min.substr(0, 2);
		 
		         }
		 
		         if(seg <=9){
		 
		             seg = "0"+seg;		 
		         }		  
		 
		         $rootScope.horaImprimivel = '00:' + min + ':' + seg;
		 			         	
		        
		         $rootScope.teste =  setTimeout('startCountdown()',1000); 
		         
		         tempo--;		
		 
		     } else {
		 
		    	 auth.clear();
		    	 clearTimeout($rootScope.teste);
		     }
		 }					
		 clearTimeout($rootScope.teste);
				 startCountdown();
				 
	  });
	});
