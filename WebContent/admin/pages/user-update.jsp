
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="layout/header.jsp"%>
<!-- START CONTENT -->
<section id="content">
    <div class="page">
        <ul>
            <li><a href=""><i class="fa fa-home"></i></a></li>
            <li><a href="">Quản lý nhân viên</a></li>
            <li><a href="">Thông tin nhân viên</a></li>
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
                                <img src="https://tinhte.cdnforo.com/store/2015/11/3552659_cv.jpg" alt="" class="circle z-depth-2 responsive-img activator gradient-45deg-light-blue-cyan">
                                <div class="file-upload">
                                    <a href="#">
                                        <i class="material-icons grey-text text-darken-1">camera_alt</i>
                                        <input type="file" accept="image/*" name="profile">
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
                                        <input id="p_birthday" type="text" name="birthday" required>
                                        <label key="birthday" for="p_birthday">Birthday</label>
                                    </div>
                                    <div class="input-field col s4">
                                        <input id="p_address" type="text" name="address" required>
                                        <label key="address" for="p_address">Address</label>
                                    </div>
                                    <div class="input-field col s4">
                                        <select id="select_role_id" name="role_id" required>
                                        </select>
                                        <label key="role_id" for="select_role_id">Role</label>
                                    </div>

                                    <div class="row">
                                        <div class="input-field col s6">
                                            <input id="p_email" type="email" name="email" required>
                                            <label key="email" for="p_email">Email</label>
                                        </div>
                                        <% if(request.getAttribute("code") == null){ %>
                                        <div class="input-field col s6">
                                            <input id="p_password" type="password" name="password" required>
                                            <label key="password" for="p_password">Password</label>
                                        </div>
                                        <%}%>


                                    </div>

                                </div>
                            </div>
                            <div class="row">
                                <div class="row" style="text-align: center;">
                                    <div class="input-field col s12">
                                        <a class="waves-effect waves-light button-design" href="/admin/user">
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
<script type="text/javascript" src="js/user.js"></script>
<script src="js/user-update.js"></script>
