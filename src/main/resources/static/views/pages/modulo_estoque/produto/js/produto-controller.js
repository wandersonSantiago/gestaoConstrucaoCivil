app.controller("ProdutoCadastarController", ProdutoCadastarController);
app.controller("ProdutoEditarController", ProdutoEditarController);
app.controller("ProdutoListarController", ProdutoListarController);

function ProdutoCadastarController($localStorage, $state, $stateParams, ProdutoService, SubCategoriaService, CategoriaService, FabricanteService, toastr, $scope){
	
	var self = this;	
	
	self.submit = submit;
	unidadesMedida();
	self.subCategoria = subCategoria;
	self.categoria = categoria;
	self.fabricante = fabricante
	$scope.backPage = $stateParams.backPage;
	self.proximaPagina = proximaPagina;
	
	if($localStorage.produto){
		self.produto = $localStorage.produto;
	}
	
	function proximaPagina(proxima){
		var backPage = 'produto.cadastrar';
		$state.go(proxima ,{backPage});
		$localStorage.produto = self.produto;
	}
	
	function submit(form){
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
				ProdutoService.insert(self.produto).
				then(function(response){
					toastr.success("Produto, cadastrado")
					self.produto = null;
					if($scope.backPage){
						$state.go($scope.backPage);
					}
					clear(form);
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
	function clear(form){	    	 
    	 form.$setPristine();
    	 form.$setUntouched();   
    	 self.produto = null;
    	 $localStorage.$reset()
    	
     }
		
	 function unidadesMedida(){
		 ProdutoService.findByUnidadesMedida().then(function(u){
			 self.unidadesMedida = u;
		 },function(erro){

		 })
	 };
	 function subCategoria(idCategoria){
		if(!idCategoria){
			return
		}
		self.subCategorias = null;
		SubCategoriaService.findByCategoriaId(idCategoria).then(function(c){
			self.subCategorias = c;
		},function(erro){
		})
	 };
			 
	 function categoria(texto){
	     	return  CategoriaService.findByDescricao(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     } ; 	
	 
	
	 function fabricante(texto){
	     	return  FabricanteService.buscarPorTexto(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     } ;	 	
	 
			
}		

function ProdutoEditarController($localStorage, $state, $stateParams, ProdutoService, SubCategoriaService, CategoriaService, FabricanteService, toastr, $scope){
	
	var self = this;
	
	var idProduto = $stateParams.idProduto;
	
	self.submit = submit;
	
	self.subCategoria = subCategoria;
	self.categoria = categoria;
	self.fabricante = fabricante
	self.proximaPagina = proximaPagina;
	unidadesMedida();
	findById(idProduto);
	
	if($localStorage.produto){
		self.produto = $localStorage.produto;
	}
	
	function proximaPagina(proxima){
		var backPage = 'produto.editar';
		$state.go(proxima ,{backPage});
		$localStorage.produto = self.produto;
	}
	
	function submit(form){
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
				ProdutoService.update(self.produto).
				then(function(response){
					toastr.success("Produto, Editado")
					self.produto = null;
					$state.go('produto.consultar');
					clear(form);
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});			
		};
		
	function clear(form){	
    	 $localStorage.$reset()
    	
     }
		
	 function unidadesMedida(){
		 ProdutoService.findByUnidadesMedida().then(function(u){
			 self.unidadesMedida = u;
		 },function(erro){

		 })
	 };
	 function subCategoria(idCategoria){
			if(!idCategoria){
				return
			}
			self.subCategorias = null;
			SubCategoriaService.findByCategoriaId(idCategoria).then(function(c){
				self.subCategorias = c;
			},function(erro){
			})
		 };
			 
	 function categoria(texto){
	     	return  CategoriaService.findByDescricao(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     } ; 	
	 
	 function fabricante(){
		FabricanteService.buscarPorTexto("").then(function(f){
			self.fabricante = f.content;
		},function(erro){
			console.log(erro); 
		})
	 };
			
	 function fabricante(texto){
	     	return  FabricanteService.buscarPorTexto(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     } ;	 	
	 	
	
	
	
	function findById(id){
		if(!id)return;
		ProdutoService.findById(id).
		then(function(p){
			self.produto = p;
			$scope.categoria = p.categoria.categoria;
			subCategoria(p.categoria.categoria.id)
			}, function(errResponse){
		});
	};
		
}

function ProdutoListarController(blockUI, ProdutoService, toastr, $scope){
	
	var self = this;
	
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = null;
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	
	buscarPorTexto('');
		 
	     
	     
	    function buscarPorTexto(texto){
	    	$scope.mensagemErro = null;	    	 
	    	 blockUI.start();
	    	 self.paginaCorrente == '0'? self.paginaCorrente = 0 : self.paginaCorrente = self.paginaCorrente - 1;   
	    	 ProdutoService.findByTextAndPagination(texto, self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		 self.produtos = e.content;	
	    		 self.totalElementos = e.totalElements;
	    		 self.totalPaginas = e.totalPages;
	    		 self.paginaCorrente = e.number + 1;
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
