<%--
  Created by IntelliJ IDEA.
  User: HuyChu
  Date: 11/24/2018
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- header --%>
<%@include file="layout/header-footer/header.jsp"%>
<%-- main --%>
<div class="columns-container">
    <div id="columns" class="container">
        <div class="inner_container">
            <div class="inner_container_sub">
                <div class="row" id="columns_inner">
                    <%@include file="layout/order/checkout.jsp"%>
                </div>
            </div>
        </div>
    </div>
</div>
<script  defer src="https://maps.googleapis.com/maps/api/js?libraries=places&language=en&key=AIzaSyBREMcBMUrIbUUE7CEZl6K1YihvFW-i-18"  type="text/javascript"></script>
<%@include file="layout/header-footer/footer.jsp"%>
<script src="client/js/checkout.js"></script>