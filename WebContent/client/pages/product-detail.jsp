<%--
  Created by IntelliJ IDEA.
  User: HuyChu
  Date: 11/24/2018
  Time: 11:40
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
                <div class="alert alert-success alert_wish_list">
                    <i class="fa fa-check-circle"></i> Success: You have modified your wish list!
                </div>
                <div class="row" id="columns_inner">
                    <%@include file="layout/product/detail.jsp"%>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="layout/header-footer/footer.jsp"%>
<script src="client/js/product_detail.js"></script>