
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="center_column" class="center_column col-xs-12">
    <div class="breadcrumb clearfix">
        <a class="home" href="/home" title="Return to Home">
            <i class="icon-home"></i>
        </a>
        <span class="navigation-pipe">&gt;</span>
        <span class="navigation_page">Giỏ hàng của bạn</span>
    </div>
    <h1 id="cart_title" class="page-heading">
        GIỎ HÀNG (<span class="cart_length"></span> <span> sản phẩm</span>)
    </h1>
    <p style="display:none" id="emptyCartWarning" class="alert alert-warning">Your shopping cart is empty.</p>

    <div id="order-detail-content" class="table_block table-responsive col-md-8">
        <table id="cart_summary" class="table table-bordered">
            <thead>
            <tr>
                <th class="cart_product first_item">Product</th>
                <th class="cart_description item">Description</th>
                <th class="cart_unit item">Unit price</th>
                <th class="cart_quantity item">Qty</th>
                <th class="cart_total item">Total</th>
                <th class="cart_delete last_item">&nbsp;</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
    <div class="col-md-4 order-right">
        <div class="each-row">
            <div class="box-style fee">
                <p class="list-info-price">

                    <strong class="total_price"></strong>
                    <span>Tạm tính: </span>
                </p>
            </div>
            <div class="box-style fee">
                <div class="total2 clearfix">
                    <span class="text-label">Thành tiền: </span>
                    <div class="amount">
                        <p>
                            <strong class="total_price"></strong>
                        </p>
                        <p class="text-right">
                            <small>(Đã bao gồm VAT)</small>
                        </p>
                    </div>
                </div>
            </div>
            <button type="button" class="btn btn-large btn-block btn-checkout" onclick="location.href='/order?action=checkout';return false;">Tiến hành đặt hàng</button>

        </div>
    </div>
</div>

