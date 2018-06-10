app.controller("ProdutoCadastarController", ProdutoCadastarController);
app.controller("ProdutoEditarController", ProdutoEditarController);
app.controller("ProdutoListarController", ProdutoListarController);

function ProdutoCadastarController(ProdutoService,SubCategoriaService,FabricanteService, toastr, $scope){
	
	var self = this;	
	
	self.submit = submit;
	unidadesMedida();
	subCategoria();
	fabricante();
	
	 function submit(produto){
				ProdutoService.insert(self.produto).
				then(function(response){
					toastr.success("Produto, cadastrado")
					self.produto = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
	 function unidadesMedida(){
		 ProdutoService.findByUnidadesMedida().then(function(u){
			 self.unidadesMedida = u;
		 },function(erro){

		 })
	 };
	 function subCategoria(){
		SubCategoriaService.findBySubCategoriaByDescricao("").then(function(c){
			self.subCategoria = c.content;
		},function(erro){
			console.log(erro); 
		})
	 };
	 function fabricante(){
		FabricanteService.buscarPorTexto("").then(function(f){
			self.fabricante = f.content;
		},function(erro){
			console.log(erro); 
		})
	 };
			
		 	
	 
			
}		

function ProdutoEditarController($stateParams, $state , ProdutoService, toastr, $scope){
	
	var self = this;
	
	var idProduto = $stateParams.idProduto;
	self.submit = submit;
	
	function submit(produto){		
			ProdutoService.update(self.produto).
			then(function(response){
				toastr.info("Produto Alterado!!!")
				self.produto = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000, text: errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});					
			});		
	};	
	
	
	
	function findById(id){
		if(!id)return;
		ProdutoService.buscarPorId(id).
		then(function(p){
			self.produto = p;
			}, function(errResponse){
		});
	};

	if(idProduto){
		findById(idProduto);		
	}
	
	
}

function ProdutoListarController(blockUI, ProdutoService, toastr, $scope){
	
	var self = this;
	
	self.findByTextAndPagination = findByTextAndPagination;
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	
	findAllPagination();
	

	  function findAllPagination(){
		  blockUI.start();
		  ProdutoService.findByTextAndPagination("", self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
		    		self.produtos = e.content;	
		    		 self.totalElementos = e.totalElements;
		    		 self.totalPaginas = e.totalPages;
		    		 blockUI.stop();
	    	 }, function(errResponse){
	    		 blockUI.stop();
	    	 });
	     };
	     
	     
	    function findByTextAndPagination(texto){
	    	$scope.mensagemErro = null;
	    	 if(!texto || texto.length < 3){
	    		 $scope.mensagemErro = "Digite pelo menos 3 caracters";
	    		 return;
	    	 }
	    	 blockUI.start();
	    	 ProdutoService.findByTextAndPagination(texto, self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		self.produtos = e.content;	
	    		 self.totalElementos = e.totalElements;
	    		 self.totalPaginas = e.totalPages;
	    		 blockUI.stop();
	    	 }, function(errResponse){
	    		 blockUI.stop();
	    		 if(errResponse.status == 404){
	    			 $scope.mensagemErro = errResponse.data.message;
	    		 }else{
	    			 $scope.mensagemErro =errResponse.data.message;
	    		 }
			 });
	    }
	
}