app.controller("homeCtrl", [ "$scope", "restApi", function($scope, restApi) {
	$scope.listOfCompanies = [];
	$scope.watchlistToShow = [];
	$scope.init = function() {
		restApi.getAllCompanies().then(function(data) {
			$scope.listOfCompanies = data.data;
		});
	}
	$scope.init();

	$scope.selectizeConfig = {
		    maxItems: 5,
		  	labelField: 'name',
		  	searchField: ['name'],
		  	valueField: 'symbol'
		  };
	
	$scope.addToWatchlist = function() {
		_.forEach($scope.selectedCompany, function(selectedC){
			restApi.addToWatchList(selectedC).then(function(data) {
				console.log(data);
				$scope.watchlistToShow = convertToSignal(data.data.list);
			});
		});
	}

	$scope.removeFromWatchlist = function(symbol) {
		restApi.removeToWatchList(symbol).then(function(data) {
			console.log(data);
			$scope.watchlistToShow = convertToSignal(data.data.list);
		});
	}

	$scope.start = function() {
		restApi.startSniper().then(function(data) {
			console.log(data);
			$scope.listOfCompanies = data.data;
		},
		function(data){
			console.log(data);
		});
		$scope.updateWatchlist();
	}

	$scope.stop = function() {
		restApi.stopSniper().then(function(data) {
			console.log(data);
			$scope.listOfCompanies = data.data;
		},
		function(data){
			console.log(data);
		});
		$scope.stopWatchlist();
	}

	$scope.getSignals = function() {
		restApi.getSignals().then(function(data) {
			if (data && data.data) {
				console.log(data);
				$scope.watchlistToShow = compareValues($scope.watchlistToShow, data.data);
			}
		});
	}
	
	var convertToSignal = function(list){
		var result = [];
		_.forEach(list, function(item){
			var obj = {};
			obj.time = "";
			obj.value = "";
			obj.name = item.name;
			obj.symbol = item.symbol;
			obj.stockValue = "";
			result.push(obj);
		});
		return result;
	}
	var compareValues = function(oldValues, newValues){
		_.forEach(oldValues, function(oldV){
			_.forEach(newValues, function(newV){
				if(oldV.symbol == newV.symbol && oldV.signal != newV.signal){
					if(newV.signal == 'buy'){
						createSound(340);
					}
					else{
						createSound(440);
					}
				}
				//createSound(440);
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