<%@ page import="java.util.List" %>
<%@ page import="admin.model.Bill" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- header --%>
<%@include file="layout/header-footer/header.jsp"%>
<%-- main --%>
<div class="columns-container">
    <div id="columns" class="container">
        <div class="inner_container">
            <div class="inner_container_sub">
                <div class="row" id="columns_inner">
                    <div id="content" class="col-sm-12">
                        <div class="breadcrumb clearfix">
                            <a class="home" href="/home" title="Return to Home">
                                <i class="icon-home"></i>
                            </a>
                            <span class="navigation-pipe">&gt;</span>
                            <span class="navigation_page">Thông tin cá nhân</span>
                        </div>
                        <div class="panel-group" id="edit-info">
                            <div class="col-md-3">
                                <div class="menu-left">
                                    <div class="profiles">
                                        <p class="image"><img src="/client/img/avatar.png" height="45" width="45" alt=""></p>
                                        <p class="name">Tài khoản của</p>
                                        <h6>${currentCustomer.name}</h6>
                                    </div>
                                    <%
                                        String action = request.getParameter("action");
                                    %>
                                    <div class="menu">
                                        <ul >
                                            <li class="<%if (action.equalsIgnoreCase("edit-info")){%>active <%}%>">
                                                <a href="/customer?action=edit-info"><i class="fa fa-user" aria-hidden="true"></i><span>Thông tin tài khoản</span></a>
                                            </li>
                                            <li class="<%if (action.equalsIgnoreCase("my-order")){%>active <%}%>">
                                                <a href="/customer?action=my-order"><i class="fa fa-list-alt" aria-hidden="true"></i> <span>Đơn hàng của tôi</span> </a>
                                            </li>

                                        </ul>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-9">
                                <%
                                    if(action.equalsIgnoreCase("edit-info")){
                                %>
                                <div class="account-profile register-form">
                                    <form class="content" method="post" action="/customer?action=edit-info" id="edit-account">
                                        <div class="form-group">
                                            <label class="control-label" for="full_name">Họ tên </label>
                                            <div class="input-wrap">
                                                <input type="text" name="name" required class="form-control" id="full_name" value="${currentCustomer.name}" placeholder="Họ tên">
                                                <span class="help-block"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="phone_number">Số điện thoại</label>
                                            <div class="input-wrap">
                                                <input type="text" disabled="" value="${currentCustomer.phone}" class="form-control" name="phone_number" id="phone_number" placeholder="Số điện thoại">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="form_email">Email</label>
                                            <div class="input-wrap">
                                                <input  type="email" disabled="" value="${currentCustomer.email}" class="form-control" name="email" id="form_email" placeholder="Email">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="">
                                                <label><input style="margin-right: 5px;" type="checkbox" name="is_change_pass" value="checked">Thay đổi mật khẩu</label>
                                            </div>
                                        </div>
                                        <div class="password-group" style="display: none;">
                                            <div class="form-group">
                                                <label class="control-label" for="old_password">Mật khẩu cũ</label>
                                                <div class="input-wrap">
                                                    <input type="password" disabled required name="old_password" class="form-control" id="old_password" value="" autocomplete="off" placeholder="Nhập mật khẩu cũ">
                                                    <span class="pass-wrong"></span>
                                                </div>

                                            </div>
                                            <div class="form-group">
                                                <label class="control-label" for="new_password">Mật khẩu mới</label>
                                                <div class="input-wrap">
                                                    <input type="password" disabled required minlength="8" pattern="(?=^.{8,}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" name="new_password" class="form-control" id="new_password" value="" autocomplete="off" placeholder="Mật khẩu từ 8 ký tự">
                                                    <span class="help-block"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-wrap margin">
                                                <button type="submit" class="btn btn-info btn-block btn-custom3">Cập nhật</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <%
                                    }else if (action.equalsIgnoreCase("my-order")){
                                        List<Bill> bills = (List<Bill>) request.getAttribute("bills");
                                %>
                                <%----%>
                                    <ul class="list-group">
                                        <%
                                            for (Bill b: bills){
                                        %>
                                        <li class="list-group-item">
                                            <a href="/order?action=complete&code=<%=b.getCode()%>">Mã hóa đơn: <%=b.getCode()%></a>
                                            <a href="/order?action=complete&code=<%=b.getCode()%>">Ngày tạo: <%=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(b.getCreateAt())%></a>

                                        </li>
                                        <%}%>
                                    </ul>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="layout/header-footer/footer.jsp"%>
<script !src="">
    $(document).ready(function () {
        $(document).on('click','input[name="is_change_pass"]', function () {
            if($(this).is(':checked')){
                $('.password-group').show();
                $('.password-group input').prop('disabled', false);
            }else{
                $('.password-group').hide();
                $('.password-group input').prop('disabled', true);
            }
        })
        $(document).on('submit','#edit-account', function (e) {
            e.preventDefault();
            $.ajax({
                url : $(this).attr('action'),
                type : $(this).attr('method'),
                data : $(this).serialize(),
                cache : false,
                success : function (res) {
                    if(res===true||res==='true'){
                        location.reload();
                    }else {
                        $('.pass-wrong').html('Sai mật khẩu!')
                    }
                }
            })
        })
    })
</script>