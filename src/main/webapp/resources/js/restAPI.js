
app.service("restApi", ["$http", function($http){
	var baseUrl = "http://localhost:8085/analysis";
	
	this.getAllCompanies = function(){
		var url = baseUrl + "/api/companies";
		return $http.get(url);
	}
}]);