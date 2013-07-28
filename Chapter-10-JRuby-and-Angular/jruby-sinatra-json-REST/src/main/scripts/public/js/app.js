function StockPortfolioCtrl($scope, $http){
	var validSymbol = false;
	$scope.v = {CurrentTime: Date.now()}

	// Get
	$http.get('/stocks', { "data" : ""}).
	        success(function(data, status) {$scope.status = status; $scope.stocks = data;}).
			error(  function(data, status) {$scope.status = status;});
    
	// Delete
	$scope.deleteStock =  function(t, index){
	
		if (confirm('Delete '+t+'?')){
			// Delete the record from the local collection
			$scope.stocks.splice(index, 1);
			
			// REST call to delete the record
			$http.delete('/stock/' + t, { "data" : t}).
	        	success(function(data, status) {$scope.status = status;}).
				error(  function(data, status) {$scope.status = status;});
		}
	}
	
	// JSONP Call to Google to lookup company information
	$scope.getStockData = function(){
		
		if ($scope.t.length > 0)
		{
			var url = 'http://www.google.com/finance/info?callback=JSON_CALLBACK&infotype=infoquoteall&q=' + 
					$scope.t ;
			$http.jsonp(url ,{isArray: true}).
		    	success(function(data, status, headers, config) {
					window.myData = data;
					$scope.name = data[0].name;
					$scope.l_cur = data[0].l_cur;
					validSymbol = true;
		    		}).
		    	error(function(data, status, headers, config) {
					validSymbol = false;
					
					// Could never put in GOOG due to invalid test for GO
					//$scope.t='';
					//$scope.l_cur='';
					//$scope.name='';
			    	
					// Ignore invalid requests
					// alert("status:"+status + " headers:" + headers +' data '+ data)
		    	});
		}	
	}
	
	// Add
	$scope.showAddForm = function() {
        $scope.addFormIsVisible = true; 
    };

 	$scope.hideAddForm = function() {
        $scope.t = '';
        $scope.name = '';
		$scope.l_cur = '';

        $scope.addFormIsVisible = false; 	        
    };

	$scope.saveStock = function() {
		    var rec = {
	        "created_at": new Date(),
	        "t": $scope.t,
	        "name": $scope.name,
	        "l_cur": $scope.l_cur}
	
			if (validSymbol===true){
	
	        	$scope.stocks.push(rec);

				$http.put('/stock/' + $scope.t, {"data" : rec}).
	        		success(function(data, status) {$scope.status = status;}).
					error(  function(data, status) {alert(data);$scope.status = status;});

	         	$scope.hideAddForm();
			}else{
				$scope.t = '';
		        $scope.name = '';
				$scope.l_cur = '';
			}
	    };

	// Order
    $scope.orderProp = 't';	
    $scope.orderBy = function(col) {$scope.orderProp = col;}	

}