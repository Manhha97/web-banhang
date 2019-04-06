<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- header --%>
<%@include file="layout/header-footer/header.jsp"%>
<%-- main --%>
<div class="columns-container">
    <div id="columns" class="container">
        <div class="inner_container">
            <div class="inner_container_sub">
                <div class="row" id="columns_inner">
                    <%@include file="layout/home/left.jsp"%>
                    <%@include file="layout/wish-list/main.jsp"%>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="layout/header-footer/footer.jsp"%>