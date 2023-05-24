<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
          var data = google.visualization.arrayToDataTable([
            ['THÁNG', 'DOANH SỐ', { role: 'style' } ],
            <c:forEach var="c" items="${rpMonth}">
             ["${c[0]}", "${c[2]}", 'opacity: 0.2'],
           </c:forEach>
            /*  ['2010', 10, 'color: gray'],
             ['2020', 14, 'color: #76A7FA'],
             ['2030', 16, 'opacity: 0.2'],
             ['2040', 22, 'stroke-color: #703593; stroke-width: 4; fill-color: #C5A5CF'],
             ['2050', 28, 'stroke-color: #871B47; stroke-opacity: 0.6; stroke-width: 8; fill-color: #BC5679; fill-opacity: 0.2'] */
          ]);

        var options = {
          chart: {
            title: 'DOANH SỐ BÁN HÀNG QUA TỪNG THÁNG',
          }
        };

        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

               
        
        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
    </script>
</head>
<body>
	<div id="columnchart_material" style="width: 800px; height: 500px;"></div>
</body>
</html>
