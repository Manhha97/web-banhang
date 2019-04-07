<%@page import="admin.model.Customer"%>
<%@ page import="admin.model.Product" %>
<%@ page import="admin.dto.ProductDetail" %>
<%@ page import="java.util.List" %>
<%@ page import="admin.model.Comment" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% ProductDetail p = (ProductDetail) request.getAttribute("product"); %>

<div id="center_column" class="center_column col-xs-12">
    <div class="clearfix breadcrumb1">
        <a href="/home" title="Return to Home">
            Trang chủ
        </a>
        <span> > </span>
        <a href="/" title="<%=p.getName()%>"><%=p.getName()%></a>
    </div>
    <div class="primary_block row" >
        <div class="container">
            <div class="top-hr"></div>
        </div>
        <div class="pb-left-column col-xs-12 col-sm-5 col-md-5">
            <div id="image-block" class="clearfix">
                <span class="new-box"> <span class="new-label">New</span> </span>
                <span class="sale-box no-print">
                    <span class="sale-label">Sale!</span>
                </span>
                <span id="view_full_size">
                    <img id="bigpic" src="admin/file?action=image&name=<%=p.getImage()%>"
                         title="Aliquam erat volutpat " alt="Aliquam erat volutpat "
                         width="458" height="458"/>
                </span>
            </div>

        </div>
        <div class="pb-center-column col-xs-12 col-sm-7 col-md-7 ">
            <p class="online_only"> </p>
            <h1 class="p-name" itemprop="name"><%=p.getName()%></h1>
            <p id="product_condition"><label>Condition </label> <span class="editable" itemprop="condition"> New </span>
            </p>
            <div id="short_description_block">
                <div id="short_description_content" class="rte align_justify" itemprop="description">
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed at ante. Mauris eleifend, quam a
                        vulputate dictum, massa quam dapibus leo.</p>
                </div>
            </div>
            <div class="pb-right-column">
                <form id="buy_block" action="" method="post">
                    <div class="box-info-product">
                        <div class="content_prices clearfix">
                            <div class="price">
                                <p class="p-price">
                                    <span ><%=p.getPrice()%> ₫</span>
                                </p>
                                <p class="saleoff-price-item">
                                    <span class="price-label">Tiết kiệm:</span>
                                    <span id="span-discount-percent" class="discount-percent"><%=p.getSale()%></span>
                                    <span id="span-saving-price"><%=p.getSale()*p.getPrice()*0.01%></span>
                                </p>
                                <p class="old-price-item">
                                    <span class="price-label">Giá thị trường:</span>
                                    <span id="span-list-price"><%=p.getSale()*p.getPrice()*0.01+p.getPrice()%>&nbsp;₫</span>
                                </p>
                            </div>
                            </p>
                            <div class="clear"></div>
                        </div>
                        <div class="product_attributes clearfix">
                            <p id="quantity_wanted_p"><label>Số lượng:</label>
                                <input type="number" maxlength="<%=p.getQuantity()%>" required name="product_detail_qty" value="1"/>
                                <span class="clearfix"></span>
                            </p>

                        </div>
                        <div class="box-cart-bottom">
                            <div>
                                <p id="add_to_cart" class="buttons_bottom_block no-print">
                                    <a product-code="<%=p.getCode()%>" href="" class="exclusive ajax_add_to_cart_button">
                                        <span>Add to cart</span>
                                    </a>
                                </p>
                            </div>
                            <p class="buttons_bottom_block no-print">
                                <a product-code="<%=p.getCode()%>" class="wishlist_add" href="#" rel="nofollow" title="Add to my wishlist">
                                    <i class="fa fa-heart-o" aria-hidden="true"></i>
                                    Add to my wishlist
                                </a>
                            </p>
                            <div class="fb-share-button" 
							    data-href="http://localhost:8080/product?code=<%=p.getCode()%>" 
							    data-layout="button_count">
							 </div>
                            <strong></strong>
                        </div>
                    </div>
                </form>
                <div class="seller-container">
                    <div id="seller-list" style=""><div data-reactroot="">
                        <div class="seller-block-wrap">
                            <div class="current-seller" data-id="1">
                                <div class="name item">
                                    <i class="fa fa-home" aria-hidden="true"></i>
                                    <p class="text">
                                        <span>FS Trading</span><br>
                                        <span class="text-small">Cam kết chính hiệu 100%</span>
                                    </p>
                                </div>
                                <div class="warranty-info item">
                                    <i class="fa fa-cc" aria-hidden="true"></i>
                                    <p class="text">Đảm bảo hoàn tiền 111%
                                        <br><span class="text-small">Nếu phát hiện hàng giả</span>
                                    </p>
                                </div>
                            </div>
                            <div class="other-seller"></div>
                        </div>
                    </div>
                    </div>
                    <div class="additional">
                        <div class="contact">
                            <div class="item">
                                <i class="fa fa-phone" aria-hidden="true"></i>
                                <p>
                                    <b>Liên hệ</b><br>
                                    Hotline đặt hàng <a href="tel:1800 6963">1800 0000</a><br>
                                    <small>(Miễn phí, 8-21h cả T7, CN)</small>
                                </p>
                            </div>
                            <div class="item">
                                <i class="fa fa-envelope-o" aria-hidden="true"></i>
                                <p>
                                    Email: <a href="mailto:hotro@fs.vn">hotro@fs.vn</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <section class="tm-tabcontent">
        <ul id="productpage_tab" class="nav nav-tabs clearfix">
            <li class="active"><a data-toggle="tab" href="#moreinfo" class="moreinfo">THÔNG TIN CHI TIẾT</a></li>
        </ul>
        <div class="tab-content">
            <ul id="moreinfo" style="display: block;" class="tm_productinner tab-pane">
                <li>
                    <table class="table-data-sheet">
                        <tr class="odd">
                            <td>Tên sách</td>
                            <td><%=p.getName()%></td>
                        </tr>
                        <tr class="even">
                            <td>Tác giả</td>
                            <td><%=p.getAuthor()%></td>
                        </tr>
                        <tr class="odd">
                            <td>Thể loại</td>
                            <td><%=p.getType()%></td>
                        </tr>
                        <tr class="even">
                            <td>Nhà xuất bản</td>
                            <td><%=p.getNxb()%></td>
                        </tr>
                        <tr class="odd">
                            <td>Giá</td>
                            <td><%=p.getPrice()%>&nbsp;₫</td>
                        </tr>

                    </table>
                </li>
            </ul>
        </div>
    </section>
    <section class="page-product-box">
        <h3 id="#idTab5" class="idTabHrefShort page-product-heading">Reviews</h3>
        <%
            List<Comment> lc = (List<Comment>) request.getAttribute("comments");
            System.out.println(33);
            if (lc==null || lc.size() ==0){
        %>
        <div id="idTab5">
            <div id="product_comments_block_tab">
                <p class="align_center">No customer comments for the moment.</p>
            </div>
        </div>
        <%}else {%>
        <ul class="list-group list-comment">
            <%
                for (Comment cmt: lc){
            %>
            <li class="list-group-item">
                <p class="image">
                    <img src="/client/img/avatar.png" height="45" width="45" alt="">
                    <span>
                            <span><%=cmt.getCustomer().getName()%></span><br>
                            <span><%=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(cmt.getCreateAt())%></span>
                    </span>
                </p>
                <p>
                    <%=cmt.getComment()%>
                </p>
                <div class='rating-stars text-center'>
                    <ul class='stars'>
                        <%
                            for (int i=0;i<cmt.getStarVote();i++){

                        %>
                        <li class='star selected'>
                            <i class='fa fa-star'></i>
                        </li>
                        <%}%>
                    </ul>
                </div>
            </li>
            <%}%>
        </ul>
        <%}%>
    </section>
    <%
        Customer cus2 = (Customer) session.getAttribute("currentCustomer");
        if(cus2!=null){
    %>
    <p>
    <form class="form-inline" id="add-comment">
        <section class='rating-widget'>
            <div><i>Đánh giá: </i></div>
            <!-- Rating Stars Box -->
            <div class='rating-stars text-center'>
                <ul class='stars'>
                    <li class='star' title='Poor' data-value='1'>
                        <i class='fa fa-star '></i>
                    </li>
                    <li class='star' title='Fair' data-value='2'>
                        <i class='fa fa-star'></i>
                    </li>
                    <li class='star' title='Good' data-value='3'>
                        <i class='fa fa-star '></i>
                    </li>
                    <li class='star' title='Excellent' data-value='4'>
                        <i class='fa fa-star '></i>
                    </li>
                    <li class='star' title='WOW!!!' data-value='5'>
                        <i class='fa fa-star '></i>
                    </li>
                </ul>
            </div>
        </section>
        <div class="form-group">
            <input type="hidden" name="productId" value="<%=p.getId()%>">
            <input type="hidden" name="star-vote" value="0">
            <input type="text" name="comment" placeholder="Bình luận của bạn..." class="form-control" id="email">
        </div>
        <button type="submit" class="btn btn-default btn-danger">Bình luận</button>
    </form>
    </p>
    <%
    }else {
    %>
    <form action="/customer" method="get">
        <input type="hidden" name="action" value="login">
        <input type="hidden" name="next_page" value="<%=p.getCode()%>">
        <button type="submit" class="btn btn-default btn-custom3">
            Đăng nhập để comment
        </button>
    </form>
    <%}%>

</div>
