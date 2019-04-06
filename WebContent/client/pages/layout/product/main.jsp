
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="center_column" class="center_column col-xs-12" style="width:77%;">
    <div class="breadcrumb clearfix"><a class="home" href="index-2.html" title="Return to Home"><i
            class="icon-home"></i></a> <span class="navigation-pipe">&gt;</span> <span class="navigation_page"><span class="lang" key="home"></span></span>
    </div>
    <div class="content_scene_cat">
        <div class="align_center owl-carousel owl-theme">
            <div class="item"><img src="client/img/slider/slide-1.jpg" title="Electronics" width="737" height="112"/></div>
            <div class="item"><img src="client/img/slider/slide-2.jpg" title="Electronics" width="737" height="112"/></div>
            <div class="item"><img src="client/img/slider/slide-3.jpg" title="Electronics" width="737" height="112"/></div>
            <div class="item"><img src="client/img/slider/slide-4.jpg" title="Electronics" width="737" height="112"/></div>
            <div class="item"><img src="client/img/slider/slide-5.jpg" title="Electronics" width="737" height="112"/></div>

        </div>
        <div class="cat_desc">
            <div class="rte">
                <p></p>
            </div>
        </div>
    </div>
    <h1 class="page-heading product-listing">
        <span class="cat-name"><span class="lang" key="highlight"></span>&nbsp;</span>
        <span class="heading-counter">There are <span id="product-count"></span> products.</span>
    </h1>

    <div class="content_sortPagiBar clearfix">
        <div class="sortPagiBar clearfix">
            <ul class="display hidden-xs">
                <li class="display-title">View:</li>
                <li id="grid"><a rel="nofollow" href="#" title="Grid"><i class="icon-th-large"></i>Grid</a></li>
                <li id="list"><a rel="nofollow" href="#" title="List"><i class="icon-th-list"></i>List</a></li>
            </ul>
            <form id="productsSortForm"
                  action=""
                  class="productsSortForm">
                <div class="select selector1">
                    <label for="selectProductSort"><span class="lang" key="sort"></span></label>
                    <select id="selectProductSort" class="selectProductSort form-control">
                        <option value="all:asc" selected="selected">--</option>
                        <option value="price_sort:asc">Giá từ thấp tới cao</option>
                        <option value="price_sort:desc">Giá từ cao tới thấp</option>
                        <option value="name_sort:asc">Tên sản phẩm: A to Z</option>
                        <option value="name_sort:desc">Tên sản phẩm: Z to A</option>
                    </select>
                </div>
            </form>
            <form action=""
                  method="get" class="nbrItemPage">
                <div class="clearfix selector1">
                    <label for="selectPageSize"> <span class="lang" key="show"></span> </label>
                    <select name="pageSize" id="selectPageSize" class="form-control">
                        <option value="6" selected="selected">6</option>
                        <option value="12">12</option>
                    </select>
                    <span>per page</span>
                </div>
            </form>
        </div>
    </div>
    <div id="layered_ajax_loader">
        <p> <img src="client/img/loader.gif" alt=""> <br>Loading...</p>
    </div>
    <ul class="product_list grid row">


    </ul>
    <div class="content_sortPagiBar">
        <div class="bottom-pagination-content clearfix">
            <div id="pagination_bottom" class="pagination clearfix">

                <ul class="pagination">
                    <%--<li id="pagination_previous_bottom" class="disabled pagination_previous"><span> <i--%>
                            <%--class="icon-chevron-left"></i> <b>Previous</b> </span></li>--%>

                    <%--<li id="pagination_next_bottom" class="pagination_next"><a--%>
                            <%--href="">--%>
                        <%--<b>Next</b> <i class="icon-chevron-right"></i> </a></li>--%>
                </ul>
            </div>
        </div>
    </div>
</div>
