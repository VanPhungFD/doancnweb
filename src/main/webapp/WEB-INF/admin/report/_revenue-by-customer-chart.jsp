<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Khách hàng', 'Doanh thu'],
          <c:forEach var="e" items="${rpcustomer}">
          ["${e[0]}", ${e[2]}],
          </c:forEach>
       
        ]);

        var options = {
          title: 'BIỂU ĐỒ DOANH SỐ KHÁCH HÀNG',
          curveType: 'function',
          titleTextStyle: {
		        color: "orangered",
		        fontName: "Impact",
		        fontSize: 20,
		    },
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>
</head>
<body>
	<div id="curve_chart" style="width: 900px; height: 500px"></div>
</body>
</html>