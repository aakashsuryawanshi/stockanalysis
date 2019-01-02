<!DOCTYPE html>
<%@include file="/WEB-INF/shared/cssResources.jsp"%>
<%@include file="/WEB-INF/shared/javascriptResources.jsp"%>
<html ng-app="stockanalysis">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body ng-controller="homeCtrl" class="container">
	<div class="row">
		<div class="col-md-12">
			<br>
		</div>
	</div>
	<div class="row">
		<div class="col-md-8">
			<selectize options='listOfCompanies' config="selectizeConfig"
				ng-model="selectedCompany" name='myName2'></selectize>
		</div>
		<div class="col-md-3">
			<button ng-click="addToWatchlist()">Add To Watchlist</button>
			<button ng-click="start()">Start</button>
			<button ng-click="stop()">Stop</button>
			<!-- <button ng-click="removeFromWatchlist()">Remove From
				Watchlist</button>
			<button ng-click="updateWatchlist()">Get Signals</button>
			<button ng-click="stopWatchlist()">Stop Signals</button> -->
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<br> <br> <br> <br>
			</div>
			<div class="row">
				<table class="table table-bordered">
					<tr>
						<th>Sr. No.</th>
						<th>Symbol</th>
						<th>Name</th>
						<th>Time</th>
						<th>Current Value</th>
						<th>MACD</th>
						<th>Signal</th>
						<th>Delete</th>
					</tr>
					<tr
						ng-repeat="c in watchlistToShow | orderBy: name track by $index"
						ng-class="{changed: c.changed != undefined && c.changed == 1, notchanged: c.changed != undefined && c.changed == 0}">
						<td>{{$index + 1}}</td>
						<td>{{c.symbol}}</td>
						<td>{{c.name}}</td>
						<td>{{c.time}}</td>
						<td>{{c.stockValue}}</td>
						<td>{{c.value}}</td>
						<td
							ng-class="{success: c.signal == 'buy', fail: c.signal == 'sell'}">{{c.signal}}</td>
						<td><a href="#" ng-click="removeFromWatchlist(c.symbol)">Delete</a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>