<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>humble finance - html5 visualization</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width">
<meta name="description"
	content="HumbleFinance is a HTML5 and Canvas finance visualization tool written in JavaScript." />
<link rel="stylesheet" type="text/css" href="css/hsd.css" />
<link rel="stylesheet" type="text/css" href="css/finance.css" />
</head>

<body>

	<div id="content-container">
		<div id="content">
			<div id="finance">
				<div id="labels">
					<div id="financeTitle">Commits </div>
					<div id="dateRange"></div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/hsd.js"></script>
	<script src="js/prototype.min.js"></script>
	<script src="js/Finance.js"></script>
	<script src="js/excanvas.js"></script>
	<script src="js/base64.js"></script>
	<script src="js/canvas2image.js"></script>
	<script src="js/canvastext.js"></script>
	<script src="js/flotr.js"></script>
	<script src="js/HumbleFinance.js"></script>
	<script type="text/javascript">
		Event.observe(document, 'dom:loaded', function() {
			jsonData = "${name}";
			jsonData = eval('(' + jsonData + ')'); 
			priceData = "${data}";
			priceData = eval('(' + priceData + ')'); 
			volumeData = priceData;
			summaryData = [];

			prettyPrint();

			HumbleFinance.trackFormatter = function(obj) {

				var x = Math.floor(obj.x);
				var data = jsonData[x];
				var text = data.date + " commit : " + data.commit;

				return text;
			};

			HumbleFinance.yTickFormatter = function(n) {

				if (n == this.axes.y.max) {
					return false;
				}

				return n;
			};

			HumbleFinance.xTickFormatter = function(n) {

				if (n == 0) {
					return false;
				}

				var date = jsonData[n].date;

				return date;
			}

			HumbleFinance.init('finance', priceData, volumeData, summaryData);
			HumbleFinance.setFlags(flagData);

			var xaxis = HumbleFinance.graphs.summary.axes.x;
			var prevSelection = HumbleFinance.graphs.summary.prevSelection;
			var xmin = xaxis.p2d(prevSelection.first.x);
			var xmax = xaxis.p2d(prevSelection.second.x);

			$('dateRange').update(
					jsonData[xmin].date + ' - ' + jsonData[xmax].date);

			Event.observe(HumbleFinance.containers.summary, 'flotr:select',
					function(e) {

						var area = e.memo[0];
						xmin = Math.floor(area.x1);
						xmax = Math.ceil(area.x2);

						var date1 = jsonData[xmin].date;
						var date2 = jsonData[xmax].date;

						$('dateRange').update(
								jsonData[xmin].date + ' - '
										+ jsonData[xmax].date);
					});
		});
	</script>
</body>
</html>
