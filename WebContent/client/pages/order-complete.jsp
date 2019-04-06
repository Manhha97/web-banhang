<%@ page import="java.util.List" %>
<%@ page import="admin.model.BillDetail" %>
<%@ page import="admin.model.OrderDetail" %>
<%@ page import="util.CONSTANT" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- header --%>
<%@include file="layout/header-footer/header.jsp"%>
<%-- main --%>
<div class="columns-container">
    <div id="columns" class="container">
        <div class="inner_container">
            <div class="inner_container_sub">
                <div class="row" id="columns_inner">
                    <div class="col-md-offset-2 col-md-10">
                        <table>
                            <tbody>
                            <tr>
                                <td>
                                    <h1 style="font-size:17px;font-weight:bold;color:#444;padding:0 0 5px 0;margin:0">
                                        Cảm ơn quý khách ${currentCustomer.name} đã đặt hàng tại FS,</h1>
                                    <p style="margin:4px 0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal">
                                        FS rất vui thông báo đơn hàng ${bill.code} của quý khách đã được tiếp nhận và đang trong quá trình xử lý. FS sẽ thông
                                        báo đến quý khách ngay khi hàng chuẩn bị được giao.
                                    </p>
                                    <h3 style="font-size:13px;font-weight:bold;color:#02acea;text-transform:uppercase;margin:20px 0 0 0;border-bottom:1px solid #ddd">
                                        Thông tin đơn hàng ${bill.code}
                                        <span style="font-size:12px;color:#777;text-transform:none;font-weight:normal">(Ngày ${bill.createAt})</span>
                                    </h3>
                                </td>
                            </tr>

                            <tr id="order-info">
                                <td style="font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px">
                                    <table cellspacing="0" cellpadding="0" border="0" width="100%">
                                        <thead>
                                        <tr>
                                            <th align="left" width="50%" style="padding:6px 9px 0px 9px;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;font-weight:bold">
                                                Thông tin thanh toán
                                            </th>
                                            <th align="left" width="50%" style="padding:6px 9px 0px 9px;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;font-weight:bold">
                                                Địa chỉ giao hàng                                                             </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            OrderDetail orderDetail = (OrderDetail) request.getAttribute("orderDetail");
                                        %>
                                        <tr>
                                            <td valign="top" style="padding:3px 9px 9px 9px;border-top:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal">
                                                <span style="text-transform:capitalize"><%=orderDetail.getName()%></span>
                                                <br> <a href="mailto:ngothuylinhptit@gmail.com" target="_blank">${currentCustomer.email}</a>
                                                <br> <%=orderDetail.getPhone()%>
                                            </td>

                                            <td valign="top" style="padding:3px 9px 9px 9px;border-top:0;border-left:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal">
                                                                <span style="text-transform:capitalize"><%=orderDetail.getName()%></span>
                                                <br>  <a href="mailto:ngothuylinhptit@gmail.com" target="_blank">${currentCustomer.email}</a>
                                                <br> <%=orderDetail.getAddress()%>
                                                <br>  T: <%=orderDetail.getPhone()%>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top" style="padding:7px 9px 0px 9px;border-top:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444" colspan="2">
                                                <p style="font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal">

                                                    <strong>Phương thức thanh toán: </strong>
                                                    Thanh toán tiền mặt khi nhận hàng
                                                    <br>                                                                      <strong>Thời gian giao hàng dự kiến:</strong>
                                                    dự kiến giao hàng vào Thứ tư, 30/01/2019 - không giao ngày lễ                                                                      <br>
                                                    <strong>Phí vận chuyển: </strong>
                                                    <%=orderDetail.getAmount()* CONSTANT.PRICE_PER_KM%> &nbsp;₫
                                                    <br>
                                                </p>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            <tr id="order-detail">
                                <td>
                                    <h2 style="text-align:left;margin:10px 0;border-bottom:1px solid #ddd;padding-bottom:5px;font-size:13px;color:#02acea">
                                        CHI TIẾT ĐƠN HÀNG</h2>

                                    <table cellspacing="0" cellpadding="0" border="0" width="100%" style="background:#f5f5f5">
                                        <thead>
                                        <tr>
                                            <th align="left" bgcolor="#02acea" style="padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px">Sản phẩm</th>
                                            <th align="left" bgcolor="#02acea" style="padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px">
                                                Đơn giá</th>
                                            <th align="left" bgcolor="#02acea" style="padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px">Số lượng</th>
                                            <th align="left" bgcolor="#02acea" style="padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px">Giảm giá</th>
                                            <th align="right" bgcolor="#02acea" style="padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px">Tổng tạm</th>
                                        </tr>
                                        </thead>
                                        <tbody bgcolor="#eee" style="font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px">
                                        <%
                                            List<BillDetail> list = (List<BillDetail>) request.getAttribute("billDetailList");
                                            Double total = 0d;
                                            for(BillDetail bd : list){
                                                total+= bd.getProduct().getPrice() * bd.getQuantity();
                                        %>
                                        <tr>
                                            <td align="left" valign="top" style="padding:3px 9px">
                                                <span class=""><%=bd.getProduct().getName()%></span>
                                                <br>                                                             </td>
                                            <td align="left" valign="top" style="padding:3px 9px">
                                                <span><%=bd.getProduct().getPrice()%>&nbsp;₫</span>
                                            </td>
                                            <td align="left" valign="top" style="padding:3px 9px">2</td>
                                            <td align="left" valign="top" style="padding:3px 9px">
                                                <span>0&nbsp;₫</span>
                                            </td>
                                            <td align="right" valign="top" style="padding:3px 9px">
                                                <span><%=bd.getProduct().getPrice() * bd.getQuantity()%>&nbsp;₫</span>
                                            </td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                        <tfoot style="font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px">
                                        <tr>
                                            <td colspan="4" align="right" style="padding:5px 9px">Tổng tạm tính</td>
                                            <td align="right" style="padding:5px 9px">
                                                <span><%=total%>&nbsp;₫</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" align="right" style="padding:5px 9px">Phí vận chuyển</td>
                                            <td align="right" style="padding:5px 9px">
                                                <span><%=orderDetail.getAmount()* CONSTANT.PRICE_PER_KM%>&nbsp;₫</span>
                                            </td>
                                        </tr>

                                        <tr bgcolor="#eee">
                                            <td colspan="4" align="right" style="padding:7px 9px">
                                                <strong>
                                                    <big>Tổng giá trị đơn hàng</big>
                                                </strong>
                                            </td>
                                            <td align="right" style="padding:7px 9px">
                                                <strong>
                                                    <big>
                                                        <span><%=total+orderDetail.getAmount()* CONSTANT.PRICE_PER_KM%>&nbsp;₫</span>
                                                    </big>
                                                </strong>
                                            </td>
                                        </tr>
                                        </tfoot>
                                    </table>
                                    <div style="margin:auto">
                                        <a id="printOrder" href="#" style="display:inline-block;text-decoration:none;background-color:#00b7f1!important;margin-right:30px;text-align:center;border-radius:3px;color:#fff;padding:5px 10px;font-size:12px;font-weight:bold;margin-left:35%;margin-top:5px" target="_blank" >In đơn hàng (pdf)</a>
                                        <a id="sendOrder" href="#" style="display:inline-block;text-decoration:none;background-color:#00b7f1!important;margin-right:30px;text-align:center;border-radius:3px;color:#fff;padding:5px 10px;font-size:12px;font-weight:bold;margin-left:35%;margin-top:5px" target="_blank" >Gửi về email</a>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <br>

                                    <p style="font-family:Arial,Helvetica,sans-serif;font-size:12px;margin:0;padding:0;line-height:18px;color:#444;font-weight:bold">
                                        Một lần nữa FS cảm ơn quý khách.
                                        <br>

                                    </p>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="layout/header-footer/footer.jsp"%>
<script !src="">
    $(document).ready(function () {
        $(document).on('click','#printOrder', function (e) {
            e.preventDefault();
            var h1 = $('#order-info').html();
            var h2 = h1 + $('#order-detail').html();
            newWin = window.open("");
            newWin.document.write(h2);
            newWin.print();
            newWin.close();
        })
        $(document).on('click', '#sendOrder', function (e) {
            e.preventDefault();
            $.ajax({
                url : '/order?action=send-order-mail',
                type : 'post',
                success : function (res) {
                    if(res===true||res==='true'){
                        alert("Xong!")
                    }
                }
            })
        })
    });
</script>
