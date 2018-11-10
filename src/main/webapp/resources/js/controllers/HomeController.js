app.controller("homeCtrl", ["$scope","restApi", function($scope, restApi) {
	
	$scope.init = function(){
		restApi.getAllCompanies().then(function(data){
			console.log(data);
		});
	}
	$scope.init();
    $scope.products = ["Milk", "Bread", "Cheese"];
    console.log($scope.products);
}]);