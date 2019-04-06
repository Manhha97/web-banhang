
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="layout/header.jsp"%>
<!-- START CONTENT -->
<section id="content">
    <div class="page">
        <ul>
            <li><a href=""><i class="fa fa-home"></i></a></li>
            <li><a href="">Quản lý sản phẩm</a></li>
            <li><a href="">Thông tin sản phẩm</a></li>
        </ul>
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="clearfix" style="margin-bottom: 40px;">
                    <h4 class="left">Thông tin </h4>
                </div>
                <div>
                    <form class="col s12 form-add" enctype="multipart/form-data;charset=UTF-8">
                        <div class="row">
                            <div class="col s2">
                                <img src="images/avatar-7.png" alt="" class="circle z-depth-2 responsive-img activator gradient-45deg-light-blue-cyan">
                                <div class="file-upload">
                                    <a href="#">
                                        <i class="material-icons grey-text text-darken-1">camera_alt</i>
                                        <input type="file" accept="image/*" name="image">
                                    </a>
                                    <span><i class="file-name">image_name</i></span>
                                </div>
                            </div>
                            <div class="col s10 form-add">
                                    <div class="row">
                                        <% if(request.getAttribute("code") != null){ %>
                                            <div class="input-field col s12">
                                            <input id="p_code" type="text" name="code" readonly>
                                            <label key="code" for="p_code">Code</label>
                                            </div>
                                        <%}%>
                                        <div class="input-field col s12">
                                            <input id="p_name" type="text" name="name" required>
                                            <label key="name" for="p_name">Name</label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="input-field col s4">
                                            <select id="select_typeId" name="typeId" required>
                                            </select>
                                            <label key="typeId" for="select_typeId">Thể loại</label>
                                        </div>
                                        <div class="input-field col s4">
                                            <select id="select_authorId" name="authorId" required>
                                            </select>
                                            <label key="authorId" for="select_authorId">Tác giả</label>
                                        </div>
                                        <div class="input-field col s4">
                                            <select id="select_nxbId" name="nxbId" required>
                                            </select>
                                            <label key="nxbId" for="select_nxbId">Nhà xuất bản</label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="input-field col s4">
                                            <input value="2" id="p_quantity" type="number" name="quantity" required>
                                            <label key="quantity" for="p_quantity">Quantity</label>
                                        </div>
                                        <div class="input-field col s4">
                                            <input value="3" id="p_price" type="number" name="price" required>
                                            <label key="price" for="p_price">Price</label>
                                        </div>
                                        <div class="input-field col s4">
                                            <input value="1" id="p_sale" type="number" name="sale" required>
                                            <label key="sale" for="p_sale">Sale</label>
                                        </div>
                                    </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="row" style="text-align: center;">
                                <div class="input-field col s12">
                                    <a class="waves-effect waves-light button-design" href="/admin/home">
                                        <i class="fa fa-arrow-left"></i>
                                        Quay lại
                                    </a>
                                    <button class="waves-effect waves-light button-design" type="submit">
                                        <i class="fa fa-floppy-o"></i>
                                        Lưu
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- END CONTENT -->
<%@include file="layout/footer.jsp"%>
<script src="js/product-update.js"></script>
