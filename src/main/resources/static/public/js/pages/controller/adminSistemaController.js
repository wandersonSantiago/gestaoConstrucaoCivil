app.controller('adminSistemaController', function($scope, adminSistemaService, adminSistemaService, $routeParams){
	
	var self = this;
	
	self.empresas = [];
	self.empresa = [];
	
	
	self.createEmpresa = function(empresa){
		adminSistemaService.empresaCreate()
		.then(function(empresa){
			self.empresa = empresa;
		}, function(errResponse){
			console.error('erro ao cadastrar empresa');
		});
	};
	
	self.empresaFinfAll = function(){
		adminSistemaService.empresaFindAll()
		.then(function(empresas){
			self.empresas = empresas;
		}, function(errResponse){
			console.error('erro na busca das empresas');
		});
	};

		
	self.deletarEmpresa = function(id){
		adminSistemaService.empresaDelete(id)
		.then(self.buscarEmpresas, function(errResponse){
			console.error('erro na busca das empresas');
			}
		
		);
	}
});