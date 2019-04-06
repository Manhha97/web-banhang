
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- header --%>
<%@include file="layout/header-footer/header.jsp"%>
<%-- main --%>
    <div class="columns-container">
        <div id="columns" class="container">
            <div class="inner_container">
                <div class="inner_container_sub">
                    <%@include file="layout/slider/top.jsp"%>
                    <div class="row" id="columns_inner">
                        <%@include file="layout/home/left.jsp"%>
                        <%@include file="layout/home/main.jsp"%>
                    </div>
                    <%@include file="layout/slider/bottom.jsp"%>
                </div>
            </div>
        </div>
    </div>
<%@include file="layout/header-footer/footer.jsp"%>