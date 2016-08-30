app.controller('notaFiscalController', function($scope,notaFiscalService, $routeParams){
	
	var self = this;
		self.listaFornecedores = [];
	
	 self.salva = function(produto){
		 self.produto.fornecedores = self.listaFornecedores;
			 produtoService.salva(self.produto).
				then(function(response){
					self.produto = null;
					self.listaFornecedores = null;
					}, function(errResponse){
				});
	 }
	 self.altera = function(produto){
		 self.produto.fornecedores = self.listaFornecedores;
			 produtoService.altera(self.produto).
				then(function(response){
					self.produto = null;
					self.listaFornecedores = null;
					}, function(errResponse){
				});
	 }
	 
	 self.lista = function(){
		 produtoService.lista().
			then(function(t){
				self.produto = t;
				}, function(errResponse){
			});
		};
		
		self.codigoBarras = function(){
			
			swal({
				  title: 'Codigo de Barras Produto',
				  input: 'email',
				  showCancelButton: true,
				  confirmButtonText: 'Submit',
				  showLoaderOnConfirm: true,
				  preConfirm: function(email) {
				    return new Promise(function(resolve, reject) {
				      setTimeout(function() {
				        if (email === 'taken@example.com') {
				          reject('This email is already taken.');
				        } else {
				          resolve();
				        }
				      }, 2000);
				    });
				  },
				  allowOutsideClick: false
				}).then(function(email) {
				  swal({
				    type: 'success',
				    title: 'Ajax request finished!',
				    html: 'Submitted email: ' + email
				  });
				})
		}
		
	
		//cria uma lista de fornecedores
		self.adicionarFornecedores = function(){
			self.listaFornecedores.push({
				fornecedor : self.fornecedor
			});
			console.log(self.listaFornecedores);
			self.fornecedores = null;
		}
		
		self.ativarExcluirLote = function(listaFornecedores){
			console.log("ativado");
			self.listaFornecedores.filter(function(fornecedores){
			if(fornecedores.selecionado){
				$scope.ativadoExcluirLote = true;
			}
			});
		}
			

		//apagar outros empreendimentos, somente da lista de front
		self.apagarFornecedores = function(listaFornecedores){
			console.log("apagar");
				self.listaFornecedores = listaFornecedores.filter(function(fornecedores){
				if(!fornecedores.selecionado) return fornecedores;
				$scope.ativadoExcluirLote = null;
			});
		}
});