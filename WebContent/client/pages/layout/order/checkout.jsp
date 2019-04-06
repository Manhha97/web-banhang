<%@ page import="admin.model.OrderDetail" %>
<%@ page import="util.CONSTANT" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="content" class="col-sm-12">
    <div class="breadcrumb clearfix">
        <a class="home" href="/home" title="Return to Home">
            <i class="icon-home"></i>
        </a>
        <span class="navigation-pipe">&gt;</span>
        <span>Giỏ hàng</span>
        <span class="navigation-pipe">&gt;</span>
        <span class="navigation_page">Thanh toán</span>
    </div>
    <div class="panel-group" id="accordion">
        <div class="col-md-7">
            <div class="type_ship">

                <h3>1.Địa chỉ giao hàng</h3>
                        <%
                            OrderDetail detail = (OrderDetail) request.getAttribute("addressDetail");
                            if(detail != null){
                        %>
                <div class="panel panel-default">
                    <div class="panel-body address-detail">
                        <p class="name"><%=detail.getName()%></p>
                        <p class="address" title="">
                            Giao từ: <%=CONSTANT.FS_ADDRESS%>            </p>
                        <p class="address" title="">
                            Giao tới: <%=detail.getAddress()%>            </p>
                        <p class="address"></p>
                        <p class="phone">Điện thoại: <%=detail.getPhone()%></p>
                        <p class="phone">Phí giao hàng: <%=detail.getDistance() * CONSTANT.PRICE_PER_KM%> &nbsp;₫</p>
                        <p class="phone">Km: <%=detail.getDistance()%> &nbsp;km</p>
                        <p class="phone">Đơn giá: <%=CONSTANT.PRICE_PER_KM%> &nbsp;₫</p>
                        <p class="action">
                            <button type="button" class="btn btn-default btn-custom3 save-order">
                                Xác nhận đơn hàng (Đặt mua)
                            </button>
                            <button type="button" class="btn btn-default btn-custom1 edit-address">Sửa</button>
                        </p>
                    </div>
                </div>
                <div class="panel panel-default edit-order-detail" style="display: none;">
                    <div class="panel-body">
                        <form class="form-horizontal bv-form" action="/order?action=edit-order-detail" role="form" id="address-info" novalidate="novalidate">
                            <div class="form-group row">
                                <label for="full_name1" class="col-md-3">Họ tên </label>
                                <div class="col-md-9">
                                    <input required type="text" name="name" class="form-control" id="full_name1" value="<%=detail.getName()%>" placeholder="Nhập họ tên">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="phone1" class="col-md-3">Số điện thoại</label>
                                <div class="col-md-9">
                                    <input required type="text" name="phone" class="form-control" id="phone1" value="<%=detail.getPhone()%>" placeholder="Nhập số điện thoại">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="address" class="col-md-3">Địa chỉ </label>
                                <div class="col-md-9">
                                    <input required type="text" name="address" class="form-control" id="address" value="<%=detail.getAddress()%>" placeholder="Nhập địa chỉ">
                                    <input type="hidden" id="destination" value="<%=detail.getAddress()%>" name="destination">
                                    <div id="result"></div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="type_ship" class="col-md-3">Hình thức giao hàng </label>
                                <div class="col-md-9">
                                    <%if(detail.getDeliveryId()==1){%>
                                    <select  class="form-control" name="deliveryId" id="type_ship">
                                        <option value="1">Giao hàng tiêu chuẩn</option>
                                        <option value="2">Giao hàng nhanh</option>
                                    </select>
                                    <%}else{%>
                                    <select  class="form-control" name="deliveryId" id="type_ship">
                                        <option value="2">Giao hàng nhanh</option>
                                        <option value="1">Giao hàng tiêu chuẩn</option>
                                    </select>
                                    <%}%>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-3"></label>
                                <div class="col-md-9">
                                    <i style="font-size: 11px;">Để nhận hàng thuận tiện hơn, bạn vui lòng chọn hình thức giao hàng.</i>
                                </div>
                            </div>
                            <input type="hidden" name="distance" value="<%=detail.getDistance()%>">
                            <div class="form-group row">
                                <div class="col-lg-offset-3 col-md-9">
                                    <button type="button" class="btn btn-default btn-custom2 cancel-edit-address">Hủy bỏ</button>
                                    <button type="submit" class="btn btn-primary btn-custom3">Cập nhật</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                        <%
                            }else{
                        %>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <%
                            String url = "/order?action=insert-order-detail";
                            if(detail!=null){
                                url = "/order?action=edit-order-detail";
                            }
                        %>
                        <form class="form-horizontal bv-form" action="<%=url%>" role="form" id="address-info" novalidate="novalidate">
                            <div class="form-group row">
                                <label for="full_name" class="col-md-3">Họ tên </label>
                                <div class="col-md-9">
                                    <input required type="text" name="name" class="form-control" id="full_name" value="" placeholder="Nhập họ tên">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="phone" class="col-md-3">Số điện thoại</label>
                                <div class="col-md-9">
                                    <input required type="text" name="phone" class="form-control" id="phone" value="" placeholder="Nhập số điện thoại">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="address" class="col-md-3">Địa chỉ </label>
                                <div class="col-md-9">
                                    <input required type="text" name="address" class="form-control" id="address" value="" placeholder="Nhập địa chỉ">
                                    <input type="hidden" id="destination" name="destination">
                                    <div id="result"></div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="type_ship" class="col-md-3">Hình thức giao hàng </label>
                                <div class="col-md-9">
                                    <select class="form-control" name="deliveryId" id="type_ship">
                                        <option value="1">Giao hàng tiêu chuẩn</option>
                                        <option value="2">Giao hàng nhanh</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-3"></label>
                                <div class="col-md-9">
                                    <i style="font-size: 11px;">Để nhận hàng thuận tiện hơn, bạn vui lòng chọn hình thức giao hàng.</i>
                                </div>
                            </div>
                            <input type="hidden" name="distance" value="">
                            <div class="form-group row">
                                <div class="col-lg-offset-3 col-md-9">
                                    <button type="button" class="btn btn-default btn-custom2 cancel-edit-address">Hủy bỏ</button>
                                    <button type="submit" class="btn btn-primary btn-custom3">Cập nhật</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                        <%
                            }
                        %>

            </div>

        </div>
        <div class="col-md-5">
            <h3>2. Hình thức giao hàng</h3>
            <div class="panel panel-default">
                <div class="panel-body">
                    <div >
                        <div class="form-group row row-style-3">
                            <div class="check-payment col-md-1">
                                <i class="fa fa-check-square-o" aria-hidden="true"></i>
                            </div>
                            <div class="col-lg-11 col-md-11 col-sm-11 col-xs-10">
                                <%
                                    if(detail!=null){
                                        if(detail.getDeliveryId()==1)   {

                                %>
                                <label class="control-label is-large">
                                    Giao hàng tiêu chuẩn <span class=""> <%=detail.getAmount()%> %</span>
                                </label>
                                <%
                                        }else{
                                %>
                                <label class="control-label is-large">
                                    Giao hàng nhanh <span class=""> <%=detail.getAmount()%> %</span>
                                </label>
                                <%
                                        }
                                    }else {
                                %>
                                <label class="control-label is-large">
                                    Giao hàng tiêu chuẩn <span class=""> 5%</span>
                                </label>
                                <%}%>
                            </div>
                        </div>
                    </div>
                    <div >
                        <div class="form-group row row-style-3">
                            <div class="check-payment col-md-1">
                                <i class="fa fa-check-square-o" aria-hidden="true"></i>
                            </div>
                            <div class="col-lg-11 col-md-11 col-sm-11 col-xs-10">
                                <label class="control-label is-large">
                                    Thanh toán tiền mặt khi nhận hàng
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <h3>3.Thông tin đơn hàng</h3>
            <div class="panel panel-default cart">
                <div class="panel-body">
                    <div class="order">
                        <span class="title">Đơn Hàng</span>
                        <span class="title"> (<span class="cart_length"></span> sản phẩm)</span>
                        <a href="/order?action=cart" class="btn btn-default btn-custom1">Sửa</a>
                    </div>
                    <form action="/order?action=save-order" method="post" id="order-detail">
                        <div class="product">


                        </div>

                        <p class="list-info-price">
                            <b>Tạm tính</b>
                            <span>&nbsp;₫</span><span class="total_price1"></span>
                        </p>
                        <p class="list-info-price">
                            <b>Phí vận chuyển</b>
                            <%
                                if(detail!=null){
                            %>
                            <input type="hidden" value="<%=detail.getDistance() * CONSTANT.PRICE_PER_KM%>" name="ship_pay">
                            <span> <%=detail.getDistance() * CONSTANT.PRICE_PER_KM%> &nbsp;₫</span>
                            <%
                                }else {
                            %>
                            <input type="hidden" value="0" name="ship_pay">
                            <span> 0&nbsp;₫</span>
                            <%}%>
                        </p>

                        <p class="total2">
                            Thành tiền:
                            <span>&nbsp;₫ </span><span class="total_price2"></span>
                        </p>
                        <p class="text-right">
                            <i>(Đã bao gồm VAT)</i>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
