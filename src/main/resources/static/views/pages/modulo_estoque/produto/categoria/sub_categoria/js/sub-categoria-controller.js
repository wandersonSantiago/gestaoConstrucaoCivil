app.controller("SubCategoriaCadastarController", SubCategoriaCadastarController);
app.controller("SubCategoriaEditarController", SubCategoriaEditarController);
app.controller("SubCategoriaListarController", SubCategoriaListarController);

function SubCategoriaCadastarController(Auth, SubCategoriaService, toastr, $rootScope, $scope) {

	var self = this;

	self.submit = submit;

	function submit(categoria) {		
			SubCategoriaService.salvar(self.categoria).then(function(response) {
				toastr.success("SubCategoria, cadastrado")
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

function SubCategoriaEditarController(SubCategoriaService, Auth,	$stateParams, $state, toastr, $rootScope, $scope) {

	var self = this;

	var idSubCategoria = $stateParams.idSubCategoria;
	self.submit = submit;
	self.buscarPorId = buscarPorId;

	function submit(categoria) {
			SubCategoriaService.alterar(self.categoria).then(function(response) {
				toastr.info("SubCategoria Salvo!!!")
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
		SubCategoriaService.buscarPorId(id).then(function(p) {
			self.categoria = p;
		}, function(errResponse) {
		});
	}
	;

	if (idSubCategoria) {
		self.buscarPorId(idSubCategoria);
	}
}

function SubCategoriaListarController(blockUI, $stateParams, $state, SubCategoriaService,
		toastr, $rootScope, $scope) {

	var self = this;

	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;

	listar();

	function listar() {
		blockUI.start();
		SubCategoriaService.buscarPorTexto("", self.paginaCorrente).then(
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
		SubCategoriaService.buscarPorTexto(texto, self.paginaCorrente).then(
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
