app.controller("homeCtrl", [ "$scope", "restApi", function($scope, restApi) {
	$scope.listOfCompanies = [];
	$scope.watchlistToShow = [];
	$scope.init = function() {
		restApi.getAllCompanies().then(function(data) {
			$scope.listOfCompanies = data.data;
		});
	}
	$scope.init();

	$scope.addToWatchlist = function() {
		restApi.addToWatchList($scope.selectedCompany).then(function(data) {
			console.log(data);
		});
	}

	$scope.removeFromWatchlist = function() {
		restApi.removeToWatchList($scope.selectedCompany).then(function(data) {
			console.log(data);
		});
	}

	$scope.start = function() {
		restApi.startSniper().then(function(data) {
			$scope.listOfCompanies = data.data;
		});
	}

	$scope.stop = function() {
		restApi.stopSniper().then(function(data) {
			$scope.listOfCompanies = data.data;
		});
	}

	$scope.getSignals = function() {
		restApi.getSignals().then(function(data) {
			if (data && data.data) {
				console.log(data);
				$scope.watchlistToShow = compareValues($scope.watchlistToShow, data.data);
			}
		});
	}
	var compareValues = function(oldValues, newValues){
		_.forEach(oldValues, function(oldV){
			_.forEach(newValues, function(newV){
				if(oldV.symbol == newV.symbol && oldV.signal != newV.signal){
					if(newV.signal == 'buy'){
						
					}
					else{
						createSound();
					}
				}
				createSound(440);
			});
		});
		return newValues;
	}
	
	var createSound = function(freq){
		var context = new (window.AudioContext || window.webkitAudioContext)();
		var osc = context.createOscillator(); // instantiate an oscillator
		osc.type = 'square';//'sine'; // this is the default - also square, sawtooth, triangle
		osc.frequency.value = freq; // Hz
		osc.connect(context.destination); // connect it to the destination
		osc.start(); // start the oscillator
		osc.stop(context.currentTime + 1);
	}
	$scope.updateWatchlist = function() {
		$scope.watchInterval = setInterval(function(){ $scope.getSignals(); }, 3000);
		//$scope.getSignals();
	}
	
	$scope.stopWatchlist = function(){
		clearInterval($scope.watchInterval);
	}
} ]);