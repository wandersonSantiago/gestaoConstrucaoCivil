app.factory('categoriaService', function($rootScope, toastr, $http,$q){
	
	
	return{
		salva: function(categoria){
			return $http.post('rest/almoxarifado/categoria/salva',categoria)
			.then(function(response){
				sweetAlert({ timer : 3000,text :"Salvo com sucesso",  type : "success", width: 300, higth: 100, padding: 20});
			return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
				});
		},
		lista: function(){
			return $http.get('rest/almoxarifado/categoria/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					});
		},
		listaTipoCategoria: function(){
			return $http.get('rest/almoxarifado/categoria/tipoCategoria')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					});
		},
		
		buscaPorId: function(param){
			return $http.get('rest/almoxarifado/categoria/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		altera: function(categoria){
			return $http.put('rest/almoxarifado/categoria/altera', categoria)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso",  type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
	}
});