app.controller( 'loginController', function($route, $location, $rootScope, auth) {
			
			var self = this;
			self.falhaLogin = true;
			self.credentials = {};
			
			self.tab = function(route) {
				
				return $route.current && route === $route.current.controller;
			};

			self.authenticated = function() {
			
				return auth.authenticated;
			}

			self.login = function() {
				auth.authenticate(self.user, function(authenticated) {
				
					if (authenticated) {
						$rootScope.logado = true;
						$location.path("#/");
						
						self.error = false;
					} else {
						self.falhaLogin = false;
						
						self.error = true;
					}
				})
			};

			self.logout = auth.clear;

		});
