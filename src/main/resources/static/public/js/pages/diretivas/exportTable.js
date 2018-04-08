app.directive('exportTable', function(){
	return {
		restrict : 'C',
		link : function($scope, elm, attr){
		$scope.$on('export-pdf', function(e, d){
		     // elm.tableExport({type:'pdf', escape:false, pdfFontSize:7, pdfLeftMargin:20});
		      newWin= window.open("");
			  newWin.document.write(elm[0].outerHTML);
			  newWin.print();
			  newWin.close();
		 });
		$scope.$on('export-excel', function(e, d){
		     // elm.tableExport({type:'excel', escape:false});
		      window.open('data:application/vnd.ms-excel,' + elm[0].outerHTML);
		 });
		$scope.$on('export-doc', function(e, d){
		      elm.tableExport({type: 'doc', escape:false});
		 });
		} 
	};
});