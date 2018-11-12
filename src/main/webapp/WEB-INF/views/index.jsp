<!DOCTYPE html>
<%@include file="/WEB-INF/shared/cssResources.jsp"%>
<%@include file="/WEB-INF/shared/javascriptResources.jsp"%>
<html ng-app="stockanalysis">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body ng-controller="homeCtrl">
	<div class="row">
		<div class="col-md-3">
			<select class="form-control companyList" ng-model="selectedCompany"
				size="30">
				<option ng-repeat="company in listOfCompanies track by $index"
					value="{{company.symbol}}">{{company.name}}</option>
			</select>
			<button ng-click="addToWatchlist()">Add To Watchlist</button>
			<button ng-click="removeFromWatchlist()">Remove From
				Watchlist</button>
			<button ng-click="start()">Start</button>
			<button ng-click="stop()">Stop</button>
			<button ng-click="updateWatchlist()">Get Signals</button>
			<button ng-click="stopWatchlist()">Stop Signals</button>
		</div>
		<div class="col-md-8">
			<div class="row">
				<br> <br> <br> <br>
			</div>
			<div class="row">
				<table class="table table-bordered">
					<tr>
						<th>Time</th>
						<th>Name</th>
						<th>Current Value</th>
						<th>MACD</th>
						<th>Signal</th>
					</tr>
					<tr ng-repeat="c in watchlistToShow">
						<td>{{c.time}}</td>
						<td>{{c.symbol}}</td>
						<td>{{c.stockValue}}</td>
						<td>{{c.value}}</td>
						<td ng-class="c.signal == 'buy'? success : fail">{{c.signal}}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>