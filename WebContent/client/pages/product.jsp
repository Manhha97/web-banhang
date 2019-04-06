<%--
  Created by IntelliJ IDEA.
  User: HuyChu
  Date: 11/22/2018
  Time: 17:01
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
                    <%@include file="layout/product/left.jsp"%>
                    <%@include file="layout/product/main.jsp"%>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="notification"></div>
<div id="notificationModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Sản phẩm mới</h4>
            </div>
            <div class="modal-body">
                <div class="list-group">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>
<%@include file="layout/header-footer/footer.jsp"%>
<script src="client/js/product.js"></script>