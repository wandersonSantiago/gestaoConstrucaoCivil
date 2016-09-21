app.factory('enderecoService', function($http,$q , $rootScope, toastr)
{

	 return {

			
			lista: function(){
				return $http.get('rest/endereco/uf')
				.then(function(response){
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "error", width: 300, higth: 100, padding: 20});	return $q.reject(errResponse);
				});
			},
			
	
	 }
	 
});