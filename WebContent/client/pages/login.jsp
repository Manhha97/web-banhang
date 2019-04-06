
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="layout/header-footer/header.jsp"%>
<div class="columns-container">
    <div id="columns" class="container">
        <div class="inner_container">
            <div class="inner_container_sub">
                <div class="row" id="columns_inner">
                    <form class="content bv-form login-form" method="POST" action="">
                        <input type="hidden" disabled name="next_page" value="${next_page}">
                        <div class="form-group">
                            <label class="">Email</label>
                            <input type="email" class="form-control login" name="email" placeholder="Nhập email" required>
                        </div>
                        <div class="form-group">
                            <label class="">Mật khẩu</label>
                            <input type="password" class="form-control login" name="password" required placeholder="Nhập mật khẩu" autocomplete="off">
                        </div>
                        <a class="toggle-form" href="">Đăng ký tài khoản</a>
                        <p style="color: red;font-style: italic;" class="login-error"></p>
                        <div class="form-group last">
                            <button type="submit" class="btn btn-info btn-block">Đăng nhập</button>
                        </div>
                        <div class="form-group last" style="font-size: 20px;">
                            <div
							  class="g-signin2" onclick="ClickLogin()"
							  data-onsuccess="onSignIn">
							</div>
							<div onclick="loginfb();">login fb</div>
                        </div>
                        
                    </form>
                    <form class="content bv-form register-form" method="POST" action="" style="display: none">
                        <div class="form-group">
                            <label class="">Họ tên</label>
                            <input type="text" class="form-control login" name="name" placeholder="Nhập tên" required>
                        </div>
                        <div class="form-group">
                            <label class="">Email</label>
                            <input type="email" class="form-control login" name="email" placeholder="Nhập email" required>
                        </div>
                        <div class="form-group">
                            <label class="">Số điện thoại</label>
                            <input type="text" class="form-control login" name="phone" placeholder="Nhập số điện thoại" required>
                        </div>
                        <div class="form-group">
                            <label class="">Mật khẩu</label>
                            <input type="password" class="form-control login" pattern="(?=^.{8,}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" name="password" required placeholder="Nhập mật khẩu" autocomplete="off">
                        </div>
                       <!--  <a class="toggle-form" href="">Đã có tài khoản</a> -->
                        <p style="color: red;font-style: italic;" class="login-error"></p>
                        <div class="form-group last">
                            <button type="submit" class="btn btn-info btn-block">Đăng ký</button>
                        </div>
                    </form>
                    
                    
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="layout/header-footer/footer.jsp"%>


<script !src="">
    $(document).ready(function () {
    	
    	
        $('.toggle-form').click(function (e) {
            e.preventDefault();
           $(this).parents('form').siblings().show();
           $(this).parents('form').hide();
        });
        $(document).on('submit','.login-form', function (e) {
            e.preventDefault();
            $.ajax({
                url :'/customer?action=login',
                type : 'post',
                data : $(this).serialize(),
                cache : false,
                success : function (res) {
                    if(res === true || res === 'true'){
                        $('.login-error').html('');
                        var next_page = $('input[name="next_page"]').val();
                        if(next_page === null || next_page === ''){
                            next_page = '/home';
                        }
                        location.href = next_page;
                    }else{
                        $('.login-error').html('Sai thông tin đăng nhập!');
                    }
                }
            })
        })
        $(document).on('submit','.register-form', function (e) {
            e.preventDefault();
            $.ajax({
                url :'/customer?action=register',
                type : 'post',
                data : $(this).serialize(),
                cache : false,
                success : function (res) {
                    if(res === true || res === 'true'){
                        $('.login-error').html('');
                        location.href = '/home';
                    }else{
                        $('.login-error').html('Thông tin đã tôn tại!');
                    }
                }
            })
        })
    })
</script>