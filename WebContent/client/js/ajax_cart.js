
$(document).ready(function () {
    //-------------------------------------------- carts -----------------------------------------------
    var cart = {
        add : function (product, quantity) {
            var arr3 = JSON.parse(localStorage.getItem("carts"));
            var check = true;
            arr3.forEach(function(item){
                if(item.product.code === product.code){
                    item.quantity += quantity;
                    check = false;
                }
            });
            if(check === true){
                arr3.push({
                    'product' : product,
                    'quantity' : quantity
                })
            }
            localStorage.setItem("carts", JSON.stringify(arr3));
            alert('Thêm thành công !')
            this.updateCart();
        },
        edit : function(product, quantity){
            var arr3 = JSON.parse(localStorage.getItem("carts"));
            arr3.forEach(function(item){
                if(item.product.code === product.code){
                    item.quantity = quantity;
                }
            })
            localStorage.setItem("carts", JSON.stringify(arr3))
            this.updateCart();
            this.updateCartContent();
        },
        remove : function (product) {
            var arr3 = JSON.parse(localStorage.getItem("carts"));
            arr3.forEach(function(item, index){
                if(item.product.code === product.code){
                    arr3.splice(index,1)
                }
            });
            localStorage.setItem("carts", JSON.stringify(arr3))
            this.updateCart();
            this.updateCartContent();
        },
        updateCart : function () {
            $('.cart_list').children().remove();
            var cart_arr = JSON.parse(localStorage.getItem("carts"));
            var totalPrice = 0;
            var totalQuantity = 0;
            if(cart_arr.length === 0){
                $('#cart_length').text("Cart 0 products")
                $('.cart_block_no_products').removeClass('unvisible')
                $('.cart-prices').addClass('unvisible')
                $('.cart-buttons').addClass('unvisible')
            }else {
                $('.cart_block_no_products').addClass('unvisible')
                $('.cart-prices').removeClass('unvisible')
                $('.cart-buttons').removeClass('unvisible')

                cart_arr.forEach(function (item) {
                    totalPrice += item.quantity * item.product.price;
                    totalQuantity += item.quantity;
                    $('.cart_list').append('<li class="first_item">' +
                        '<a class="cart-images" >' +
                        '<img width="70" height="70" src="admin/file?action=image&name=' + item.product.image + '" alt="' + item.product.name + '"></a>' +
                        '<div class="cart-info">' +
                        '<div class="product-name"> <span class="quantity-formated">' +
                        '<span class="quantity">' + item.quantity + '</span>&nbsp;x&nbsp;</span>' +
                        '<a class="cart_block_product_name" title="' + item.product.name + '">' + item.product.name + '</a>' +
                        '</div>' +
                        '<div class="product-atributes"> <a href="" title="Product detail">'+all.getAuthorName(item.product.authorId)+'</a></div>' +
                        '<span class="price setPrice">$ ' + item.product.price + '</span>' +
                        '</div>' +
                        '<span class="remove_link"> <a product-code="' + item.product.code + '" class="ajax_cart_block_remove_link" rel="nofollow" title="remove this product from my cart">&nbsp;</a> </span>' +
                        '</li>')
                })

            }
            $(".cart_block_total").text("$ " + totalPrice);
            $('.cart_length').text(totalQuantity)
            $('html, body').animate({scrollTop: '10px'}, 500);
        },
        updateCartContent : function () {
            var content = $('#order-detail-content tbody');
            content.children().remove();
            var cart_arr = JSON.parse(localStorage.getItem("carts"));
            if(cart_arr.length === 0){
                content.parents('table').find('tfoot').hide();
                content.append('<h1>Giỏ hàng trống !!</h1>')
            }else {
                content.parents('table').find('tfoot').show();
                var totalPrice = 0;
                var totalQuantity = 0;
                cart_arr.forEach(function (item) {
                    totalPrice += item.quantity * item.product.price;
                    totalQuantity += item.quantity;
                    content.append('<tr class="cart_item address_0 odd">\n' +
                        '                <input type="hidden" value="'+item.product.code+'"/>\n' +
                        '                <td class="cart_product"><a href="">\n' +
                        '                    <img src="admin/file?action=image&name='+item.product.image+'" alt="'+item.product.name+'" width="60" height="60"/></a></td>\n' +
                        '                <td class="cart_description">\n' +
                        '                    <p class="product-name"><a href="">'+item.product.name+'</a></p>\n' +
                        '                    <small class="cart_ref">Tác giả : '+all.getAuthorName(item.product.authorId)+'</small>\n' +
                        '                </td>\n' +
                        '                <td class="cart_unit" data-title="Unit price"><span class="price" > ' +
                        '                        <span class="price special-price">'+item.product.price+' ₫</span> ' +
                        '                </td>\n' +
                        '                <td class="cart_quantity text-center">\n' +
                        '                    <input size="2" type="number" autocomplete="off" class="cart_quantity_input form-control grey"\n' +
                        '                           value="'+item.quantity+'" name="cart_quantity"/>\n' +
                        '                    <div class="cart_quantity_button clearfix">\n' +
                        '                        <a rel="nofollow" class="cart_quantity_down btn btn-default button-minus" href="" title="Subtract">\n' +
                        '                            <span><i class="icon-minus"></i></span>\n' +
                        '                        </a>\n' +
                        '                        <a rel="nofollow" class="cart_quantity_up btn btn-default button-plus" href="" title="Add">\n' +
                        '                            <span><i class="icon-plus"></i></span>\n' +
                        '                        </a>\n' +
                        '                    </div>\n' +
                        '                </td>\n' +
                        '                <td class="cart_total" data-title="Total">\n' +
                        '                    <span class="price"> '+item.product.price*item.quantity+' ₫</span>\n' +
                        '                </td>\n' +
                        '                <td class="cart_delete text-center">\n' +
                        '                    <div>\n' +
                        '                        <a product-code="'+item.product.code+'" rel="nofollow" title="Delete" class="ajax_cart_block_remove_link" href=""><i class="icon-trash"></i></a>\n' +
                        '                    </div>\n' +
                        '                </td>\n' +
                        '            </tr>')
                })
                $("#total_product").text(totalPrice +' ₫');
                $('.total_price').text(totalPrice+' ₫');
            }
        }

    };
    if(!localStorage.carts){
        localStorage.setItem("carts", JSON.stringify([]));
    }
    cart.updateCart();
    $(document).on('click', '.ajax_add_to_cart_button', function (e) {
        e.preventDefault();
        var qty = $('input[name="product_detail_qty"]').val();
        if(qty === undefined || qty < 1)
            qty = 1;
        $.ajax({
            url : 'admin/product?action=getByCode&code=' + $(this).attr('product-code'),
            type : 'POST',
            dataType: 'json',
            contentType: 'application/json',
            success : function (res) {
                if(JSON.stringify(res) !== JSON.stringify({})){
                    cart.add(res, parseInt(qty));
                }
            },error : function () {
                alert('lỗi detail cart')
            }
        })
    });
    $(document).on('click', '.ajax_cart_block_remove_link', function (e) {
        e.preventDefault();
        var product = {
            code : $(this).attr('product-code')
        };
        cart.remove(product);
    });
    $(document).on('change', 'input[name="cart_quantity"]', function(){
        var product = {
            code : $(this).parents('tr').find('input[type="hidden"]').val()
        };
        var quantity = parseInt($(this).val());
        cart.edit(product, quantity);
    });
    $(document).on('click', '.cart_quantity_up', function(e){
        e.preventDefault();
        var input = $(this).parents('td').find('input[name="cart_quantity"]');
        var oldVal = input.val();
        input.val(parseInt(oldVal) + 1);
        input.trigger("change");
    });
    $(document).on('click', '.cart_quantity_down', function(e){
        e.preventDefault();
        var input = $(this).parents('td').find('input[name="cart_quantity"]');
        var oldVal = input.val();
        if(oldVal > 0)
            input.val(parseInt(oldVal) - 1);
        input.trigger("change");
    });
    cart.updateCartContent();
});