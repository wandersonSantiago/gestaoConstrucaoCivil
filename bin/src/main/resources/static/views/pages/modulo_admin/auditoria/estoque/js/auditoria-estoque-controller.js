app.controller('auditoriaEstoqueController', function($scope, auditoriaEstoqueService,  $routeParams){
	
	var self = this;
 
		
	var idEmpresa =  $routeParams.idEmpresa;
	
	
	self.buscarEntrada = function(){
		auditoriaEstoqueService.buscarEntrada().
		then(function(e){			
			self.listaAuditoria = e;
		}, function(errResponse){
		});
	};
	
	self.buscarSaida = function(){
		auditoriaEstoqueService.buscarSaida().
		then(function(e){			
			self.listaAuditoria = e;
		}, function(errResponse){
		});
	};
	
	self.buscarTransferenciaEntrada = function(){
		auditoriaEstoqueService.buscarTransferenciaEntrada().
		then(function(e){			
			self.listaAuditoria = e;
		}, function(errResponse){
		});
	};
	
	self.buscarTransferenciaSaida = function(){
		auditoriaEstoqueService.buscarTransferenciaSaida().
		then(function(e){			
			self.listaAuditoria = e;
		}, function(errResponse){
		});
	};
	
	self.buscarRequisicao = function(){
		auditoriaEstoqueService.buscarRequisicao().
		then(function(e){			
			self.listaAuditoria = e;
		}, function(errResponse){
		});
	};


	self.buscaPorId = function(id){
		if(!id)return;
		auditoriaEstoqueService.buscaPorId(id).
		then(function(p){
			self.empresa = p;
		}, function(errResponse){
		});
	};	
	if(idEmpresa){
		self.buscaPorId(idEmpresa);
	}
	
	
});