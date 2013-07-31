	$scope.drawChart = function(symbol, hi, lo, curr){
		var canvas, c, t, m;
		try{
		canvas = document.getElementById('graph'+symbol);
		c = canvas.getContext('2d')
		c.fillStyle="#000000";
		c.fillRect(0,0,150,20);
		c.fillStyle="#FF0000";

		t = hi - lo; 
		m = hi - curr  					
		c.fillRect(0,0,(m/t) * 150,20);
		}catch(exception){}
	}
