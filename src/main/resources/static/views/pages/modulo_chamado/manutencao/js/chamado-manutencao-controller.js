app.controller('chamadoManutencaoController', function($scope, $rootScope, $timeout, chamadoManutencaoService, $location,  usuarioService,  $routeParams) {

	var self = this;
	$scope.habilitaTexto = true;
	$scope.habilitaBotaoAtenderChamado = false;
	$scope.habilitaBotaoFecharChamado = false;
	$rootScope.atualizarListaChamado = false;
	self.getPage=0;
	self.totalPages = 0;
	self.totalElements = 0;
	$scope.maxResults = 15;
	self.contagemSuporte = [];
	var idChamadoManutencao = $routeParams.idChamadoManutencao;
	
	
	self.atualizaListaChamadoSuporte = function(){
		$rootScope.atualizarListaChamado = true;
		if($rootScope.atualizarListaChamado === true ){
				self.verificaMensagemLidaAtualizada();
			}
	};
	
	
	self.verificaMensagemLida = function(){			
		if($rootScope.atualizarListaChamado === false && $rootScope.logado){
			self.listaSuporte();			
			timeoutLida = setTimeout(self.verificaMensagemLida, 40000);			
		}
	};
	
	self.verificaMensagemLidaAtualizada = function(){
		self.listaSuporte();	
		$rootScope.atualizarListaChamado = false;
		clearTimeout(timeoutLida);
		self.verificaMensagemLida();			
	};
	
	self.salva = function(chamadoManutencao) {
		self.chamadoManutencao.mensagens = [{texto : $scope.texto}];
		chamadoManutencaoService.salva(self.chamadoManutencao).
			then(function(response){
				self.chamadoManutencao = null;
				$scope.texto = null;
				}, function(errResponse){
			});
		};
	
	self.silenciarChamadoFalse = function(chamadoManutencao) {
		chamadoManutencao.mensagens = null;
		chamadoManutencaoService.silenciarChamadoFalse(chamadoManutencao).
			then(function(response){				
				self.listaSuporte();
				}, function(errResponse){					
			});
		};
		
		self.silenciarChamadoTrue = function(chamadoManutencao) {
			chamadoManutencao.mensagens = null;
			chamadoManutencaoService.silenciarChamadoTrue(chamadoManutencao).
				then(function(response){
					self.listaSuporte();
					}, function(errResponse){						
				});
			};
	
	self.salvaMensagem = function(chamadoManutencao) {
		self.chamadoManutencao.mensagens = null;
		self.chamadoManutencao.mensagens = [{texto : $scope.texto}];
		chamadoManutencaoService.salvaMensagem(self.chamadoManutencao).
		then(function(response){
			$scope.texto = null;
			self.buscarPorId(idChamadoManutencao);
			}, function(errResponse){
		});
	};
	self.salvaServicos = function(descricao) {	
		self.chamadoManutencao.mensagens = null;
		self.chamadoManutencao.descricaoServico = descricao;
		chamadoManutencaoService.salvaServicos(self.chamadoManutencao).
		then(function(response){	
			self.buscarPorId(self.chamadoManutencao.id);
			}, function(errResponse){
		});
	};
	self.atenderChamado = function(chamadoManutencao) {
		self.chamadoManutencao.mensagens = null;
		swal({
			  title: 'Atender Chamado!!!',
			  text: "Tem que certeza que deseja atender este chamado?",
			  type: 'info',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Atender!'
			}).then(function () {
				chamadoManutencaoService.atenderChamado(self.chamadoManutencao).
				then(function(response){
					$scope.texto = null;
					self.buscarPorId(idChamadoManutencao);
					self.verificaMensagemLida();
					}, function(errResponse){
				});			  
			})		
	};
	
	self.fecharChamado = function(chamadoManutencao) {
		self.chamadoManutencao.mensagens = null;
		swal({
			  title: 'Encerrar Chamado!!!',
			  text: "Tem que certeza que deseja encerrar este chamado?",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Encerrar!'
			}).then(function () {
		chamadoManutencaoService.fecharChamado(self.chamadoManutencao).
		then(function(response){
			$scope.texto = null;
			self.buscarPorId(idChamadoManutencao);
			}, function(errResponse){
		});
	})
};
	
	var vid = document.getElementById("myVideo");
	
	self.enableAutoplay = function() { 
	    vid.autoplay = true;
	    vid.load();
	};
	self.disableAutoplay = function() { 
	    vid.autoplay = false;
	    vid.load();
	};


	
	 self.listaSuporte = function(){
		 chamadoManutencaoService.listaSuporte().
			then(function(f){
				self.listaChamadoManutencaoSuporte = f;	
				self.tocaMusica = 0;
				self.contagemSuporte = [];
				for(i = 0 ; i < self.listaChamadoManutencaoSuporte.length ; i++){						
							if(self.listaChamadoManutencaoSuporte[i].lido === false){
								self.contagemSuporte.push({
									cout : self.listaChamadoManutencaoSuporte[i]
								});							
								$rootScope.quantidadeChamadoAberto = self.contagemSuporte.length; 								
					}
					if(self.listaChamadoManutencaoSuporte[i].lido === false && self.listaChamadoManutencaoSuporte[i].silenciar === false){
						
						self.tocaMusica = 1;
					}
					if(self.tocaMusica > 0 ){
						self.enableAutoplay(); 						
					}else{
						self.disableAutoplay();
					}
					
				}
				}, function(errResponse){
			});
		 
		};
		
		
		self.listaUsuario = function(){
			 chamadoManutencaoService.listaUsuario().
				then(function(f){
					self.listaChamadoManutencaoUsuario = f;					
					}, function(errResponse){
				});
			};
							
			self.usuarioLista = function(){
				 usuarioService.lista().
					then(function(f){
						self.usuarios = f;				
						}, function(errResponse){
					});
				};
		 self.prioridade = function(){
			 chamadoManutencaoService.prioridade().
				then(function(f){
					self.prioridades = f;			
					}, function(errResponse){
				});
			};
		
			self.status = function(){
				chamadoManutencaoService.status().
					then(function(f){
						self.listaStatus = f;			
						}, function(errResponse){
					});
				};
			self.titulo = function(){
				chamadoManutencaoService.titulo().
					then(function(f){
						self.titulos = f;			
						}, function(errResponse){
					});
				};
	self.buscarPorId = function(id){
			if(!id)return;
			chamadoManutencaoService.buscarPorId(id).
			then(function(p){
				self.chamadoManutencao = p;
				if(self.chamadoManutencao.status == "EM_ANDAMENTO"){	
					$scope.habilitaTexto = true;
					$scope.habilitaBotaoFecharChamado = true;
					$scope.habilitaBotaoAtenderChamado = false;
					$scope.habilitaBotaoServicoChamado = true;
				}else if(self.chamadoManutencao.status == "ABERTO"){
					$scope.habilitaBotaoAtenderChamado = true;
					$scope.habilitaBotaoFecharChamado = false;
					$scope.habilitaTexto = false;
					$scope.habilitaBotaoServicoChamado = false;
				}else if(self.chamadoManutencao.status == "FECHADO"){
					$scope.habilitaTexto = false;
					$scope.habilitaBotaoFecharChamado = false;
					$scope.habilitaBotaoServicoChamado = true;
				}
				for(i = 0 ; i < self.chamadoManutencao.mensagens.length ; i++){			
				
					if(self.chamadoManutencao.mensagens[i].usuario.id != $rootScope.user.usuario.id){
						
						self.chamadoManutencao.mensagens[i].usuario.id = null;
					}else {					
					}
					$rootScope.quantidadeMensagem = i + 1;
				}
		}, function(errResponse){
			});
		};
	
		if(idChamadoManutencao){
			self.buscarPorId(idChamadoManutencao);
			
		}
				
		self.buscaDinamicaUsuario = function(usuario){
			$scope.buscaChamado = null;
			$scope.buscaChamado = usuario.nome;
		}
		self.buscaDinamicaSetor = function(setor){
			$scope.buscaChamado = null;
			$scope.buscaChamado = setor.nome;
		}
		self.buscaDinamicaStatus = function(status){
			$scope.buscaChamado = null;
			$scope.buscaChamado = status;
		}
		self.buscaDinamicaTitulo = function(titulo){
			$scope.buscaChamado = null;
			$scope.buscaChamado = titulo;
		}
		self.buscaDinamicaData = function(data){
			$scope.buscaChamado = null;
			$scope.buscaChamado = data;
		}
		
		//===========================RELATORIO==============================================
		
		 $scope.ativaTabela = false;
	     $scope.ativaGrafico = false;
	     
	     self.ativaBotaoTabelaGrafico =  function(botao){
	    	 if(botao === false){
	    		 $scope.ativaTabela = true;
	    		 $scope.ativaGrafico = false;
	    	 }else if(botao === true){
	    		 $scope.ativaGrafico = true;
	    		 $scope.ativaTabela = false;
	    	 }
	     };
	     
	     self.relatorioChamadoSuporte = function(pages, maxResults){
	    	 
	    	 	self.totalPages = [];
				self.getPage=pages;
				chamadoManutencaoService.relatorioChamadoSuporte(pages, maxResults).
				then(function(f){
					$scope.ativaTabela = true;
					$scope.relatorioChamadoSuporte = f.content;
					$scope.totalPages = f.totalPages;
					self.totalElements = f.totalElements;
					for(i = 0; i < $scope.totalPages ; i++){
						self.totalPages.push(i);
					}
					}, function(errResponse){
				});
	     };
	     
	     self.relatorioPorData = function(dataInicial , dataFinal){
	    	 chamadoManutencaoService.relatorioPorData(dataInicial, dataFinal).
				then(function(f){
					$scope.ativaTabela = true;
					$scope.relatorioChamadoSuporte = f;					
					}, function(errResponse){
				});
	     };
	     
	     $scope.labels = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho"];
	     $scope.series = ['Series A', 'Series B'];
	     $scope.data = [
	       [65, 59, 80, 81, 56, 55, 40],
	       [28, 48, 40, 19, 86, 27, 90]
	     ];
	     $scope.onClick = function (points, evt) {
	       console.log(points, evt);
	     };
	     
	     // Simulate async data update 
	     $timeout(function () {
	       $scope.data = [
	         [28, 48, 40, 19, 86, 27, 90],
	         [65, 59, 80, 81, 56, 55, 40]
	       ];
	     }, 3000);

		
});