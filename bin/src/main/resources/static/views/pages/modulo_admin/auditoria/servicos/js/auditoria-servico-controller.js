app.controller('auditoriaServicoController', function($scope, auditoriaServicoService,  $routeParams){
	
	var self = this;
 
	self.getPage=0;
	self.totalPages = 0;
	self.totalElements = 0;
	$scope.maxResults = 15;
	
	var idEmpresa =  $routeParams.idEmpresa;
	
	
	self.buscar = function(pages, maxResults){
		self.totalPages = [];
		self.getPage=pages;		
		auditoriaServicoService.buscarComPaginacao(pages, maxResults).
		then(function(e){			
			self.listaAuditoria = e.content;
			$scope.totalPages = e.totalPages;
			self.totalElements = e.totalElements;
			for(i = 0; i < $scope.totalPages ; i++){
				self.totalPages.push(i);
			}
		}, function(errResponse){
		});
	};


	self.buscaPorId = function(id){
		if(!id)return;
		auditoriaServicoService.buscaPorId(id).
		then(function(p){
			self.empresa = p;
		}, function(errResponse){
		});
	};	
	if(idEmpresa){
		self.buscaPorId(idEmpresa);
	}
	
	
});