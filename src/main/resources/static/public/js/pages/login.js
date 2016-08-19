app.controller( 'loginController', function($route, $location, $rootScope, auth) {
			
			var self = this;

			self.credentials = {};
			
			self.tab = function(route) {
				console.log("estou no controle de rotas");
				return $route.current && route === $route.current.controller;
			};

			self.authenticated = function() {
				console.log("estou no autentication");
				return auth.authenticated;
			}

			self.login = function() {
				auth.authenticate(self.user, function(authenticated) {
				console.log("estou no método de login");
					if (authenticated) {
						$rootScope.logado = true;
						$location.path("/home");
						console.log("Logado com sucesso")
						self.error = false;
					} else {
						console.log("falha no login")
						self.error = true;
					}
				})
			};

			self.logout = auth.clear;

		});
