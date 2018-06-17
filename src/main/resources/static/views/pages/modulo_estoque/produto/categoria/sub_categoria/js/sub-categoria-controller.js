app.controller("SubCategoriaCadastarController", SubCategoriaCadastarController);
app.controller("SubCategoriaEditarController", SubCategoriaEditarController);
app.controller("SubCategoriaListarController", SubCategoriaListarController);

function SubCategoriaCadastarController($stateParams, Auth, SubCategoriaService, CategoriaService, toastr, $rootScope, $scope, blockUI, $state) {


	var self = this;
	$scope.backPage = $stateParams.backPage;
	
	self.submit = submit;
	self.submitCategoria = submitCategoria;
	self.buscarPorTexto = buscarPorTexto;
	self.implementModal = implementModal;
	
	function submit(form) {	
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}	
		$scope.categoria == null ?'' : self.categoria.categoria = $scope.categoria;
		 blockUI.start();
		 SubCategoriaService.insert(self.categoria)
			.then(function(response) {
				toastr.success("Categoria, cadastrado")
				self.categoria = null;
				$scope.categoria = null;
				$scope.form.$setPristine();
				blockUI.stop();
				if($scope.backPage){
					$state.go($scope.backPage);
				}
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
	
	function submitCategoria(form, categoria) {	
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}	
		categoria.categoria = null;
		 blockUI.start();
			CategoriaService.insert(categoria)
			.then(function(response) {
				toastr.success("Categoria, cadastrado")
				$('.implementModal').modal('hide');
				$scope.categoria = null;
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
	 function buscarPorTexto(texto){
	     	return  CategoriaService.findByDescricao(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     }
	 
	function implementModal(objeto){
		$scope.objeto = objeto;
		$('.implementModal').modal('show');
	}
		
	 


}

function SubCategoriaEditarController(SubCategoriaService, CategoriaService , Auth,	$stateParams, $state, toastr, $rootScope, $scope, blockUI) {

	var self = this;

	var idCategoria = $stateParams.idCategoria;
	self.submit = submit;
	self.buscarPorId = buscarPorId;
	self.submitCategoria = submitCategoria;
	self.buscarPorTexto = buscarPorTexto;
	self.implementModal = implementModal;

	function submit(form) {	
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}	
		$scope.categoria == null ?'' : self.categoria.categoria = $scope.categoria;
		 blockUI.start();
		 SubCategoriaService.update(self.categoria)
			.then(function(response) {
				toastr.success("Sub Categoria, editado")
				$state.go('sub-categoria.consultar');
				blockUI.stop();
				if($scope.backPage){
					$state.go($scope.backPage);
				}
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
		
	function buscarPorId(id) {
		if (!id)
			return;
		SubCategoriaService.findById(id)
		.then(function(p) {
			self.categoria = p;
			$scope.categoria = p.categoria;
		}, function(errResponse) {
		});
	}
	;

	if (idCategoria) {
		self.buscarPorId(idCategoria);
	}
	
	function submitCategoria(form, categoria) {	
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}	
		categoria.categoria = null;
		 blockUI.start();
			CategoriaService.update(categoria)
			.then(function(response) {
				toastr.success("Categoria, cadastrado")
				$('.implementModal').modal('hide');
				$scope.categoria = null;
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
	 function buscarPorTexto(texto){
	     	return  CategoriaService.findByDescricao(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     }
	 
	function implementModal(objeto){
		$scope.objeto = objeto;
		$('.implementModal').modal('show');
	}
}

function SubCategoriaListarController(blockUI, $stateParams, $state, SubCategoriaService,
		toastr, $rootScope, $scope) {

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
		SubCategoriaService.findBySubCategoriaByDescricao(texto, self.paginaCorrente).then(
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
