var app = angular.module('referrals', [ 'ngMaterial' ]);

app.controller("domain",
	function($scope, $http, $mdDialog) {
		// loads all the domains names on initial page load
		$http.get('http://localhost:8080/domain/name').success(
			function(data) {
				$scope.names = data.domains;
		});
		
		// represents state of "Show Top 3" checkbox
		$scope.showTop3 = false;
		
		// retrieves to the top 3 domains by referral count
		$scope.retrieveTop3 = function() {
			$http.get('http://localhost:8080/domain/name/top?n=3').success(
				function(data) {
					$scope.names = data.domains;
			});
		}
		
		// retrieves all tracked domains
		$scope.showAll = function() {
			$http.get('http://localhost:8080/domain/name').success(
				function(data) {
					$scope.names = data.domains;
			});
		}
		
		// handles the "Show Top 3" toggling events
		$scope.toggleShowTop3 = function() {
			if ($scope.showTop3) 
			{
				$scope.retrieveTop3();
			}
			else 
			{
				$scope.showAll();
			}
		}
	
		// draws a line graph for the given domain name after
		// retrieving its stats
		$scope.showGraph = function($event, id, name) {
			$http.get('http://localhost:8080/stat/referral/' + id)
			.success(function(data) {
				$scope.dates = data;
				var parentEl = angular.element(document.body);
				
				// draw the chart in a modal window popup
				$mdDialog
				.show({
					parent : parentEl,
					targetEvent : $event,
					template : '<md-dialog flex="75" aria-label="Chart dialog">'
							+ '  <md-dialog-content>'
							+ '    	<canvas id="domainGraph" width="500" height="250"></canvas>'
							+ '  </md-dialog-content>'
							+ '  <md-dialog-actions>'
							+ '    <md-button ng-click="closeDialog()" class="md-primary">'
							+ '      Close Graph'
							+ '    </md-button>'
							+ '  </md-dialog-actions>'
							+ '</md-dialog>',
					locals : {
						dates : $scope.dates
					},
					onComplete: drawGraph,
					controller : DialogController
				});
				
				// now that we have data, draw the graph on the canvas element
				function drawGraph() {
					var ctx = document.getElementById("domainGraph");
					
					// hacky shortcut to get the total referrals to display
					// ideally this would just come from the WS
					var total = 0;
					for ( var i = 0; i < $scope.dates.length; i++) {
						total += $scope.dates[i];
					}
					
					// configure the chart
					var data = {
						// these hardcoded labels are pretty much a hack to simplify this demo
					    labels: ["January", "February", "March", "April", "May", "June", "July"],
					    datasets: [
					        {
					            label: name + " -- Total: " + total,
					            fill: true,
					            lineTension: 0.1,
					            backgroundColor: "rgba(75,192,192,0.4)",
					            borderColor: "rgba(75,192,192,1)",
					            borderCapStyle: 'butt',
					            borderDash: [],
					            borderDashOffset: 0.0,
					            borderJoinStyle: 'miter',
					            pointBorderColor: "rgba(75,192,192,1)",
					            pointBackgroundColor: "#fff",
					            pointBorderWidth: 1,
					            pointHoverRadius: 10,
					            pointHoverBackgroundColor: "rgba(75,192,192,1)",
					            pointHoverBorderColor: "rgba(220,220,220,1)",
					            pointHoverBorderWidth: 2,
					            pointRadius: 5,
					            pointHitRadius: 10,
					            data: $scope.dates, // data here
					        }
					    ]
					};

					// initialize the chart, give the config data
					var myLineChart = new Chart(ctx, {
						type: 'line',
					    data: data
					});
				}
	
				// "controller" for chart window popup
				function DialogController($scope, $mdDialog, dates) {
					$scope.dates = dates;
					$scope.closeDialog = function() {
						$mdDialog.hide();
					}
				}
			});
		}
	}
);