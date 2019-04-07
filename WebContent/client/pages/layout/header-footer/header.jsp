<%@ page import="admin.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Book store - FS.com</title>
    <meta name="description" content="Shop powered by PrestaShop"/>
    <meta name="generator" content="PrestaShop"/>
    <meta name="robots" content="index,follow"/>
    <meta name="viewport" content="width=device-width, minimum-scale=0.25, maximum-scale=1.6, initial-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="google-signin-client_id" content="443655797689-h19b37c1dpbcbuoecddeuvlpf9pi1jgl.apps.googleusercontent.com">

	
    <link rel="icon" type="image/vnd.microsoft.icon" href="client/img/icon/favicon.ico"/>
    <link rel="shortcut icon" type="image/x-icon" href="client/img/icon/favicon.ico"/>
    <link rel="stylesheet" href="vendors/bootstrap/css/bootstrap.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="vendors/bootstrap/css/bootstrap-theme.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="client/css/global.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="client/css/uniform.default.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="client/css/product.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="client/css/product-list.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="client/css/blockcart.css" type="text/css" media="all"/>


    <link rel="stylesheet" href="client/css/blockcategories.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="vendors/jquery/css/jquery.ui.core.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="vendors/jquery/css/jquery.ui.slider.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="vendors/jquery/css/jquery.ui.theme.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="client/css/blocklayerd.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="client/css/superfish-modified.css" type="text/css" media="all"/>
    <link rel="stylesheet" type="text/css" href="client/css/custom.css"/>
    <link rel="stylesheet" type="text/css" href="vendors/font-awesome-4.6.3/css/font-awesome.css"/>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,700" type="text/css" media="all"/>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Rokkitt:400,700" type="text/css" media="all"/>

    <link rel="stylesheet" href="client/vendors/css/owl.carousel.css">
</head>
<body id="index" class="index hide-right-column lang_en">
<div class="top-banner">
    <img src="client/img/Xmas.png" alt="">
</div>
<div id="page">
    <div class="header-container">
        <header id="header">
            <div class="banner">
                <div class="container">
                    <div class="row"></div>
                </div>
            </div>
            <div>
                <div class="container">
                    <div class="row">
                        <div id="header_logo"><a href="/home" title="Book Store">
                            <img class="logo img-responsive" src="client/img/logo.png" alt="Demo Store" width="239" height="64"/> </a>
                        </div>
                        <div id="search_block_top" class="col-sm-4 clearfix">
                            <div id="search_block_top_wrap">
                                <form id="searchbox" method="get" action="">
                                    <input class="search_query form-control" type="text"
                                            name="search_query" placeholder="Tìm kiếm..." value=""/>
                                    <button type="button" name="submit_search" class="btn btn-default button-search">
                                        <span>Search</span></button>
                                </form>
                            </div>
                        </div>

                        <div id="languages-block-top" class="languages-block">
                            <select name="language" >
                                <option value="vn">Việt Nam</option>
                                <option value="en">English</option>
                            </select>
                        </div>
                        <div class="customer-login">
                            <i class="fa fa-heart-o" aria-hidden="true"></i>
                            <a href="/product?action=wish-list"><span class="lang" key="wish-list"></span> <span id="count-wish-list"></span></a>
                        </div>
                        <%
                            Customer cus = (Customer) session.getAttribute("currentCustomer");
                            if(cus == null){
                        %>
                        <div class="customer-login">
                            <i class="fa fa-sign-in" aria-hidden="true"></i>
                            <a href="/customer?action=login"><span class="lang" key="login"></span></a>
                        </div>
                        <%
                            }else {
                        %>
                        <div class="customer-login dropdown">
                            <i class="fa fa-user" aria-hidden="true"></i>
                            <a href="" class="dropdown-toggle"><%=cus.getName()%></a>
                            <ul class="dropdown-menu">
                                <li><a href="/customer?action=edit-info"><span class="lang" key="info"></span></a></li>
                                <li><a  href="javascript:void(0)" <% if(cus.getType() == "google") {out.print("onclick='googleLogout()'");}else if(cus.getType() == "facebook"){out.print("onclick='fbLogout()'");}else{out.print("onclick='simpleLogout()'");} %> ><span class="lang" key="logout"></span></a></li>
                            </ul>

                        </div>
                        <%
                            }
                        %>
                        <div class="col-sm-3 clearfix block_cart_header">
                            <div class="shopping_cart">
                                <a href="/order?action=cart" title="View my shopping cart" rel="nofollow">
                                    <span class="lang" key="cart"></span> ( <span class="cart_length">0</span> )
                                </a>
                                <div class="cart_block block exclusive">
                                    <div class="block_content">
                                        <div class="cart_block_list">
                                            <ul class="cart_list">

                                            </ul>
                                            <p class="cart_block_no_products unvisible"> No products</p>
                                            <div class="cart-prices">
                                                <div class="cart-prices-line first-line">
                                                </div>
                                                <div class="cart-prices-line last-line"><span
                                                        class="price cart_block_total ajax_block_cart_total">$475.46</span>
                                                    <span>Total</span></div>
                                            </div>
                                            <p class="cart-buttons">
                                                <a id="button_order_cart" class="btn btn-default button button-small" href="/order?action=cart"
                                                                       title="Check out" rel="nofollow"> <span> Check out
                                                    <i class="icon-chevron-right right"></i> </span> </a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    </div>
