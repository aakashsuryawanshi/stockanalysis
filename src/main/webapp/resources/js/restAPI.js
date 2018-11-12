
app.service("restApi", ["$http", function($http){
	var baseUrl = "http://localhost:8085/analysis";
	
	this.getAllCompanies = function(){
		var url = baseUrl + "/api/companies";
		return $http.get(url);
	}
	
	this.startSniper = function(){
		var url = baseUrl + "/api/startSniper";
		return $http.get(url);
	}
	
	this.stopSniper = function(){
		var url = baseUrl + "/api/stopSniper";
		return $http.get(url);
	} 
	
	this.getSignals = function(){
		var url = baseUrl + "/api/getSignals";
		return $http.get(url);
	} 
	
	this.addToWatchList = function(symbol){
		var url = baseUrl + "/api/watchlist/" + symbol + "/add";
		return $http.get(url);
	}
	
	this.removeToWatchList = function(symbol){
		var url = baseUrl + "/api/watchlist/" + symbol + "/remove";
		return $http.get(url);
	}
}]);