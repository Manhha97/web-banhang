
<%@include file="layout/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section id="content">
    <div class="page">
        <ul>
            <li><a href=""><i class="fa fa-home"></i></a></li>
            <li><a href="">Quản lý hóa đơn</a></li>
        </ul>
        <%--<div class="panel panel-default">--%>
            <%--<div class="panel-body">--%>
                <%--<div class="clearfix">--%>
                    <%--<h4 class="left">Thông tin tìm kiếm</h4>--%>
                    <%--<a href="javascript:;" class="right display-form">--%>
                        <%--<i class="fa fa-chevron-down"></i>--%>
                    <%--</a>--%>
                <%--</div>--%>
                <%--<div class="form-search">--%>
                    <%--<form class="col s12">--%>
                        <%--<div class="row">--%>
                            <%--<div class="input-field col s6">--%>
                                <%--<input id="p_code" type="text" name="code">--%>
                                <%--<label for="p_code">Code</label>--%>
                            <%--</div>--%>
                            <%--<div class="input-field col s6">--%>
                                <%--<input id="p_name" type="text" name="name">--%>
                                <%--<label for="p_name">Thời gian</label>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="row">--%>
                            <%--<div class="input-field col s6">--%>
                                <%--<input id="p_code" type="text" name="code">--%>
                                <%--<label for="p_code">Người thực hiện</label>--%>
                            <%--</div>--%>
                            <%--<div class="input-field col s6">--%>
                                <%--<input id="p_name" type="text" name="name">--%>
                                <%--<label for="p_name">Khách hàng</label>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="row">--%>
                            <%----%>
                            <%--<div class="input-field col s4">--%>
                                <%--<select id="select_role_id" name="role_id">--%>
                                <%--</select>--%>
                                <%--<label for="select_role_id">Sản phẩm</label>--%>
                            <%--</div>--%>
                            <%----%>
                        <%--</div>--%>
                        <%--<div class="row">--%>
                            <%--<div class="row" style="text-align: center;">--%>
                                <%--<div class="input-field col s12">--%>
                                    <%--<button class="waves-effect waves-light button-design" type="button">--%>
                                        <%--<i class="fa fa-search"></i>--%>
                                        <%--Tìm kiếm--%>
                                    <%--</button>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</form>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="panel panel-default">
            <div class="panel-body" style="margin-bottom: 0;">
                <div class="clearfix">
                    <h4>Danh sách hóa đơn</h4>
                </div>
            </div>
            <div class="content">
                
                <div>
                    <table class="striped highlight products">
                        <thead>
                        <tr>
                            <th>Code</th>
                            
                            <th>Ngày thực hiện</th>
                            <th>Chi tiết</th>
                        </tr>
                        </thead>
                        <tbody class="general">


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
    <div id="modal_detail" class="modal" style="wi">
        <h4>Thông tin chi tiết của hóa đơn</h4>
        <div class="row">
            <div class="col s5">
               <p>Mã hóa đơn: <span title="code"></span></p>
               <p>Ngày thực hiện: <span title="createAt"></span></p>
               <p>Tổng tiền: <span title="total_price"></span></p>
            </div>
            <div class="col s7">
				<table>
					<tbody class="thongtin">
						<tr>
							<h5 style="font-size:16pt;">Thông tin giao hàng</h5>
						</tr>
						<tr>
							<td>Người nhận:</td>
							<td title="name"></td>
						</tr>
						<tr>
							<td>Địa chỉ giao hàng:</td>
							<td title="address"></td>
						</tr>
						<tr>
							<td>Số điện thoại liên hệ:</td>
							<td title="phone"></td>
						</tr>
						<tr>
							<td>Hình thức giao hàng: </td>
							<td title="delivery_id"></td>
						</tr>

					</tbody>
				</table>
            </div>
            </div>
        <div>
        	<table>
        		<thead>
        			<tr>
                        <th>STT</th>
                        <th>Mã sản phẩm</th>
                        <th>Tên sản phẩm</th>
                        <th>Số lượng</th>
                        <th>Giá tiền</th>
                        <th>Thành tiền</th>
                      </tr>
        		</thead>
        		<tbody class="product">
        			
        		</tbody>
        	</table>
        </div>
        <div class="modal-footer">
            <a class="modal-action modal-close waves-effect waves-green btn-flat">Close</a>
        </div>
    </div>
</section>
<%@include file="layout/footer.jsp"%>
<script src="vendors/sweetalert.min.js"></script>
<script type="text/javascript" src="js/bill.js"></script>
