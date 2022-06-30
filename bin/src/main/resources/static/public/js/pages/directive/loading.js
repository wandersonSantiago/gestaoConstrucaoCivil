app.directive('loading', [ '$http', 'blockUI', function($http, blockUI) {
	return {
		restrict : 'A',
		link : function(scope, elm, attrs) {
			
			scope.isLoading = function() {
				return $http.pendingRequests.length > 0;
			};

			scope.$watch(scope.isLoading, function(v) {
				if (v) {
					blockUI.start("Carregando...");
				} else {
					blockUI.stop();
				}
			});
		}
	};

} ]);