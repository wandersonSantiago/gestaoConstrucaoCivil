app.controller("PermissaoCadastarController", PermissaoCadastarController);
app.controller("PermissaoEditarController", PermissaoEditarController);
app.controller("PermissaoListarController", PermissaoListarController);


function PermissaoCadastarController( PermissaoService,  toastr, $rootScope, $scope){
	
	var self = this;
	
	self.submit = submit;
	listarModulo();
	
	 function submit(permissao){
				PermissaoService.salvar(self.permissao).
				then(function(response){
					self.permissao = null;
					toastr.info("Permissoes Salva!!")
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});		
		};	
	
	
		
	 function listarModulo(){
		 PermissaoService.modulos().
			then(function(f){
				self.modulos = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};	
		
}		

function PermissaoEditarController(PermissaoService, $stateParams, $state , toastr, $rootScope, $scope){
	
	var self = this;
	
	var idPermissao = $stateParams.idPermissao;

	self.submit = submit;
	listarModulo();
	
	 function submit(permissao){
				PermissaoService.salvar(self.permissao).
				then(function(response){
					toastr.info("Permissoes Editada!!")
					$state.go('permissao.listar');
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});		
		};	
	
	
		
	 function listarModulo(){
		 PermissaoService.modulos().
			then(function(f){
				self.tipoModulos = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};	
	
	function buscarPorId(id){
		if(!id)return;
		PermissaoService.buscarPorId(id).
		then(function(p){
			self.permissao = p;
			}, function(errResponse){
		});
	};

	if(idPermissao){
		buscarPorId(idPermissao);		
	}
	
	
}

function PermissaoListarController($stateParams, $state , PermissaoService, toastr, $rootScope, $scope){
	
	var self = this;
		
	listar();
	
	
	 function listar(){
		 PermissaoService.listar().
			then(function(u){				
					self.permissoes = u;			
				}, function(errResponse){
			});
		};		
	
}