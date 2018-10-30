app.directive('dateInput', function(){
    return {
        restrict : 'A',
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
        	
        	ctrl.$formatters.push(function (value){
        		if(value == null){
        			return null;
        		}
        		return new Date(value);
        	});           
        }
    };
});