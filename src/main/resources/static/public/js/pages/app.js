var app = angular.module('gcc',['ui.router','ngAnimate','toastr', 'angularFileUpload', 'ngResource','ui.bootstrap','ngCookies','ui.utils.masks','blockUI','checklist-model', 'ncy-angular-breadcrumb','flow','ngStorage','idf.br-filters', 'chart.js'])
.config(function(blockUIConfig) {
	  
	  // Change the default overlay message
	  blockUIConfig.message = 'Carregando...';
	  
	  // Change the default delay to 100ms before the blocking is visible
	  blockUIConfig.delay = 100;
	  
	  blockUIConfig.autoBlock = false;
	  
	  blockUIConfig.resetOnException = true;
	  
	});
;
