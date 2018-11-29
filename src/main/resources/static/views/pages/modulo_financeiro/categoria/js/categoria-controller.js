app.controller("CategoriaFinanceiroCadastarController", CategoriaFinanceiroCadastarController);
app.controller("CategoriaFinanceiroEditarController", CategoriaFinanceiroEditarController);
app.controller("CategoriaFinanceiroListarController", CategoriaFinanceiroListarController);

function CategoriaFinanceiroCadastarController($stateParams, Auth, CategoriaFinanceiroService, CategoriaFinanceiroService, toastr, $rootScope, $scope, blockUI, $state) {


	var self = this;
	$scope.backPage = $stateParams.backPage;
	
	self.submit = submit;
	
	tipos();
	
	function submit(form) {	
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}	
		 blockUI.start();
		 CategoriaFinanceiroService.insert(self.categoriaFinanceiro)
			.then(function(response) {
				toastr.success("CategoriaFinanceiro, cadastrado")
				self.categoriaFinanceiro = null;
				$scope.form.$setPristine();
				blockUI.stop();
				if($scope.backPage){
					$state.go($scope.backPage);
				}
			}, function(errResponse) {
				blockUI.stop();
				sweetAlert({
					timer : 10000,
					text : errResponse.data.message,
					type : "error",
					width : 300,
					higth : 300,
					padding : 20
				});
			});
		} 
	
	
	 function tipos(){
	     CategoriaFinanceiroService.tipos().
	     	 then(function(e){
	     		 self.tipos = e;
	     	 }, function(errResponse){
	     	 });
	     }
	 
	 	 
	function implementModal(objeto){
		$scope.objeto = objeto;
		$('.implementModal').modal('show');
	}
		
	 


}

function CategoriaFinanceiroEditarController(CategoriaFinanceiroService, CategoriaFinanceiroService , Auth,	$stateParams, $state, toastr, $rootScope, $scope, blockUI) {

	var self = this;

	var idCategoriaFinanceiro = $stateParams.idCategoriaFinanceiro;
	self.submit = submit;
	self.buscarPorId = buscarPorId;
	self.implementModal = implementModal;

	tipos();
	
	function submit(form) {	
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}	
		 blockUI.start();
		 CategoriaFinanceiroService.update(self.categoriaFinanceiro)
			.then(function(response) {
				toastr.success("CategoriaFinanceiro, cadastrado")
				self.categoriaFinanceiro = null;
				$scope.form.$setPristine();
				blockUI.stop();
				if($scope.backPage){
					$state.go($scope.backPage);
				}
			}, function(errResponse) {
				blockUI.stop();
				sweetAlert({
					timer : 10000,
					text : errResponse.data.message,
					type : "error",
					width : 300,
					higth : 300,
					padding : 20
				});
			});
		} 
	
	
	 function tipos(){
	     CategoriaFinanceiroService.tipos().
	     	 then(function(e){
	     		 self.tipos = e;
	     	 }, function(errResponse){
	     	 });
	     }
	 
		
	function buscarPorId(id) {
		if (!id)
			return;
		CategoriaFinanceiroService.findById(id)
		.then(function(p) {
			self.categoria = p;
			$scope.categoria = p.categoria;
		}, function(errResponse) {
		});
	}
	;

	if (idCategoriaFinanceiro) {
		self.buscarPorId(idCategoriaFinanceiro);
	}
	
	function implementModal(objeto){
		$scope.objeto = objeto;
		$('.implementModal').modal('show');
	}
}

function CategoriaFinanceiroListarController(blockUI, $stateParams, $state, CategoriaFinanceiroService,
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
		CategoriaFinanceiroService.findByCategoriaFinanceiroByDescricao(texto, self.paginaCorrente).then(
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
