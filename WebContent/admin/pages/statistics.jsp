
<%@include file="layout/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section id="content">
    <div class="page">
        <ul>
            <li><a href=""><i class="fa fa-home"></i></a></li>
            <li><a href="">Thống kê</a></li>
        </ul>
        <div>
        	<h4>Thống kê doanh thu theo ngày</h4>
        	<canvas id="chartjs-0" class="chartjs"></canvas>
        </div>
        <div>
        	<h4>Thống kê doanh thu theo tháng</h4>
        	<canvas id="myChart"></canvas>
        </div>
        <div>
        	<h4>Thống kê doanh thu sản phẩm mua nhiều nhất theo tháng</h4>
        	<canvas id="myChart1"></canvas>
        </div>
        
    </div>
    <!-- Modal Detail -->
    
</section>
<%@include file="layout/footer.jsp"%>
<script src="vendors/sweetalert.min.js"></script>
<script src="js/statistic.js"></script>
<script type="text/javascript">

</script>
