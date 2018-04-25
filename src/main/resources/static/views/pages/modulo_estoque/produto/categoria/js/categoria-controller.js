app.controller("CategoriaCadastarController", CategoriaCadastarController);
app.controller("CategoriaEditarController", CategoriaEditarController);
app.controller("CategoriaListarController", CategoriaListarController);

function CategoriaCadastarController(Auth, CategoriaService, toastr, $rootScope, $scope) {

	var self = this;

	self.submit = submit;

	function submit(categoria) {		
			CategoriaService.salvar(self.categoria).then(function(response) {
				toastr.success("Categoria, cadastrado")
				self.categoria = null;
			}, function(errResponse) {
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
			CategoriaService.alterar(self.categoria).then(function(response) {
				toastr.info("Categoria Salvo!!!")
				self.categoria = null;
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
		CategoriaService.buscarPorId(id).then(function(p) {
			self.categoria = p;
		}, function(errResponse) {
		});
	}
	;

	if (idCategoria) {
		self.buscarPorId(idCategoria);
	}
}

function CategoriaListarController(blockUI, $stateParams, $state, CategoriaService,
		toastr, $rootScope, $scope) {

	var self = this;

	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;

	listar();

	function listar() {
		blockUI.start();
		CategoriaService.buscarPorTexto("", self.paginaCorrente).then(
				function(e) {
					$scope.mensagemErro = null;
					self.categorias = e.content;
					self.totalElementos = e.totalElements;
					self.totalPaginas = e.totalPages;
					blockUI.stop();
				}, function(errResponse) {
					blockUI.stop();
				});
	}
	;

	function buscarPorTexto(texto) {
		$scope.mensagemErro = null;
		if (!texto || texto.length < 3) {
			$scope.mensagemErro = "Digite pelo menos 3 caracters";
			return;
		}
		blockUI.start();
		CategoriaService.buscarPorTexto(texto, self.paginaCorrente).then(
				function(e) {
					$scope.mensagemErro = null;
					self.categorias = e.content;
					self.totalElementos = e.totalElements;
					self.totalPaginas = e.totalPages;
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
