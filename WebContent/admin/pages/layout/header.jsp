    <%@ page import="admin.model.User" %><%--
  Created by IntelliJ IDEA.
  User: HuyChu
  Date: 11/29/2018
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="msapplication-tap-highlight" content="no">
        <meta name="description" content="Materialize is a Material Design Admin Template,It's modern, responsive and based on Material Design by Google. ">
        <meta name="keywords" content="materialize, admin template, dashboard template, flat admin template, responsive admin template,">
        <title>Quản lý nhân viên</title>
        <!-- Favicons-->
        <link rel="icon" href="images/favicon-32x32.png" sizes="32x32">
        <!-- Favicons-->
        <link rel="apple-touch-icon-precomposed" href="images/apple-touch-icon-152x152.png">
        <!-- For iPhone -->
        <meta name="msapplication-TileColor" content="#00bcd4">
        <meta name="msapplication-TileImage" content="images/mstile-144x144.png">
        <!-- For Windows Phone -->
        <!-- CORE CSS-->
        <link href="css//materialize.css" type="text/css" rel="stylesheet">
        <link href="css//style.css" type="text/css" rel="stylesheet">
        <!-- Custome CSS-->
        <link href="css/custom.css" type="text/css" rel="stylesheet">
        <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
        <link rel="stylesheet" type="text/css" href="../vendors/font-awesome-4.6.3/css/font-awesome.css">
        <link href="vendors/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet">
    </head>
    <body>
    <!-- Start Page Loading -->
        <div id="loader-wrapper">
            <div id="loader"></div>
            <div class="loader-section section-left"></div>
            <div class="loader-section section-right"></div>
        </div>
    <!-- End Page Loading -->
    <!-- //////////////////////////////////////////////////////////////////////////// -->
    <!-- START HEADER -->
        <header id="header" class="page-topbar">
    <!-- start header nav-->
            <div class="navbar-fixed">
                <nav class="navbar-color gradient-45deg-light-blue-cyan">
                    <div class="nav-wrapper">
                        <ul class="left">
                            <li>
                                <h1 class="logo-wrapper">
                                    <a href="index.html" class="brand-logo darken-1">
                                        <img src="images/materialize-logo.png" alt="materialize logo">
                                        <span class="logo-text hide-on-med-and-down">Materialize</span>
                                    </a>
                                </h1>
                            </li>
                        </ul>
                        <div class="header-search-wrapper hide-on-med-and-down">
                            <i class="material-icons">search</i>
                            <input type="text" name="Search" class="header-search-input z-depth-2" placeholder="Explore Materialize" />
                        </div>
                        <ul class="right hide-on-med-and-down">
                            <li>
                                <a href="javascript:void(0);" class="waves-effect waves-block waves-light translation-button" data-activates="translation-dropdown">
                                    <span class="flag-icon flag-icon-gb"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" class="waves-effect waves-block waves-light toggle-fullscreen">
                                    <i class="material-icons">settings_overscan</i>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" class="waves-effect waves-block waves-light notification-button" data-activates="notifications-dropdown">
                                    <i class="material-icons">notifications_none
                                        <small class="notification-badge pink accent-2">5</small>
                                    </i>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" class="waves-effect waves-block waves-light profile-button" data-activates="profile-dropdown">
                                    <span class="avatar-status avatar-online">
                                        <img src="images/avatar-7.png" alt="avatar">
                                        <i></i>
                                    </span>
                                </a>
                            </li>

                        </ul>
    <!-- translation-button -->
                        <ul id="translation-dropdown" class="dropdown-content">
                            <li>
                                <a href="#!" class="grey-text text-darken-1">
                                    <i class="flag-icon flag-icon-gb"></i> English
                                </a>
                            </li>
                            <li>
                                <a href="#!" class="grey-text text-darken-1">
                                    <i class="flag-icon flag-icon-fr"></i> French
                                </a>
                            </li>
                            <li>
                                <a href="#!" class="grey-text text-darken-1">
                                    <i class="flag-icon flag-icon-cn"></i> Chinese
                                </a>
                            </li>
                            <li>
                                <a href="#!" class="grey-text text-darken-1">
                                    <i class="flag-icon flag-icon-de"></i> German
                                </a>
                            </li>
                        </ul>
    <!-- notifications-dropdown -->
                        <ul id="notifications-dropdown" class="dropdown-content">
                            <li>
                                <h6>NOTIFICATIONS
                                <span class="new badge">5</span>
                                </h6>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#!" class="grey-text text-darken-2">
                                <span class="material-icons icon-bg-circle cyan small">add_shopping_cart</span> A new order has been placed!</a>
                                <time class="media-meta" datetime="2015-06-12T20:50:48+08:00">2 hours ago</time>
                            </li>
                            <li>
                                <a href="#!" class="grey-text text-darken-2">
                                <span class="material-icons icon-bg-circle red small">stars</span> Completed the task</a>
                                <time class="media-meta" datetime="2015-06-12T20:50:48+08:00">3 days ago</time>
                            </li>
                            <li>
                                <a href="#!" class="grey-text text-darken-2">
                                <span class="material-icons icon-bg-circle teal small">settings</span> Settings updated</a>
                                <time class="media-meta" datetime="2015-06-12T20:50:48+08:00">4 days ago</time>
                            </li>
                            <li>
                                <a href="#!" class="grey-text text-darken-2">
                                <span class="material-icons icon-bg-circle deep-orange small">today</span> Director meeting started</a>
                                <time class="media-meta" datetime="2015-06-12T20:50:48+08:00">6 days ago</time>
                            </li>
                            <li>
                                <a href="#!" class="grey-text text-darken-2">
                                <span class="material-icons icon-bg-circle amber small">trending_up</span> Generate monthly report</a>
                                <time class="media-meta" datetime="2015-06-12T20:50:48+08:00">1 week ago</time>
                            </li>
                        </ul>
    <!-- profile-dropdown -->
                        <ul id="profile-dropdown" class="dropdown-content">
            <li>
                <a href="#" class="grey-text text-darken-1">
                <i class="material-icons">face</i> Profile</a>
            </li>
            <li>
                <a href="#" class="grey-text text-darken-1">
                <i class="material-icons">settings</i> Settings</a>
            </li>
            <li>
                <a href="#" class="grey-text text-darken-1">
                <i class="material-icons">live_help</i> Help</a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#" class="grey-text text-darken-1">
                <i class="material-icons">lock_outline</i> Lock</a>
            </li>
            <li>
                <a href="/admin/login?action=logout" class="grey-text text-darken-1">
                <i class="material-icons">keyboard_tab</i> Logout</a>
            </li>
        </ul>
                    </div>
                </nav>
            </div>
    <!-- end header nav-->
        </header>
    <!-- END HEADER -->
    <!-- //////////////////////////////////////////////////////////////////////////// -->
    <!-- START MAIN -->
        <div id="main">
    <!-- START WRAPPER -->
            <div class="wrapper">
    <!-- START LEFT SIDEBAR NAV-->
                <aside id="left-sidebar-nav">
                        <%
                            User cuser = (User) session.getAttribute("currentUser");
                        %>
        <ul id="slide-out" class="side-nav fixed leftside-navigation">
            <li class="user-details cyan darken-2">
                <div class="row">
                    <div class="col col s4 m4 l4">
                        <img src="/admin/file?action=image&name=<%=cuser.getProfile()%>" style="width: 52px;height: 52px;" alt="" class="circle responsive-img valign profile-image cyan">
                    </div>
                    <div class="col col s8 m8 l8">
                        <ul id="profile-dropdown-nav" class="dropdown-content">
                        <li>
                            <a href="#" class="grey-text text-darken-1">
                            <i class="material-icons">face</i> Profile</a>
                        </li>
                        <li>
                            <a href="#" class="grey-text text-darken-1">
                            <i class="material-icons">settings</i> Settings</a>
                        </li>
                        <li>
                            <a href="#" class="grey-text text-darken-1">
                            <i class="material-icons">live_help</i> Help</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#" class="grey-text text-darken-1">
                            <i class="material-icons">lock_outline</i> Lock</a>
                        </li>
                        <li>
                            <a href="/admin/login?action=logout" class="grey-text text-darken-1">
                            <i class="material-icons">keyboard_tab</i> Logout</a>
                        </li>
                    </ul>

                        <a class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn" href="#" data-activates="profile-dropdown-nav"><%=cuser.getName()%><i class="mdi-navigation-arrow-drop-down right"></i></a>
                        <% if (cuser.getRole_id()==1){%>
                        <p class="user-roal">ADMIN</p>
                        <%}else if (cuser.getRole_id()==2){%>
                        <p class="user-roal">EMPLOYEE</p>
                        <%}else {%>
                        <p class="user-roal">BOSS</p>
                        <%}%>
                    </div>
                </div>
            </li>
            <li class="no-padding">
                <ul class="collapsible" data-collapsible="accordion">
                    <li class="bold" title="home">
                        <a href="/admin/home" class="waves-effect waves-cyan">
                            <i class="material-icons">timeline</i>
                            <span class="nav-text">Thống kê</span>
                        </a>
                    </li>
                    <%
                        User userc = (User) session.getAttribute("currentUser");
                        if (userc.getRole_id()!=2){
                    %>
                    <li class="bold" title="user">
                        <a href="/admin/user" class="waves-effect waves-cyan">
                            <i class="material-icons">group</i>
                        <span class="nav-text">Quản lý nhân viên</span>
                        </a>
                    </li>
                    <%}%>
                    <li class="bold" title="product">
                        <a href="/admin/product" class="waves-effect waves-cyan">
                            <i class="material-icons">cast</i>
                            <span class="nav-text">Quản lý sản phẩm</span>
                        </a>
                    </li>
                    <li class="bold" title="bill">
                        <a href="/admin/bill" class="waves-effect waves-cyan">
                            <i class="material-icons">account_circle</i>
                            <span class="nav-text">Thống kê hóa đơn</span>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
        <a href="#" data-activates="slide-out" class="sidebar-collapse btn-floating btn-medium waves-effect waves-light hide-on-large-only">
            <i class="material-icons">menu</i>
        </a>
    </aside>
    <!-- END LEFT SIDEBAR NAV-->