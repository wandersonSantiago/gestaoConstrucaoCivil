var app = angular.module('gcc',['ngAnimate','ngRoute','ngMaterial', 'angucomplete-alt','ngResource','toastr','ui.bootstrap','blockUI', 'ngStorage','ui.utils.masks', 'ui.mask'])
.run(function ($rootScope, $location, usuarioService, auth) {
	 
	var self = this;
	  $rootScope.$on('$locationChangeStart', function () { 
	  role = function(){					
			for(i = 0 ; i < $rootScope.userData.authorities.length ; i++ ){								
				var	perfil = $rootScope.userData.authorities[i].authority;
				
				//MODULO ADMIN SISTEMA
						ROLE_MODULO_ADMIN(perfil); ROLE_EMPRESA_CONTRATADA_CADASTRAR(perfil); ROLE_EMPRESA_CONTRATADA_ALTERAR(perfil);	ROLE_EMPRESA_CONTRATADA_CONSULTAR(perfil);
				//MODULO DE CADASTROS		
						ROLE_MODULO_CADASTROS(perfil); ROLE_MODULO_CADASTROS_AREA_CADASTRAR(perfil); ROLE_MODULO_CADASTROS_AREA_ALTERAR(perfil);	ROLE_MODULO_CADASTROS_AREA_CONSULTAR(perfil);	
						ROLE_MODULO_CADASTROS_FABRICANTE_CADASTRAR(perfil); ROLE_MODULO_CADASTROS_FABRICANTE_ALTERAR(perfil);	ROLE_MODULO_CADASTROS_FABRICANTE_CONSULTAR(perfil);	
						ROLE_MODULO_CADASTROS_FORNECEDOR_CADASTRAR(perfil); ROLE_MODULO_CADASTROS_FORNECEDOR_ALTERAR(perfil);	ROLE_MODULO_CADASTROS_FORNECEDOR_CONSULTAR(perfil);	
						ROLE_MODULO_CADASTROS_PRODUTO_CADASTRAR(perfil); ROLE_MODULO_CADASTROS_PRODUTO_ALTERAR(perfil);	ROLE_MODULO_CADASTROS_PRODUTO_CONSULTAR(perfil);	
						ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_CADASTRAR(perfil); ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_ALTERAR(perfil);	ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_CONSULTAR(perfil);	
						ROLE_MODULO_CADASTROS_PRODUTO_TIPO_CADASTRAR(perfil); ROLE_MODULO_CADASTROS_PRODUTO_TIPO_ALTERAR(perfil);	ROLE_MODULO_CADASTROS_PRODUTO_TIPO_CONSULTAR(perfil);
				//MODULO DE CHAMADOS		
						ROLE_MODULO_CHAMADO(perfil); ROLE_MODULO_CHAMADO_INFORMATICA_USUARIO(perfil); ROLE_MODULO_CHAMADO_INFORMATICA_SUPORTE(perfil);	ROLE_MODULO_CHAMADO_INFORMATICA_SUPORTE_RELATORIO(perfil);	
						ROLE_MODULO_CHAMADO_MANUTENCAO_USUARIO(perfil); ROLE_MODULO_CHAMADO_MANUTENCAO_SUPORTE(perfil); ROLE_MODULO_CHAMADO_INFORMATICA_SUPORTE(perfil);	ROLE_MODULO_CHAMADO_MANUTENCAO_SUPORTE_RELATORIO(perfil);
				//MODULO COMPRAS	
						ROLE_MODULO_COMPRAS(perfil); ROLE_MODULO_COMPRAS_CADASTRAR(perfil); ROLE_MODULO_COMPRAS_ALTERAR(perfil);	ROLE_MODULO_COMPRAS_CONSULTAR(perfil);
						ROLE_MODULO_COMPRAS_COTACAO_GERAR(perfil); ROLE_MODULO_COMPRAS_COTACAO_CADASTRAR(perfil); ROLE_MODULO_COMPRAS_COTACAO_ABERTO(perfil);	ROLE_MODULO_COMPRAS_COTACAO_VENCEDORES_CONSULTAR(perfil); ROLE_MODULO_COMPRAS_COTACAO_CONCORRENTES_CONSULTAR(perfil);
				// MODULO ESTOQUE	
						ROLE_MODULO_ESTOQUE_ENTRADA(perfil); ROLE_MODULO_ESTOQUE_CONSULTA(perfil);	ROLE_MODULO_ESTOQUE(perfil); ROLE_MODULO_ESTOQUE_ALTERA_LIMITE(perfil);
						ROLE_MODULO_ESTOQUE_REQUISICAO_CADASTRAR(perfil); ROLE_MODULO_ESTOQUE_REQUISICAO_CONSULTA(perfil); ROLE_MODULO_REQUISICAO_CONSULTA_CASA(perfil); ROLE_MODULO_REQUISICAO_CONSULTA_EDIFICIO(perfil); ROLE_MODULO_REQUISICAO_CONSULTA_OUTROS(perfil);
						ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CADASTRAR(perfil); ROLE_MODULO_ESTOQUE_TRANSFERENCIA_ENVIADAS(perfil); ROLE_MODULO_ESTOQUE_TRANSFERENCIA_RECEBIDAS(perfil); ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CONSULTA_ENVIADAS(perfil); ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CONSULTA_RECEBIDAS(perfil);
				//MODULO GERENCIAMENTO
						ROLE_MODULO_GERENCIAMENTO(perfil); ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_CADASTRAR(perfil); ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_ALTERAR(perfil); ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_INFORMACOES(perfil); ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_CONSULTAR(perfil);
						ROLE_EMPREENDIMENTO_CONFIGURACAO_CADASTRAR(perfil); ROLE_EMPREENDIMENTO_CONFIGURACAO_CONSULTAR(perfil); ROLE_EMPREENDIMENTO_CONFIGURACAO_ALTERAR(perfil); 
						ROLE_USUARIO_CADASTRAR(perfil); ROLE_USUARIO_EMPREENDIMENTO_CONSULTAR(perfil); ROLE_USUARIO_EMPREENDIMENTO_ALTERAR(perfil); ROLE_USUARIO_EMPREENDIMENTO_PERMISSSAO(perfil);
				//MODULO RECURSOS HUMANOS		
						ROLE_MODULO_RECURSOS_HUMANOS(perfil); ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CADASTRAR(perfil); ROLE_MODULO_RECURSOS_HUMANOS_CARGO_ALTERAR(perfil); ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CONSULTAR(perfil);
						ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_CADASTRAR(perfil); ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_ALTERAR(perfil); ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_CONSULTAR(perfil); 
				//MODULO SERVICOS			
						ROLE_MODULO_SERVICOS(perfil); ROLE_PACOTES_CADASTRAR(perfil); ROLE_PACOTES_ALTERAR(perfil); ROLE_PACOTES_CONSULTAR(perfil);
						ROLE_PRESTADORA_SERVICOS_CADASTRAR(perfil); ROLE_PRESTADORA_SERVICOS_ALTERAR(perfil); ROLE_PRESTADORA_SERVICOS_CONSULTAR(perfil); 
						ROLE_VINCULAR_CADASTRAR(perfil); ROLE_VINCULAR_CONSULTAR(perfil); ROLE_VINCULAR_CONSULTAR_CASA(perfil); ROLE_VINCULAR_CONSULTAR_EDIFICIO(perfil); ROLE_VINCULAR_CONSULTAR_EDIFICACOES_COMUNITARIA(perfil);
			
			}					
			
				};
							
				
				//MODULO ADMIN SISTEMA				
				ROLE_MODULO_ADMIN = function(perfil){if(perfil == "ROLE_MODULO_ADMIN" ){$rootScope.ROLE_MODULO_ADMIN = true;}}				
				ROLE_EMPRESA_CONTRATADA_CADASTRAR = function(perfil){if(perfil == "ROLE_EMPRESA_CONTRATADA_CADASTRAR" ){$rootScope.ROLE_EMPRESA_CONTRATADA_CADASTRAR = true;}}
				ROLE_EMPRESA_CONTRATADA_ALTERAR = function(perfil){	if(perfil == "ROLE_EMPRESA_CONTRATADA_ALTERAR" ){$rootScope.ROLE_EMPRESA_CONTRATADA_ALTERAR = true;	}}
				ROLE_EMPRESA_CONTRATADA_CONSULTAR = function(perfil){if(perfil == "ROLE_EMPRESA_CONTRATADA_CONSULTAR" ){$rootScope.ROLE_EMPRESA_CONTRATADA_CONSULTAR = true;}}
				
				//MODULO DE CADASTROS
				ROLE_MODULO_CADASTROS = function(perfil){if(perfil == "ROLE_MODULO_CADASTROS" ){$rootScope.ROLE_MODULO_CADASTROS = true;}}				
				ROLE_MODULO_CADASTROS_AREA_ALTERAR = function(perfil){if(perfil == "ROLE_MODULO_CADASTROS_AREA_ALTERAR" ){$rootScope.ROLE_MODULO_CADASTROS_AREA_ALTERAR = true;}}
				ROLE_MODULO_CADASTROS_AREA_CADASTRAR = function(perfil){	if(perfil == "ROLE_MODULO_CADASTROS_AREA_CADASTRAR" ){$rootScope.ROLE_MODULO_CADASTROS_AREA_CADASTRAR = true;	}}
				ROLE_MODULO_CADASTROS_AREA_CONSULTAR = function(perfil){if(perfil == "ROLE_MODULO_CADASTROS_AREA_CONSULTAR" ){$rootScope.ROLE_MODULO_CADASTROS_AREA_CONSULTAR = true;}}			
							
				ROLE_MODULO_CADASTROS_FABRICANTE_CADASTRAR = function(perfil){  if(perfil == "ROLE_MODULO_CADASTROS_FABRICANTE_CADASTRAR" ){$rootScope.ROLE_MODULO_CADASTROS_FABRICANTE_CADASTRAR = true; }}
				ROLE_MODULO_CADASTROS_FABRICANTE_ALTERAR = function(perfil){	if(perfil == "ROLE_MODULO_CADASTROS_FABRICANTE_ALTERAR" ){$rootScope.ROLE_MODULO_CADASTROS_FABRICANTE_ALTERAR = true;}}
				ROLE_MODULO_CADASTROS_FABRICANTE_CONSULTAR = function(perfil){if(perfil == "ROLE_MODULO_CADASTROS_FABRICANTE_CONSULTAR" ){$rootScope.ROLE_MODULO_CADASTROS_FABRICANTE_CONSULTAR = true;}}	
				
				ROLE_MODULO_CADASTROS_FORNECEDOR_CADASTRAR = function(perfil){ if(perfil == "ROLE_MODULO_CADASTROS_FORNECEDOR_CADASTRAR" ){$rootScope.ROLE_MODULO_CADASTROS_FORNECEDOR_CADASTRAR = true;}}
				ROLE_MODULO_CADASTROS_FORNECEDOR_ALTERAR = function(perfil){	if(perfil == "ROLE_MODULO_CADASTROS_FORNECEDOR_ALTERAR" ){$rootScope.ROLE_MODULO_CADASTROS_FORNECEDOR_ALTERAR = true;}}
				ROLE_MODULO_CADASTROS_FORNECEDOR_CONSULTAR = function(perfil){if(perfil == "ROLE_MODULO_CADASTROS_FORNECEDOR_CONSULTAR" ){$rootScope.ROLE_MODULO_CADASTROS_FORNECEDOR_CONSULTAR = true;}}	
				
				ROLE_MODULO_CADASTROS_PRODUTO_CADASTRAR = function(perfil){ if(perfil == "ROLE_MODULO_CADASTROS_PRODUTO_CADASTRAR" ){$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_CADASTRAR = true;}}
				ROLE_MODULO_CADASTROS_PRODUTO_ALTERAR = function(perfil){	if(perfil == "ROLE_MODULO_CADASTROS_PRODUTO_ALTERAR" ){$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_ALTERAR = true;}}
				ROLE_MODULO_CADASTROS_PRODUTO_CONSULTAR = function(perfil){if(perfil == "ROLE_MODULO_CADASTROS_PRODUTO_CONSULTAR" ){$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_CONSULTAR = true;}}	
				
				ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_CADASTRAR = function(perfil){ if(perfil == "ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_CADASTRAR" ){$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_CADASTRAR = true;}}
				ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_ALTERAR = function(perfil){	if(perfil == "ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_ALTERAR" ){$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_ALTERAR = true;}}
				ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_CONSULTAR = function(perfil){if(perfil == "ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_CONSULTAR" ){$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_CONSULTAR = true;}}	
				
				ROLE_MODULO_CADASTROS_PRODUTO_TIPO_CADASTRAR = function(perfil){ if(perfil == "ROLE_MODULO_CADASTROS_PRODUTO_TIPO_CADASTRAR" ){$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_TIPO_CADASTRAR = true;}}
				ROLE_MODULO_CADASTROS_PRODUTO_TIPO_ALTERAR = function(perfil){	if(perfil == "ROLE_MODULO_CADASTROS_PRODUTO_TIPO_ALTERAR" ){$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_TIPO_ALTERAR = true;}}
				ROLE_MODULO_CADASTROS_PRODUTO_TIPO_CONSULTAR = function(perfil){if(perfil == "ROLE_MODULO_CADASTROS_PRODUTO_TIPO_CONSULTAR" ){$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_TIPO_CONSULTAR = true;}}	
				
				//MODULO DE CHAMADOS				
				ROLE_MODULO_CHAMADO = function(perfil){if(perfil == "ROLE_MODULO_CHAMADO" ){$rootScope.ROLE_MODULO_CHAMADO = true;}}				
				ROLE_MODULO_CHAMADO_INFORMATICA_USUARIO = function(perfil){if(perfil == "ROLE_MODULO_CHAMADO_INFORMATICA_USUARIO" ){$rootScope.ROLE_MODULO_CHAMADO_INFORMATICA_USUARIO = true;}}
				ROLE_MODULO_CHAMADO_INFORMATICA_SUPORTE = function(perfil){	if(perfil == "ROLE_MODULO_CHAMADO_INFORMATICA_SUPORTE" ){$rootScope.ROLE_MODULO_CHAMADO_INFORMATICA_SUPORTE = true;	}}
				ROLE_MODULO_CHAMADO_INFORMATICA_SUPORTE_RELATORIO = function(perfil){if(perfil == "ROLE_MODULO_CHAMADO_INFORMATICA_SUPORTE_RELATORIO" ){$rootScope.ROLE_MODULO_CHAMADO_INFORMATICA_SUPORTE_RELATORIO = true;}}
				
				ROLE_MODULO_CHAMADO_MANUTENCAO_USUARIO = function(perfil){if(perfil == "ROLE_MODULO_CHAMADO_MANUTENCAO_USUARIO" ){$rootScope.ROLE_MODULO_CHAMADO_MANUTENCAO_USUARIO = true;}}
				ROLE_MODULO_CHAMADO_MANUTENCAO_SUPORTE = function(perfil){	if(perfil == "ROLE_MODULO_CHAMADO_MANUTENCAO_SUPORTE" ){$rootScope.ROLE_MODULO_CHAMADO_MANUTENCAO_SUPORTE = true;	}}
				ROLE_MODULO_CHAMADO_MANUTENCAO_SUPORTE_RELATORIO = function(perfil){if(perfil == "ROLE_MODULO_CHAMADO_MANUTENCAO_SUPORTE_RELATORIO" ){$rootScope.ROLE_MODULO_CHAMADO_MANUTENCAO_SUPORTE_RELATORIO = true;}}
				
				//MODULO COMPRAS
				ROLE_MODULO_COMPRAS = function(perfil){if(perfil == "ROLE_MODULO_COMPRAS" ){$rootScope.ROLE_MODULO_COMPRAS = true;}}				
				ROLE_MODULO_COMPRAS_CADASTRAR = function(perfil){if(perfil == "ROLE_MODULO_COMPRAS_CADASTRAR" ){$rootScope.ROLE_MODULO_COMPRAS_CADASTRAR = true;}}
				ROLE_MODULO_COMPRAS_ALTERAR = function(perfil){	if(perfil == "ROLE_MODULO_COMPRAS_ALTERAR" ){$rootScope.ROLE_MODULO_COMPRAS_ALTERAR = true;	}}
				ROLE_MODULO_COMPRAS_CONSULTAR = function(perfil){if(perfil == "ROLE_MODULO_COMPRAS_CONSULTAR" ){$rootScope.ROLE_MODULO_COMPRAS_CONSULTAR = true;}}
				
				ROLE_MODULO_COMPRAS_COTACAO_GERAR = function(perfil){if(perfil == "ROLE_MODULO_COMPRAS_COTACAO_GERAR" ){$rootScope.ROLE_MODULO_COMPRAS_COTACAO_GERAR = true;}}				
				ROLE_MODULO_COMPRAS_COTACAO_CADASTRAR = function(perfil){if(perfil == "ROLE_MODULO_COMPRAS_COTACAO_CADASTRAR" ){$rootScope.ROLE_MODULO_COMPRAS_COTACAO_CADASTRAR = true;}}
				ROLE_MODULO_COMPRAS_COTACAO_ABERTO = function(perfil){	if(perfil == "ROLE_MODULO_COMPRAS_COTACAO_ABERTO" ){$rootScope.ROLE_MODULO_COMPRAS_COTACAO_ABERTO = true;	}}
				ROLE_MODULO_COMPRAS_COTACAO_VENCEDORES_CONSULTAR = function(perfil){if(perfil == "ROLE_MODULO_COMPRAS_COTACAO_VENCEDORES_CONSULTAR" ){$rootScope.ROLE_MODULO_COMPRAS_COTACAO_VENCEDORES_CONSULTAR = true;}}
				ROLE_MODULO_COMPRAS_COTACAO_CONCORRENTES_CONSULTAR = function(perfil){if(perfil == "ROLE_MODULO_COMPRAS_COTACAO_CONCORRENTES_CONSULTAR" ){$rootScope.ROLE_MODULO_COMPRAS_COTACAO_CONCORRENTES_CONSULTAR = true;}}
				
				// MODULO ESTOQUE
				ROLE_MODULO_ESTOQUE = function(perfil){if(perfil == "ROLE_MODULO_ESTOQUE" ){$rootScope.ROLE_MODULO_ESTOQUE = true;}}				
				ROLE_MODULO_ESTOQUE_ENTRADA = function(perfil){if(perfil == "ROLE_MODULO_ESTOQUE_ENTRADA" ){$rootScope.ROLE_MODULO_ESTOQUE_ENTRADA = true;}}
				ROLE_MODULO_ESTOQUE_CONSULTA = function(perfil){	if(perfil == "ROLE_MODULO_ESTOQUE_CONSULTA" ){$rootScope.ROLE_MODULO_ESTOQUE_CONSULTA = true;	}}
				ROLE_MODULO_ESTOQUE_ALTERA_LIMITE = function(perfil){	if(perfil == "ROLE_MODULO_ESTOQUE_ALTERA_LIMITE" ){$rootScope.ROLE_MODULO_ESTOQUE_ALTERA_LIMITE = true;	}}
				
				ROLE_MODULO_ESTOQUE_REQUISICAO_CADASTRAR = function(perfil){if(perfil == "ROLE_MODULO_ESTOQUE_REQUISICAO_CADASTRAR" ){$rootScope.ROLE_MODULO_ESTOQUE_REQUISICAO_CADASTRAR = true;}}				
				ROLE_MODULO_ESTOQUE_REQUISICAO_CONSULTA = function(perfil){if(perfil == "ROLE_MODULO_ESTOQUE_REQUISICAO_CONSULTA" ){$rootScope.ROLE_MODULO_ESTOQUE_REQUISICAO_CONSULTA = true;}}
				ROLE_MODULO_REQUISICAO_CONSULTA_CASA = function(perfil){if(perfil == "ROLE_MODULO_REQUISICAO_CONSULTA_CASA" ){$rootScope.ROLE_MODULO_REQUISICAO_CONSULTA_CASA = true;}}				
				ROLE_MODULO_REQUISICAO_CONSULTA_EDIFICIO = function(perfil){ if(perfil == "ROLE_MODULO_REQUISICAO_CONSULTA_EDIFICIO" ){$rootScope.ROLE_MODULO_REQUISICAO_CONSULTA_EDIFICIO = true;}}
				ROLE_MODULO_REQUISICAO_CONSULTA_OUTROS = function(perfil){if(perfil == "ROLE_MODULO_REQUISICAO_CONSULTA_OUTROS" ){$rootScope.ROLE_MODULO_REQUISICAO_CONSULTA_OUTROS = true;}}
				
				ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CADASTRAR = function(perfil){if(perfil == "ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CADASTRAR" ){$rootScope.ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CADASTRAR = true;}}				
				ROLE_MODULO_ESTOQUE_TRANSFERENCIA_ENVIADAS = function(perfil){if(perfil == "ROLE_MODULO_ESTOQUE_TRANSFERENCIA_ENVIADAS" ){$rootScope.ROLE_MODULO_ESTOQUE_TRANSFERENCIA_ENVIADAS = true;}}
				ROLE_MODULO_ESTOQUE_TRANSFERENCIA_RECEBIDAS = function(perfil){if(perfil == "ROLE_MODULO_ESTOQUE_TRANSFERENCIA_RECEBIDAS" ){ $rootScope.ROLE_MODULO_ESTOQUE_TRANSFERENCIA_RECEBIDAS = true;}}				
				ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CONSULTA_ENVIADAS = function(perfil){ if(perfil == "ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CONSULTA_ENVIADAS" ){$rootScope.ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CONSULTA_ENVIADAS = true;}}
				ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CONSULTA_RECEBIDAS = function(perfil){if(perfil == "ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CONSULTA_RECEBIDAS" ){$rootScope.ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CONSULTA_RECEBIDAS = true;}}
				
				//MODULO GERENCIAMENTO
				ROLE_MODULO_GERENCIAMENTO = function(perfil){if(perfil == "ROLE_MODULO_GERENCIAMENTO" ){$rootScope.ROLE_MODULO_GERENCIAMENTO = true;}}				
				ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_CADASTRAR = function(perfil){if(perfil == "ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_CADASTRAR" ){$rootScope.ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_CADASTRAR = true;}}
				ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_CONSULTAR = function(perfil){if(perfil == "ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_CONSULTAR" ){ $rootScope.ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_CONSULTAR = true;}}				
				ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_ALTERAR = function(perfil){ if(perfil == "ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_ALTERAR" ){$rootScope.ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_ALTERAR = true;}}
				ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_INFORMACOES = function(perfil){if(perfil == "ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_INFORMACOES" ){$rootScope.ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_INFORMACOES = true;}}
				
				ROLE_EMPREENDIMENTO_CONFIGURACAO_CADASTRAR = function(perfil){if(perfil == "ROLE_EMPREENDIMENTO_CONFIGURACAO_CADASTRAR" ){ $rootScope.ROLE_EMPREENDIMENTO_CONFIGURACAO_CADASTRAR = true;}}				
				ROLE_EMPREENDIMENTO_CONFIGURACAO_CONSULTAR = function(perfil){ if(perfil == "ROLE_EMPREENDIMENTO_CONFIGURACAO_CONSULTAR" ){$rootScope.ROLE_EMPREENDIMENTO_CONFIGURACAO_CONSULTAR = true;}}
				ROLE_EMPREENDIMENTO_CONFIGURACAO_ALTERAR = function(perfil){if(perfil == "ROLE_EMPREENDIMENTO_CONFIGURACAO_ALTERAR" ){$rootScope.ROLE_EMPREENDIMENTO_CONFIGURACAO_ALTERAR = true;}}
				
				ROLE_USUARIO_CADASTRAR = function(perfil){if(perfil == "ROLE_USUARIO_CADASTRAR" ){$rootScope.ROLE_USUARIO_CADASTRAR = true;}}
				ROLE_USUARIO_EMPREENDIMENTO_CONSULTAR = function(perfil){if(perfil == "ROLE_USUARIO_EMPREENDIMENTO_CONSULTAR" ){ $rootScope.ROLE_USUARIO_EMPREENDIMENTO_CONSULTAR = true;}}				
				ROLE_USUARIO_EMPREENDIMENTO_ALTERAR = function(perfil){ if(perfil == "ROLE_USUARIO_EMPREENDIMENTO_ALTERAR" ){$rootScope.ROLE_USUARIO_EMPREENDIMENTO_ALTERAR = true;}}
				ROLE_USUARIO_EMPREENDIMENTO_PERMISSSAO = function(perfil){if(perfil == "ROLE_USUARIO_EMPREENDIMENTO_PERMISSSAO" ){$rootScope.ROLE_USUARIO_EMPREENDIMENTO_PERMISSSAO = true;}}
				
				//MODULO RECURSOS HUMANOS
				ROLE_MODULO_RECURSOS_HUMANOS = function(perfil){if(perfil == "ROLE_MODULO_RECURSOS_HUMANOS" ){$rootScope.ROLE_MODULO_RECURSOS_HUMANOS = true;}}
				ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CADASTRAR = function(perfil){if(perfil == "ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CADASTRAR" ){ $rootScope.ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CADASTRAR = true;}}				
				ROLE_MODULO_RECURSOS_HUMANOS_CARGO_ALTERAR = function(perfil){ if(perfil == "ROLE_MODULO_RECURSOS_HUMANOS_CARGO_ALTERAR" ){$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_CARGO_ALTERAR = true;}}
				ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CONSULTAR = function(perfil){if(perfil == "ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CONSULTAR" ){$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CONSULTAR = true;}}
				
				ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_CADASTRAR = function(perfil){if(perfil == "ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_CADASTRAR" ){ $rootScope.ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_CADASTRAR = true;}}				
				ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_ALTERAR = function(perfil){ if(perfil == "ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_ALTERAR" ){$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_ALTERAR = true;}}
				ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_CONSULTAR = function(perfil){if(perfil == "ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_CONSULTAR" ){$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_CONSULTAR = true;}}
				
				//MODULO SERVICOS
				ROLE_MODULO_SERVICOS = function(perfil){if(perfil == "ROLE_MODULO_SERVICOS" ){$rootScope.ROLE_MODULO_SERVICOS = true;}}
				ROLE_PACOTES_CADASTRAR = function(perfil){if(perfil == "ROLE_PACOTES_CADASTRAR" ){ $rootScope.ROLE_PACOTES_CADASTRAR = true;}}				
				ROLE_PACOTES_ALTERAR = function(perfil){ if(perfil == "ROLE_PACOTES_ALTERAR" ){$rootScope.ROLE_PACOTES_ALTERAR = true;}}
				ROLE_PACOTES_CONSULTAR = function(perfil){if(perfil == "ROLE_PACOTES_CONSULTAR" ){$rootScope.ROLE_PACOTES_CONSULTAR = true;}}
				
				ROLE_PRESTADORA_SERVICOS_CADASTRAR = function(perfil){if(perfil == "ROLE_PRESTADORA_SERVICOS_CADASTRAR" ){ $rootScope.ROLE_PRESTADORA_SERVICOS_CADASTRAR = true;}}				
				ROLE_PRESTADORA_SERVICOS_ALTERAR = function(perfil){ if(perfil == "ROLE_PRESTADORA_SERVICOS_ALTERAR" ){$rootScope.ROLE_PRESTADORA_SERVICOS_ALTERAR = true;}}
				ROLE_PRESTADORA_SERVICOS_CONSULTAR = function(perfil){if(perfil == "ROLE_PRESTADORA_SERVICOS_CONSULTAR" ){$rootScope.ROLE_PRESTADORA_SERVICOS_CONSULTAR = true;}}
				
				ROLE_VINCULAR_CADASTRAR = function(perfil){if(perfil == "ROLE_VINCULAR_CADASTRAR" ){$rootScope.ROLE_VINCULAR_CADASTRAR = true;}}
				ROLE_VINCULAR_CONSULTAR = function(perfil){if(perfil == "ROLE_VINCULAR_CONSULTAR" ){ $rootScope.ROLE_VINCULAR_CONSULTAR = true;}}				
				ROLE_VINCULAR_CONSULTAR_CASA = function(perfil){ if(perfil == "ROLE_VINCULAR_CONSULTAR_CASA" ){$rootScope.ROLE_VINCULAR_CONSULTAR_CASA = true;}}
				ROLE_VINCULAR_CONSULTAR_EDIFICIO = function(perfil){if(perfil == "ROLE_VINCULAR_CONSULTAR_EDIFICIO" ){$rootScope.ROLE_VINCULAR_CONSULTAR_EDIFICIO = true;}}
				ROLE_VINCULAR_CONSULTAR_EDIFICACOES_COMUNITARIA = function(perfil){ if(perfil == "ROLE_VINCULAR_CONSULTAR_EDIFICACOES_COMUNITARIA" ){$rootScope.ROLE_VINCULAR_CONSULTAR_EDIFICACOES_COMUNITARIA = true;}}
				
				
				 role();
				 
				//bloqueador de rotas
				//MODULO ADMIN SISTEMA
				 var paginaEmpresaContratadaCadastrar = "/empresa_contratada/cadastro", paginaEmpresaContratadaConsultar = "/empresa_contratada/listagem", paginaEmpresaContratadaAlterar = "/empresa_contratada/alteracao/:idEmpresa";							
					if(!$rootScope.ROLE_EMPRESA_CONTRATADA_CADASTRAR && $location.path() == paginaEmpresaContratadaCadastrar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_EMPRESA_CONTRATADA_CONSULTAR && $location.path() == paginaEmpresaContratadaConsultar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_EMPRESA_CONTRATADA_ALTERAR && $location.path() == paginaEmpresaContratadaAlterar ){	$location.path("/semAcesso");}
				
				//MODULO DE CADASTROS
			 	 var paginaAreaCadastrar = "/area/cadastro", paginaAreaConsultar = "/area/lista", paginaAreaAlterar = "/area/edita/:idAreaProduto";							
					if(!$rootScope.ROLE_MODULO_CADASTROS_AREA_CADASTRAR && $location.path() == paginaAreaCadastrar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_CADASTROS_AREA_CONSULTAR && $location.path() == paginaAreaConsultar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_CADASTROS_AREA_ALTERAR && $location.path() == paginaAreaAlterar ){	$location.path("/semAcesso");} 				 	
			 	 var paginaFabricanteCadastrar = "/fabricante/cadastro", paginaFabricanteConsultar = "/fabricante/lista", paginaFabricanteAlterar = "/fabricante/edita/:idFabricante";							
					if(!$rootScope.ROLE_MODULO_CADASTROS_FABRICANTE_CADASTRAR && $location.path() == paginaFabricanteCadastrar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_CADASTROS_FABRICANTE_CONSULTAR && $location.path() == paginaFabricanteConsultar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_CADASTROS_FABRICANTE_ALTERAR && $location.path() == paginaFabricanteAlterar ){	$location.path("/semAcesso");} 				 	
			 	 var paginaFornecedorCadastrar = "/fornecedor/cadastro", paginaFornecedorConsultar = "/fornecedor/lista", paginaFornecedorAlterar = "/fornecedor/edita/:idFornecedor";							
					if(!$rootScope.ROLE_MODULO_CADASTROS_FORNECEDOR_CADASTRAR && $location.path() == paginaFornecedorCadastrar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_CADASTROS_FORNECEDOR_CONSULTAR && $location.path() == paginaFornecedorConsultar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_CADASTROS_FORNECEDOR_ALTERAR && $location.path() == paginaFornecedorAlterar ){	$location.path("/semAcesso");} 				 	
				 var paginaProdutoCadastrar = "/produto/cadastro", paginaProdutoConsultar = "/produto/lista", paginaProdutoAlterar = "/produto/edita/:idProduto";							
					if(!$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_CADASTRAR && $location.path() == paginaProdutoCadastrar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_CONSULTAR && $location.path() == paginaProdutoConsultar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_ALTERAR && $location.path() == paginaProdutoAlterar ){	$location.path("/semAcesso");} 					 
			 	 var paginaProdutoCategoriaCadastrar = "/produto/categoria/cadastro", paginaProdutoCategoriaConsultar = "/produto/categoria/lista", paginaProdutoCategoriaAlterar = "/produto/categoria/edita/:idCategoria";							
					if(!$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_CADASTRAR && $location.path() == paginaProdutoCategoriaCadastrar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_CONSULTAR && $location.path() == paginaProdutoCategoriaConsultar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_CATEGORIA_ALTERAR && $location.path() == paginaProdutoCategoriaAlterar ){	$location.path("/semAcesso");} 				 
				 var paginaProdutoTipoCadastrar = "/produto/tipo/cadastro", paginaProdutoTipoConsultar = "/produto/tipo/lista", paginaProdutoTipoConsultar = "/produto/categoria/edita/:idTipoProduto";							
					if(!$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_TIPO_CADASTRAR && $location.path() == paginaProdutoTipoCadastrar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_TIPO_CONSULTAR && $location.path() == paginaProdutoTipoConsultar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_CADASTROS_PRODUTO_TIPO_ALTERAR && $location.path() == paginaProdutoTipoConsultar ){	$location.path("/semAcesso");} 	
				  
				 //MODULO DE CHAMADOS
			 	 var paginaChamadoInformaticaCadastrar = "/chamado/informatica/cadastrar", paginaChamadoInformaticaSuporteLista = "/chamado/informatica/suporte/lista", paginaChamadoInformaticaSuporte = "/chamado/informatica/suporte/atendimento/:idChamadoTi", paginaChamadoInformaticaRelatorioSuporte = "/chamado/informatica/suporte/relatorio";							
					if(!$rootScope.ROLE_MODULO_CHAMADO_INFORMATICA_USUARIO && $location.path() == paginaChamadoInformaticaCadastrar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_CHAMADO_INFORMATICA_SUPORTE && $location.path() == paginaChamadoInformaticaSuporte ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_CHAMADO_INFORMATICA_SUPORTE && $location.path() == paginaChamadoInformaticaSuporteLista){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_CHAMADO_INFORMATICA_SUPORTE_RELATORIO && $location.path() == paginaChamadoInformaticaRelatorioSuporte ){	$location.path("/semAcesso");} 					  
			 	 var paginaChamadoManutencaoCadastrar = "/chamado/manutencao/cadastrar", paginaChamadoManutencaoSuporteLista = "/chamado/manutencao/suporte/lista", paginaChamadoManutencaoSuporte = "/chamado/manutencao/suporte/atendimento/:idChamadoTi", paginaChamadoManutencaoRelatorioSuporte = "/chamado/informatica/suporte/relatorio";							
					if(!$rootScope.ROLE_MODULO_CHAMADO_MANUTENCAO_USUARIO && $location.path() == paginaChamadoManutencaoCadastrar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_CHAMADO_MANUTENCAO_SUPORTE && $location.path() == paginaChamadoManutencaoSuporte ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_CHAMADO_MANUTENCAO_SUPORTE && $location.path() == paginaChamadoManutencaoSuporteLista ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_CHAMADO_MANUTENCAO_SUPORTE_RELATORIO && $location.path() == paginaChamadoManutencaoRelatorioSuporte ){	$location.path("/semAcesso");} 		 
				 
				 //MODULO COMPRAS	
			 	 var paginaComprasCadastrar = "/compras/cadastro/", paginaComprasConsultar = "/compras/lista", paginaComprasAlterar = "/compras/visualizar/:idVisualizarCompras";							
					if(!$rootScope.ROLE_MODULO_COMPRAS_CADASTRAR && $location.path() == paginaComprasCadastrar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_COMPRAS_ALTERAR && $location.path() == paginaComprasAlterar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_COMPRAS_CONSULTAR && $location.path() == paginaComprasConsultar ){	$location.path("/semAcesso");} 
			 	 var paginaCotacaoCadastrar = "/cotacao/cadastro/:idCotacaoAberta", paginaCotacaoGerar = "/cotacao/gerar", paginaCotacaoAberta = "/cotacao/em_aberto", paginaCotacaoVencedor = "/cotacao/vencedor", paginaCotacaoConcorrentes = "/cotacao/concorrente";							
					if(!$rootScope.ROLE_MODULO_COMPRAS_COTACAO_GERAR && $location.path() == paginaCotacaoGerar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_COMPRAS_COTACAO_CADASTRAR && $location.path() == paginaCotacaoCadastrar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_COMPRAS_COTACAO_ABERTO && $location.path() == paginaCotacaoAberta ){	$location.path("/semAcesso");} 	 	
				 	if(!$rootScope.ROLE_MODULO_COMPRAS_COTACAO_VENCEDORES_CONSULTAR && $location.path() == paginaCotacaoVencedor ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_COMPRAS_COTACAO_CONCORRENTES_CONSULTAR && $location.path() == paginaCotacaoConcorrentes ){	$location.path("/semAcesso");} 	
				 	
				 // MODULO ESTOQUE
			 	 var  paginaEstoqueEntrada = "/estoque/entrada", paginaEstoqueConsulta = "/estoque/listagem" , paginaEstoqueAlteraLimite = "/estoque/alteracao/:idProdutoEstoque";
				 	if(!$rootScope.ROLE_MODULO_ESTOQUE_ENTRADA && $location.path() == paginaEstoqueEntrada ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_ESTOQUE_CONSULTA && $location.path() == paginaEstoqueConsulta ){	$location.path("/semAcesso");} 
					if(!$rootScope.ROLE_MODULO_ESTOQUE_ALTERA_LIMITE && $location.path() == paginaEstoqueAlteraLimite ){	$location.path("/semAcesso");}
				 	
				 var  paginaEstoqueRequisicaoCadastrar = "/estoque/requisicao/cadastro", paginaEstoqueRequisicaoConsulta = "/estoque/requisicao/listagem", paginaEstoqueRequisicaoCasa = "/estoque/visualizar/casa/:idRequisicaoCasa", paginaEstoqueRequisicaoEdificio = "/estoque/visualizar/edificio/:idRequisicaoEdificio", paginaEstoqueRequisicaoOutros = "/estoque/visualizar/outros/:idRequisicaoOutros";
				 	if(!$rootScope.ROLE_MODULO_ESTOQUE_REQUISICAO_CADASTRAR && $location.path() == paginaEstoqueRequisicaoCadastrar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_ESTOQUE_REQUISICAO_CONSULTA && $location.path() == paginaEstoqueRequisicaoConsulta ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_REQUISICAO_CONSULTA_CASA && $location.path() == paginaEstoqueRequisicaoCasa ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_REQUISICAO_CONSULTA_EDIFICIO && $location.path() == paginaEstoqueRequisicaoEdificio ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_REQUISICAO_CONSULTA_OUTROS && $location.path() == paginaEstoqueRequisicaoOutros ){	$location.path("/semAcesso");}
				 	
			 	 var  paginaEstoqueTransferenciaCadastrar = "/estoque/transferencia/cadastro", paginaEstoqueTransferenciaEnviadas = "/estoque/transferencia/enviadas", paginaEstoqueTransferenciaRecebidas = "/estoque/transferencia/recebidas", paginaEstoqueTransferenciaVizualizarEnviados = "/estoque/transferencia/vizualizarEnviados/:idEnviados", paginaEstoqueTransferenciaVizualizarRecebidas = "/estoque/transferencia/vizualizarRecebidos/:idRecebidos";
				 	if(!$rootScope.ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CADASTRAR && $location.path() == paginaEstoqueTransferenciaCadastrar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_ESTOQUE_TRANSFERENCIA_ENVIADAS && $location.path() == paginaEstoqueTransferenciaEnviadas ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_ESTOQUE_TRANSFERENCIA_RECEBIDAS && $location.path() == paginaEstoqueTransferenciaRecebidas ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CONSULTA_ENVIADAS && $location.path() == paginaEstoqueTransferenciaVizualizarEnviados ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_ESTOQUE_TRANSFERENCIA_CONSULTA_RECEBIDAS && $location.path() == paginaEstoqueTransferenciaVizualizarRecebidas ){	$location.path("/semAcesso");}
				 
				 //MODULO GERENCIAMENTO		
			 	 var  paginaEmpreedimentoCadastrar = "/empreendimento/cadastro", paginaEmpreendimentoAlterar = "/empreendimento/alteracao/:idEmpreendimento", paginaEmpreedimentoConsulta = "/empreendimento/listagem", paginaEmpreendimentoVisualizar = "/empreendimento/visualizacao/:idEmpreendimento";
				 	if(!$rootScope.ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_CADASTRAR && $location.path() == paginaEmpreedimentoCadastrar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_CONSULTAR && $location.path() == paginaEmpreedimentoConsulta ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_ALTERAR && $location.path() == paginaEmpreendimentoAlterar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_GERENCIAMENTO_EMPREENDIMENTO_INFORMACOES && $location.path() == paginaEmpreendimentoVisualizar ){	$location.path("/semAcesso");}
				 	
			 	var  paginaEmpreedimentoConfigCadastrar = "/empreendimento/configuracao/cadastro", paginaEmpreendimentoConfigAlterar = "/empreendimento/configuracao/listagem", paginaEmpreedimentoConfigConsulta = "/empreendimento/configuracao/alteracao/:idConfigEmpreendimento";
				 	if(!$rootScope.ROLE_EMPREENDIMENTO_CONFIGURACAO_CADASTRAR && $location.path() == paginaEmpreedimentoConfigCadastrar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_EMPREENDIMENTO_CONFIGURACAO_CONSULTAR && $location.path() == paginaEmpreendimentoConfigAlterar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_EMPREENDIMENTO_CONFIGURACAO_ALTERAR && $location.path() == paginaEmpreedimentoConfigConsulta ){	$location.path("/semAcesso");}	
				 
				 	
			 	var  paginaUsuarioCadastrar = "/usuario/cadastro", paginaUsuarioAlterar = "/usuario/alteracao/:idUsuario", paginaUsuarioConsulta = "/usuario/listagem", paginaUsuarioPermissao = "/usuario/permissao/editar/:idUsuario";
				 	if(!$rootScope.ROLE_USUARIO_CADASTRAR && $location.path() == paginaUsuarioCadastrar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_USUARIO_EMPREENDIMENTO_CONSULTAR && $location.path() == paginaUsuarioConsulta ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_USUARIO_EMPREENDIMENTO_ALTERAR && $location.path() == paginaUsuarioAlterar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_USUARIO_EMPREENDIMENTO_PERMISSSAO && $location.path() == paginaUsuarioPermissao ){	$location.path("/semAcesso");}
				 
				//MODULO RECURSOS HUMANOS
				var  paginaCargoCadastrar = "/cargo/cadastro", paginaCargoAlterar = "/cargo/edita/:idCargo" , paginaCargoConsultar = "/cargo/lista";
				 	if(!$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CADASTRAR && $location.path() == paginaCargoCadastrar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_CARGO_ALTERAR && $location.path() == paginaCargoAlterar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CONSULTAR && $location.path() == paginaCargoConsultar ){	$location.path("/semAcesso");}	
				 	
			 	var  paginaFuncionarioCadastrar = "/funcionario/cadastro", paginaFuncionarioAlterar = "/funcionario/edita/:idFuncionario", paginaFuncionarioConsultar = "/funcionario/lista";
				 	if(!$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_CADASTRAR && $location.path() == paginaFuncionarioCadastrar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_ALTERAR && $location.path() == paginaFuncionarioAlterar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_MODULO_RECURSOS_HUMANOS_FUNCIONARIO_CONSULTAR && $location.path() == paginaFuncionarioConsultar ){	$location.path("/semAcesso");}	
				 	
				//MODULO SERVICOS 
				var  paginaPacotesCadastrar = "/servicos/pacotes/cadastro", paginaPacotesAlterar = "/servicos/pacotes/edita/:idPacoteServico" , paginaPacotesConsultar = "/servicos/pacotes/lista";
				 	if(!$rootScope.ROLE_PACOTES_CADASTRAR && $location.path() == paginaPacotesCadastrar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_PACOTES_ALTERAR && $location.path() == paginaPacotesAlterar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_PACOTES_CONSULTAR && $location.path() == paginaPacotesConsultar ){	$location.path("/semAcesso");}	
				 	
			 	var  paginaPrestadoraServicoCadastrar = "/servicos/prestadora/cadastro", paginaPrestadoraServicoAlterar = "/servicos/prestadora/alteracao/:idPrestadoraServico", paginaPrestadoraServicoConsultar = "/servicos/prestadora/listagem";
				 	if(!$rootScope.ROLE_PRESTADORA_SERVICOS_CADASTRAR && $location.path() == paginaPrestadoraServicoCadastrar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_PRESTADORA_SERVICOS_ALTERAR && $location.path() == paginaPrestadoraServicoAlterar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_PRESTADORA_SERVICOS_CONSULTAR && $location.path() == paginaPrestadoraServicoConsultar ){	$location.path("/semAcesso");}	
				
				var  paginaVincularCadastrar = "/servicos/vincular/cadastro", paginaVincularConsultar = "/servicos/vincular/listagem", paginaVincularConsultarCasa = "/servicos/vincular/visualizar/casa/:idServicoCasa" , paginaVincularConsultarEdificio = "/servicos/vincular/visualizar/edificio/:idServicoEdificio" , paginaVincularConsultarComunitario = "/servicos/vincular/visualizar/comunitario/:idServicoComunitario";
				 	if(!$rootScope.ROLE_VINCULAR_CADASTRAR && $location.path() == paginaVincularCadastrar ){	$location.path("/semAcesso");}	
				 	if(!$rootScope.ROLE_VINCULAR_CONSULTAR && $location.path() == paginaVincularConsultar ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_VINCULAR_CONSULTAR_CASA && $location.path() == paginaVincularConsultarCasa ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_VINCULAR_CONSULTAR_EDIFICIO && $location.path() == paginaVincularConsultarEdificio ){	$location.path("/semAcesso");}
				 	if(!$rootScope.ROLE_VINCULAR_CONSULTAR_EDIFICACOES_COMUNITARIA && $location.path() == paginaVincularConsultarComunitario ){	$location.path("/semAcesso");}
				 	
				 	
				 var tempo = new Number();
					
				 tempo = 1800;
				
		 startCountdown = function(){		     
		 
		     if((tempo - 1) >= 0){		    	 	
		
		         var min = parseInt(tempo/60);	         
		 
		         var seg = tempo%60;	  
		
		         if(min < 10){
		
		             min = "0"+min;
		
		             min = min.substr(0, 2);
		 
		         }
		 
		         if(seg <=9){
		 
		             seg = "0"+seg;		 
		         }		  
		 
		         $rootScope.horaImprimivel = '00:' + min + ':' + seg;
		 			         	
		        
		         $rootScope.teste =  setTimeout('startCountdown()',1000); 
		         
		         tempo--;		
		 
		     } else {
		 
		    	 auth.clear();
		    	 clearTimeout($rootScope.teste);
		     }
		 }					
		 clearTimeout($rootScope.teste);
				 startCountdown();
				 
	  });
	});
