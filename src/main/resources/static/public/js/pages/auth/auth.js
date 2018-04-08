app.factory(
		'auth',

		function($rootScope, $http, $location) {

			enter = function() {
				if ($location.path() != auth.loginPath) {
					auth.path = $location.path();
					if (!auth.authenticated) {
						$location.path(auth.loginPath);
					}
				}					
			}

			var auth = {

				authenticated : false,
				
				loginPath : '/login',
				logoutPath : '/logout',
				homePath : '/',
				path : $location.path(),

				authenticate : function(credentials, callback) {

					var headers = credentials && credentials.username ? {
						authorization : "Basic "
								+ btoa(credentials.username + ":"
										+ credentials.password)
					} : {};	

					$http.get('/rest/usuario/usuario', {
						headers : headers
					}).then(function(response) {
						if (response.data.name) {
							$rootScope.userData = response.data;							
							auth.authenticated = true;
							$rootScope.logado = true;
						} else {
							auth.authenticated = false;
						}
						callback && callback(auth.authenticated);
						$location.path(auth.path==auth.loginPath ? auth.homePath : auth.path);
					}, function() {
						auth.authenticated = false;
						callback && callback(false);
					});

				},

				clear : function() {
					$location.path(auth.loginPath);
					auth.authenticated = false;
					$http.post(auth.logoutPath, {}).then(function() {
						console.log("Logout succeeded");
						$rootScope.logado = false;
						$rootScope.user = null;
						limpaPermissão();
					}, function() {
						console.log("Logout failed");
					});
				},

				init : function(homePath, loginPath, logoutPath) {

					auth.homePath = homePath;
					auth.loginPath = loginPath;
					auth.logoutPath = logoutPath;

					auth.authenticate({}, function(authenticated) {
						if (authenticated) {
							$location.path(auth.path);
							$rootScope.logado = true;
						}
					})

					// Guard route changes and switch to login page if unauthenticated
					$rootScope.$on('$routeChangeStart', function() {
						enter();
					});

				}

			};
			limpaPermissão = function(){
				$rootScope.ROLE_MODULO_ADMIN = null;
				$rootScope.ROLE_EMPRESA_CONTRATADA_CADASTRAR= null;
				$rootScope.ROLE_EMPRESA_CONTRATADA_ALTERAR = null;
				$rootScope.ROLE_EMPRESA_CONTRATADA_CONSULTAR = null;
				$rootScope.ROLE_MODULO_CADASTROS = null;
				$rootScope.ROLE_MODULO_CADASTROS_AREA_ALTERAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_AREA_CADASTRAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_AREA_CONSULTAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_FABRICANTE_CADASTRAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_FABRICANTE_ALTERAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_FABRICANTE_CONSULTAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_FORNECEDOR_CADASTRAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_FORNECEDOR_ALTERAR  = null;
				$rootScope.ROLE_MODULO_CADASTROS_FORNECEDOR_CONSULTAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_CADASTRAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_ALTERAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_CONSULTAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_CADASTRAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_ALTERAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_CONSULTAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_TIPO_CADASTRAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_TIPO_ALTERAR = null;
				$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_TIPO_CONSULTAR = null;
				$rootScope.ROLE_MODULO_CHAMADO = null;
				$rootScope.ROLE_MODULO_CHAMADO_INFORMATICA_USUARIO = null;
				$rootScope.ROLE_MODULO_CHAMADO_INFORMATICA_SUPORTE = null;
				$rootScope.ROLE_MODULO_CHAMADO_INFORMATICA_SUPORTE_RELATORIO = null;
				$rootScope.ROLE_MODULO_CHAMADO_MANUTENCAO_USUARIO = null;
				$rootScope.ROLE_MODULO_CHAMADO_MANUTENCAO_SUPORTE = null;
				$rootScope.ROLE_MODULO_CHAMADO_MANUTENCAO_SUPORTE_RELATORIO = null;
				$rootScope.ROLE_MODULO_COMPRAS = null;
				$rootScope.ROLE_MODULO_COMPRAS_CADASTRAR = null;
				$rootScope.ROLE_MODULO_COMPRAS_ALTERAR = null;
				$rootScope.ROLE_MODULO_COMPRAS_CONSULTAR = null;
				$rootScope.ROLE_MODULO_COMPRAS_COTACAO_GERAR = null;
				$rootScope.ROLE_MODULO_COMPRAS_COTACAO_CADASTRAR = null;
				$rootScope.ROLE_MODULO_COMPRAS_COTACAO_ABERTO = null;
				$rootScope.ROLE_MODULO_COMPRAS_COTACAO_VENCEDORES_CONSULTAR = null;
				$rootScope.ROLE_MODULO_COMPRAS_COTACAO_CONCORRENTES_CONSULTAR = null;
				$rootScope.ROLE_MODULO_ESTOQUE = null;
				$rootScope.ROLE_MODULO_ESTOQUE_ENTRADA = null;
				$rootScope.ROLE_MODULO_ESTOQUE_CONSULTA = null;
				$rootScope.ROLE_MODULO_ESTOQUE_ALTERA_LIMITE = null;
				$rootScope.ROLE_MODULO_ESTOQUE_REQUISICAO_CADASTRAR = null;
				$rootScope.ROLE_MODULO_ESTOQUE_REQUISICAO_CONSULTA = null;
				$rootScope.ROLE_MODULO_REQUISICAO_CONSULTA_CASA = null;
				$rootScope.ROLE_MODULO_REQUISICAO_CONSULTA_EDIFICIO = null;
				$rootScope.ROLE_MODULO_REQUISICAO_CONSULTA_OUTROS = null;
				$rootScope.ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CADASTRAR = null;
				$rootScope.ROLE_MODULO_ESTOQUE_TRANSFERENCIA_ENVIADAS = null;
				$rootScope.ROLE_MODULO_ESTOQUE_TRANSFERENCIA_RECEBIDAS = null;
				$rootScope.ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CONSULTA_ENVIADAS = null;
				$rootScope.ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CONSULTA_RECEBIDAS = null;
				$rootScope.ROLE_MODULO_GERENCIAMENTO = null;
				$rootScope.ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_CADASTRAR = null;
				$rootScope.ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_CONSULTAR = null;
				$rootScope.ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_ALTERAR = null;
				$rootScope.ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_INFORMACOES = null;
				$rootScope.ROLE_EMPREENDIMENTO_CONFIGURACAO_CADASTRAR = null;
				$rootScope.ROLE_EMPREENDIMENTO_CONFIGURACAO_CONSULTAR = null;
				$rootScope.ROLE_EMPREENDIMENTO_CONFIGURACAO_ALTERAR = null;
				$rootScope.ROLE_USUARIO_CADASTRAR = null;
				$rootScope.ROLE_USUARIO_EMPREENDIMENTO_CONSULTAR = null;
				$rootScope.ROLE_USUARIO_EMPREENDIMENTO_ALTERAR = null;
				$rootScope.ROLE_USUARIO_EMPREENDIMENTO_PERMISSSAO = null;
				$rootScope.ROLE_MODULO_RECURSOS_HUMANOS = null;
				$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CADASTRAR = null;
				$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_CARGO_ALTERAR = null;
				$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CONSULTAR = null;
				$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_CADASTRAR = null;
				$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_ALTERAR = null;
				$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_CONSULTAR = null;
				$rootScope.ROLE_MODULO_SERVICOS = null;
				$rootScope.ROLE_PACOTES_CADASTRAR = null;
				$rootScope.ROLE_PACOTES_ALTERAR = null;
				$rootScope.ROLE_PACOTES_CONSULTAR = null;
				$rootScope.ROLE_PRESTADORA_SERVICOS_CADASTRAR= null;
				$rootScope.ROLE_PRESTADORA_SERVICOS_ALTERAR = null;
				$rootScope.ROLE_PRESTADORA_SERVICOS_CONSULTAR = null;
				$rootScope.ROLE_VINCULAR_CADASTRAR = null;
				$rootScope.ROLE_VINCULAR_CONSULTAR = null;
				$rootScope.ROLE_VINCULAR_CONSULTAR_CASA = null;
				$rootScope.ROLE_VINCULAR_CONSULTAR_EDIFICIO = null;
				$rootScope.ROLE_VINCULAR_CONSULTAR_EDIFICACOES_COMUNITARIA = null;
				$rootScope.ROLE_MODULO_CHAMADO_INFORMATICA_USUARIO = null;
				$rootScope.ROLE_MODULO_CHAMADO = null;
				console.log("limpou tudo");
			}

			return auth;

		});
