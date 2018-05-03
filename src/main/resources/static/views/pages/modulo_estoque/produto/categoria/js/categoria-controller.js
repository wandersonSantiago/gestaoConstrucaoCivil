app.controller("CategoriaCadastarController", CategoriaCadastarController);
app.controller("CategoriaEditarController", CategoriaEditarController);
app.controller("CategoriaListarController", CategoriaListarController);

function CategoriaCadastarController(blockUI,CategoriaService, toastr, $scope) {

	var self = this;

	self.submit = submit;
	
	function submit(form) {	
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}	
		$scope.categoria == null ?'' : self.categoria.categoria = $scope.categoria;
		 blockUI.start();
			CategoriaService.insert(self.categoria)
			.then(function(response) {
				toastr.success("Categoria, cadastrado")
				self.categoria = null;
				$scope.categoria = null;
				$scope.form.$setPristine();
				blockUI.stop();
			}, function(errResponse) {
				blockUI.stop();
				sweetAlert({
					timer : 3000,
					text : errResponse.data.message,
					type : "error",
					width : 300,
					higth : 300,
					padding : 20
				});
			});
		} 

}

function CategoriaEditarController(CategoriaService, Auth,	$stateParams, $state, toastr, $rootScope, $scope) {

	var self = this;

	var idCategoria = $stateParams.idCategoria;
	self.submit = submit;
	self.buscarPorId = buscarPorId;

	function submit(categoria) {
			CategoriaService.update(self.categoria).then(function(response) {
				toastr.info("Categoria Alterada!!!")
				self.categoria = null;
				$state.go('categoria.consultar');
			}, function(errResponse) {
				sweetAlert({
					timer : 3000,
					text : errResponse.data.message,
					type : "info",
					width : 300,
					higth : 100,
					padding : 20
				});

			});
		}
		
	function buscarPorId(id) {
		if (!id)
			return;
		CategoriaService.findById(id).then(function(p) {
			self.categoria = p;
		}, function(errResponse) {
		});
	}
	;

	if (idCategoria) {
		self.buscarPorId(idCategoria);
	}
}

function CategoriaListarController(blockUI, $stateParams, $state, CategoriaService,	toastr, $rootScope, $scope) {

	var self = this;

	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;

	buscarPorTexto('');
	

	function buscarPorTexto(texto) {
		$scope.mensagemErro = null;
		blockUI.start();
		self.paginaCorrente == '0'? self.paginaCorrente = 0 : self.paginaCorrente = self.paginaCorrente - 1; 
		CategoriaService.findByDescricaoPagination(texto, self.paginaCorrente).then(
				function(e) {
					$scope.mensagemErro = null;
					self.categorias = e.content;
					self.totalElementos = e.totalElements;
					self.totalPaginas = e.totalPages;
					self.paginaCorrente = e.number + 1;
					blockUI.stop();
				}, function(errResponse) {
					blockUI.stop();
					if (errResponse.status == 404) {
						$scope.mensagemErro = errResponse.data.message;
					} else {
						$scope.mensagemErro = errResponse.data.message;
					}
				});
	}

}
