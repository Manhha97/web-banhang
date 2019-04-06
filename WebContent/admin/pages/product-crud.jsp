
<%@include file="layout/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section id="content">
    <div class="page">
        <ul>
            <li><a href=""><i class="fa fa-home"></i></a></li>
            <li><a href="">Quản lý sản phẩm</a></li>
        </ul>
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="clearfix">
                    <h4 class="left">Thông tin tìm kiếm</h4>
                    <a href="javascript:;" class="right display-form">
                        <i class="fa fa-chevron-down"></i>
                    </a>
                </div>
                <div class="form-search">
                    <form class="col s12">
                        <div class="row">
                            <div class="input-field col s6">
                                <input id="p_code" type="text" name="code">
                                <label for="p_code">Code</label>
                            </div>
                            <div class="input-field col s6">
                                <input id="p_name" type="text" name="name">
                                <label for="p_name">Name</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s4">
                                <select id="select_typeId" name="typeId">
                                </select>
                                <label for="select_typeId">Thể loại</label>
                            </div>
                            <div class="input-field col s4">
                                <select id="select_authorId" name="authorId">
                                </select>
                                <label for="select_authorId">Tác giả</label>
                            </div>
                            <div class="input-field col s4">
                                <select id="select_nxbId" name="nxbId">
                                </select>
                                <label for="select_nxbId">Nhà xuất bản</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="row" style="text-align: center;">
                                <div class="input-field col s12">
                                    <button class="waves-effect waves-light button-design" type="button">
                                        <i class="fa fa-search"></i>
                                        Tìm kiếm
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-body" style="margin-bottom: 0;">
                <div class="clearfix">
                    <h4>Kết quả tìm kiếm</h4>
                </div>
            </div>
            <div class="content">
                <div>
                    <div class="row" style="background: #f9f9f9;">
                        <div class="row">
                            <div class="input-field col s12">
                                <a href="/admin/product?action=insert" class="add left waves-effect waves-light button-design">
                                    <i class="fa fa-plus"></i>
                                    Thêm mới
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <table class="striped highlight products">
                        <thead>
                        <tr>
                            <th>Item Code</th>
                            <th>Action</th>
                            <th>Item Name</th>
                            <th>Item Quantity</th>
                            <th>Item Price</th>
                        </tr>
                        </thead>
                        <tbody>


                        </tbody>

                    </table>
                    <div class="pagination">
                        <div>
                            <label for="select-page">Page:</label>
                            <select id="select-page">
                            </select>
                        </div>
                        <div>
                            <label>Rows per page</label>
                            <select id="select-page-size">
                                <option>5</option>
                                <option>10</option>
                            </select>
                        </div>
                        <div class="pagi-detail"><span>1 - 5 of 9</span></div>
                        <div>
                            <button type="button" class="go-back"><i class="fa fa-chevron-left waves-effect waves-light" aria-hidden="true"></i></button>
                            <button type="button" class="go-to"><i class="fa fa-chevron-right waves-effect waves-light" aria-hidden="true"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal Detail -->
    <div id="modal_detail" class="modal">
        <h4>Thông tin sản phẩm </h4>
        <div class="row">
            <div class="col s2">
                <div class="col s12">
                </div>
                <img src="images/avatar-7.png" alt="" class="circle z-depth-2 responsive-img activator gradient-45deg-light-blue-cyan">
            </div>
            <div class="col s10">
                <div class="col s6">
                    <p>Mã : <span title="code"></span></p>
                    <p>Số lượng: <span title="quantity"></span></p>
                    <p>Thể loại: <span title="typeId"></span></p>
                    <p>Tác giả: <span title="authorId"></span></p>
                </div>
                <div class="col s6">
                    <p>Tên: <span title="name"></span></p>
                    <p>Giá: <span title="price"></span></p>
                    <p>Sale: <span title="sale"></span></p>
                    <p>Nhà xuất bản: <span title="nxbId"></span></p>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <a class="modal-action modal-close waves-effect waves-green btn-flat">Close</a>
        </div>
    </div>
</section>
<%@include file="layout/footer.jsp"%>
<script src="vendors/sweetalert.min.js"></script>
<script type="text/javascript" src="js/product-crud.js"></script>