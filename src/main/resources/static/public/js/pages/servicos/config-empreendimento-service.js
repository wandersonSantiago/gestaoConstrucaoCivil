app.factory('configEmpreendimentoService', function($rootScope, $http){
	
	
	return{
		
		empreendimentoSemConfiguracaoLista: function(){
			return $http.get('rest/empreendimento/empreendimento/empreendimentoSemConfiguracaoLista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
		return $q.reject(errResponse);
			});
		},
		
		configEmpreendimentoOutrosLista: function(){
			return $http.get('rest/empreendimento/configuracao/listaOutros')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
		return $q.reject(errResponse);
			});
		},
		
		quantidadeEdificio: function(){
			return $http.get('rest/empreendimento/configuracao/quantidadeEdificio')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
		return $q.reject(errResponse);
			});
		},
		
		configEmpreendimentoEdificioSalva: function(configEmpreendimentoEdificio){
			console.log(configEmpreendimentoEdificio);
			return $http.post('rest/empreendimento/configuracao/salvaEdificio', configEmpreendimentoEdificio)
			.then(function(response){			
				return response.data;
		},function(errResponse){
			sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
		});
		},
		
		configEmpreendimentoCasaSalva: function(configEmpreendimentoCasa){
			return $http.post('rest/empreendimento/configuracao/salvaCasa', configEmpreendimentoCasa)
			.then(function(response){
				return response.data;		
	},function(errResponse){
		sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
			return $q.reject(errResponse);			
		});
		},
	
		configEmpreendimentoOutrosSalva: function( listaOutros){
			return $http.post('rest/empreendimento/configuracao/salvaOutros', listaOutros)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
	}
});